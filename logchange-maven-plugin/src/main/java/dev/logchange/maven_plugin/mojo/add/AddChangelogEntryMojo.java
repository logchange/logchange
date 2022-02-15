package dev.logchange.maven_plugin.mojo.add;

import dev.logchange.core.application.changelog.repository.ChangelogEntryRepository;
import dev.logchange.core.application.changelog.service.add.AddChangelogEntryService;
import dev.logchange.core.domain.changelog.command.AddChangelogEntryUseCase;
import dev.logchange.core.domain.changelog.command.AddChangelogEntryUseCase.AddChangelogEntryCommand;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryTitle;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogEntryRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.components.interactivity.Prompter;
import org.codehaus.plexus.components.interactivity.PrompterException;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

import static dev.logchange.maven_plugin.Constants.*;

@Mojo(name = ADD_COMMAND, defaultPhase = LifecyclePhase.NONE)
public class AddChangelogEntryMojo extends AbstractMojo {

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_MVN_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, property = UNRELEASED_VERSION_DIR_MVN_PROPERTY)
    private String unreleasedVersionDir;

    @Inject
    private Prompter prompter;

    @Override
    public void execute() {
        try {
            String outputFile = getOutputFileName();
            ChangelogEntry entry = getChangelogEntry();
            executeAdd(inputDir, unreleasedVersionDir, outputFile, entry);
        } catch (PrompterException e) {
            getLog().error("Error during getting information from user!", e);
        }
    }

    public void executeAdd(String inputDir, String unreleasedVersionDir, String outputFile, ChangelogEntry entry) {
        String path = "./" + inputDir + "/" + unreleasedVersionDir + "/" + outputFile;
        File entryFile = createFile(path);

        ChangelogEntryRepository repository = new FileChangelogEntryRepository(entryFile);
        AddChangelogEntryUseCase addChangelogEntry = new AddChangelogEntryService(repository);
        AddChangelogEntryCommand command = AddChangelogEntryCommand.of(entry);

        addChangelogEntry.handle(command);
    }

    private File createFile(String path) {
        try {
            File changelog = new File(path);
            if (changelog.createNewFile()) {
                getLog().info("Created: " + changelog.getName());
                return changelog;
            } else {
                String msg = "Entry with name: " + changelog.getName() + "  already exists!";
                getLog().warn(msg);
                throw new RuntimeException(msg);
            }
        } catch (IOException e) {
            String msg = "An error occurred while creating empty changelog entry file with path: " + path;
            getLog().error(msg, e);
            throw new RuntimeException(msg);
        }
    }

    private String getOutputFileName() throws PrompterException {
        while (true) {
            String name = prompter.prompt("What is the filename?");
            if (StringUtils.isBlank(name)) {
                prompter.showMessage("Filename cannot be empty nor blank!!!");
                continue;
            }
            if (StringUtils.isWhitespace(name)) {
                prompter.showMessage("Filename cannot contain whitespace!!!");
                continue;
            }
            name = name.replace(".yml", "").replace(".yaml", "");
            return name + ".yml";
        }
    }

    private ChangelogEntry getChangelogEntry() throws PrompterException {
        ChangelogEntryTitle title = getTitle();
        return null;
    }

    private ChangelogEntryTitle getTitle() throws PrompterException {
        while (true) {
            try {
                return ChangelogEntryTitle.of(prompter.prompt("What is changelog's entry title?:"));
            } catch (IllegalArgumentException e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }
}
