package dev.logchange.maven_plugin.mojo.add;

import dev.logchange.commands.add.AddChangelogEntryBatchModeParams;
import dev.logchange.commands.add.AddEntryCommand;
import dev.logchange.commands.add.ChangelogEntryProviderFactory;
import dev.logchange.commands.add.OutputFileNameProvider;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.components.interactivity.Prompter;

import javax.inject.Inject;

import static dev.logchange.commands.Constants.*;

@Mojo(name = ADD_COMMAND, defaultPhase = LifecyclePhase.NONE)
public class AddChangelogEntryMojo extends AbstractMojo {

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, property = UNRELEASED_VERSION_DIR_PROPERTY)
    private String unreleasedVersionDir;

    @Parameter(property = FILENAME_PROPERTY)
    private String outputFileName;

    @Parameter(defaultValue = "false", property = BATCH_MODE_PROPERTY)
    private boolean batchMode;

    @Parameter(defaultValue = "false", property = EMPTY_PROPERTY)
    private boolean empty;

    @Parameter(property = "title")
    private String title;

    @Parameter(property = "author")
    private String author;

    @Parameter(property = "type")
    private String type;

    @Parameter(property = "link.name")
    private String linkName;

    @Parameter(property = "link.url")
    private String linkUrl;

    @Inject
    private Prompter prompter;

    @Override
    public void execute() {
        getLog().info(ADD_COMMAND_START_LOG);
        MavenAddEntryPrompter mavenPrompter = MavenAddEntryPrompter.of(prompter);
        AddEntryCommand addEntryCommand = AddEntryCommand.of(DEFAULT_PATH, inputDir, unreleasedVersionDir);
        outputFileName = new OutputFileNameProvider(empty, mavenPrompter, outputFileName).get();
        ChangelogEntry entry = new ChangelogEntryProviderFactory(empty, batchMode, getParams(), mavenPrompter).create().get();
        addEntryCommand.execute(entry, outputFileName);
        getLog().info(ADD_COMMAND_END_LOG);
    }

    private AddChangelogEntryBatchModeParams getParams() {
        return AddChangelogEntryBatchModeParams.of(
                title,
                author,
                type,
                linkName,
                linkUrl
        );
    }

}
