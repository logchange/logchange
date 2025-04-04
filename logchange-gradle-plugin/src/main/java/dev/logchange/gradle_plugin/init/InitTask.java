package dev.logchange.gradle_plugin.init;

import dev.logchange.commands.init.InitProjectCommand;
import dev.logchange.gradle_plugin.LogchangePluginExtension;
import lombok.CustomLog;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import static dev.logchange.commands.Constants.INIT_COMMAND_END_LOG;
import static dev.logchange.commands.Constants.INIT_COMMAND_START_LOG;

@CustomLog
public abstract class InitTask extends DefaultTask {

    @Setter
    private LogchangePluginExtension extension;

    @TaskAction
    public void doInit() {
        log.info(INIT_COMMAND_START_LOG);
        InitProjectCommand.of(
                extension.getRootPath(),
                extension.getInputDir(),
                extension.getUnreleasedVersionDir(),
                extension.getOutputFile()
        ).execute();
        log.info(INIT_COMMAND_END_LOG);
    }
}
