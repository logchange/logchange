package dev.logchange.gradle_plugin.lint;

import dev.logchange.commands.lint.LintProjectCommand;
import dev.logchange.gradle_plugin.LogchangePluginExtension;
import lombok.CustomLog;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import static dev.logchange.commands.Constants.*;

@CustomLog
public abstract class LintChangelogTask extends DefaultTask {

    @Setter
    private LogchangePluginExtension extension;

    @TaskAction
    public void doLint() {
        log.info(LINT_COMMAND_START_LOG);
        LintProjectCommand.of(DEFAULT_PATH,
                extension.getInputDir(),
                extension.getOutputFile(),
                extension.getConfigFile()
        ).validate();
        log.info(LINT_COMMAND_END_LOG);

    }
}
