package dev.logchange.gradle_plugin.aggregate;

import dev.logchange.commands.aggregate.AggregateVersionCommand;
import dev.logchange.commands.release.ReleaseVersionCommand;
import dev.logchange.gradle_plugin.LogchangePluginExtension;
import lombok.CustomLog;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

import static dev.logchange.commands.Constants.*;

@CustomLog
public abstract class AggregateProjectsTask extends DefaultTask {

    @Setter
    private LogchangePluginExtension extension;

    private String aggregateVersion;

    @Option(option = AGGREGATE_VERSION_PROPERTY, description = AGGREGATE_VERSION_OPTION_DESCRIPTION)
    public void setAggregateVersion(String aggregateVersion) {
        this.aggregateVersion = aggregateVersion;
    }

    @TaskAction
    public void doInit() {
        log.info(AGGREGATE_COMMAND_START_LOG);

        AggregateVersionCommand.of(
                DEFAULT_PATH,
                ReleaseVersionCommand.getVersion(aggregateVersion),
                extension.getInputDir(),
                extension.getConfigFile()
        ).execute();

        log.info(AGGREGATE_COMMAND_END_LOG);
    }
}
