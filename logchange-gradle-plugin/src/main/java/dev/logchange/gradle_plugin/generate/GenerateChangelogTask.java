package dev.logchange.gradle_plugin.generate;

import dev.logchange.commands.generate.GenerateProjectCommand;
import dev.logchange.gradle_plugin.LogchangePluginExtension;
import lombok.CustomLog;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import static dev.logchange.commands.Constants.*;

@CustomLog
public abstract class GenerateChangelogTask extends DefaultTask {

    @Setter
    private LogchangePluginExtension extension;

    @TaskAction
    public void doGenerate() {
        log.info(GENERATE_COMMAND_START_LOG);
        GenerateProjectCommand.of(
                DEFAULT_PATH,
                extension.getInputDir(),
                extension.getOutputFile(),
                extension.getConfigFile()
        ).execute(false);
        log.info(GENERATE_COMMAND_END_LOG);
    }
}
