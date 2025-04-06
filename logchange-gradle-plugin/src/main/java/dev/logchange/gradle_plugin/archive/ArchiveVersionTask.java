package dev.logchange.gradle_plugin.archive;

import dev.logchange.commands.archive.ArchiveVersionCommand;
import dev.logchange.gradle_plugin.LogchangePluginExtension;
import lombok.CustomLog;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

import static dev.logchange.commands.Constants.*;

@CustomLog
public abstract class ArchiveVersionTask extends DefaultTask {

    @Setter
    private LogchangePluginExtension extension;

    private String version;

    @Option(option = VERSION_PROPERTY, description = VERSION_OPTION_DESCRIPTION)
    public void setVersion(String version) {
        this.version = version;
    }


    @TaskAction
    public void doGenerate() {
        log.info(ARCHIVE_COMMAND_START_LOG);
        ArchiveVersionCommand.of(
                DEFAULT_PATH,
                extension.getInputDir(),
                version,
                extension.getConfigFile()
        ).execute();
        log.info(ARCHIVE_COMMAND_END_LOG);
    }
}
