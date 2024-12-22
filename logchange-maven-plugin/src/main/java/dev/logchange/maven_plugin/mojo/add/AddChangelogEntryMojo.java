package dev.logchange.maven_plugin.mojo.add;

import dev.logchange.commands.add.AddEntryCommand;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.maven_plugin.mojo.add.entry.ChangelogEntryProviderFactory;
import lombok.Value;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.components.interactivity.Prompter;

import javax.inject.Inject;

import static dev.logchange.commands.Constants.*;

@Mojo(name = ADD_COMMAND, defaultPhase = LifecyclePhase.NONE)
public class AddChangelogEntryMojo extends AbstractMojo {

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_MVN_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, property = UNRELEASED_VERSION_DIR_MVN_PROPERTY)
    private String unreleasedVersionDir;

    @Parameter(property = FILENAME_MVN_PROPERTY)
    private String outputFileName;

    @Parameter(defaultValue = "false", property = BATCH_MODE_MVN_PROPERTY)
    private boolean batchMode;

    @Parameter(defaultValue = "false", property = EMPTY_MVN_PROPERTY)
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
        AddEntryCommand addEntryCommand = AddEntryCommand.of(inputDir, unreleasedVersionDir);
        outputFileName = new OutputFileNameProvider(empty, prompter, outputFileName).get();
        ChangelogEntry entry = new ChangelogEntryProviderFactory(empty, batchMode, getParams(), prompter).create().get();
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

    @Value(staticConstructor = "of")
    public static class AddChangelogEntryBatchModeParams {
        String title;
        String author;
        String type;
        String linkName;
        String linkUrl;
    }
}
