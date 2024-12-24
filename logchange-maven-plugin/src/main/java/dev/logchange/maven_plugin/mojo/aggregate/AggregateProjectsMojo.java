package dev.logchange.maven_plugin.mojo.aggregate;

import dev.logchange.commands.aggregate.AggregateVersionCommand;
import dev.logchange.commands.release.ReleaseVersionCommand;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import static dev.logchange.commands.Constants.*;

@Mojo(name = AGGREGATE_COMMAND, defaultPhase = LifecyclePhase.NONE, requiresProject = false)
public class AggregateProjectsMojo extends AbstractMojo {

    @Parameter(property = AGGREGATE_VERSION_PROPERTY, required = true)
    private String aggregateVersion;

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_CONFIG_FILE, property = CONFIG_FILE_PROPERTY)
    private String configFile;

    @Override
    public void execute() {
        getLog().info(AGGREGATE_COMMAND_START_LOG);
        AggregateVersionCommand.of(PATH, ReleaseVersionCommand.getVersion(aggregateVersion), inputDir, configFile).execute();
        getLog().info(AGGREGATE_COMMAND_END_LOG);
    }
}
