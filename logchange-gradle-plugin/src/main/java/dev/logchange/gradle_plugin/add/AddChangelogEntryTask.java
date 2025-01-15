package dev.logchange.gradle_plugin.add;

import dev.logchange.commands.add.*;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.gradle_plugin.LogchangePluginExtension;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

import static dev.logchange.commands.Constants.*;

public abstract class AddChangelogEntryTask extends DefaultTask {

    private String fileName;
    private boolean empty;
    private boolean batchMode;
    private String title;
    private String author;
    private String type;
    private String linkName;
    private String linkUrl;

    @Setter
    private LogchangePluginExtension extension;

    @Option(option = FILENAME_PROPERTY, description = FILENAME_OPTION_DESCRIPTION)
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Option(option = EMPTY_PROPERTY, description = EMPTY_OPTION_DESCRIPTION)
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    @Option(option = BATCH_MODE_PROPERTY, description = BATCH_MODE_OPTION_DESCRIPTION)
    public void setBatchMode(boolean batchMode) {
        this.batchMode = batchMode;
    }

    @Option(option = "title", description = "")
    public void setTitle(String title) {
        this.title = title;
    }

    @Option(option = "author", description = "")
    public void setAuthor(String author) {
        this.author = author;
    }

    @Option(option = "type", description = "")
    public void setType(String type) {
        this.type = type;
    }

    @Option(option = "link.name", description = "")
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    @Option(option = "link.url", description = "")
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    @TaskAction
    public void doAdd() {
        getLogger().info(ADD_COMMAND_START_LOG);

        AddEntryPrompter mavenPrompter = GradleAddEntryPrompter.of();
        AddEntryCommand addEntryCommand = AddEntryCommand.of(DEFAULT_PATH, extension.getInputDir(), extension.getUnreleasedVersionDir());
        fileName = new OutputFileNameProvider(empty, mavenPrompter, fileName).get();
        ChangelogEntry entry = new ChangelogEntryProviderFactory(empty, batchMode, getParams(), mavenPrompter).create().get();
        addEntryCommand.execute(entry, fileName);

        getLogger().info(ADD_COMMAND_END_LOG);
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
