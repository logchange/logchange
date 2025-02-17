package dev.logchange.gradle_plugin.add;

import dev.logchange.commands.add.*;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.gradle_plugin.LogchangePluginExtension;
import lombok.CustomLog;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

import static dev.logchange.commands.Constants.*;

@CustomLog
public class AddExampleChangelogEntryTask extends DefaultTask {

    private String fileName;

    @Setter
    private LogchangePluginExtension extension;

    @Option(option = FILENAME_PROPERTY, description = FILENAME_OPTION_DESCRIPTION)
    public void setFileName(String fileName) {
        this.fileName = StringUtils.defaultIfBlank(fileName, DEFAULT_EXAMPLE_OUTPUT_FILENAME);
    }

    @TaskAction
    public void doAdd() {
        log.info(ADD_EXAMPLE_COMMAND_START_LOG);

        AddEntryPrompter gradlePrompter = GradleAddEntryPrompter.of();
        AddEntryCommand addEntryCommand = AddEntryCommand.of(DEFAULT_PATH, extension.getInputDir(), extension.getUnreleasedVersionDir());
        fileName = new OutputFileNameProvider(true, gradlePrompter, fileName).get();
        ChangelogEntry entry = new ChangelogEntryProviderFactory(
                true,
                false,
                AddChangelogEntryBatchModeParams.empty(),
                gradlePrompter).create().get();
        addEntryCommand.execute(entry, fileName);

        log.info(ADD_EXAMPLE_COMMAND_END_LOG);
    }
}
