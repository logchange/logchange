package dev.logchange.maven_plugin.mojo.generate;

import dev.logchange.commands.generate.GenerateProjectCommand;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import static dev.logchange.commands.Constants.*;

@Mojo(name = GENERATE_COMMAND,
        defaultPhase = LifecyclePhase.NONE,
        requiresProject = false,
        threadSafe = true,
        requiresDependencyResolution = ResolutionScope.NONE,
        aggregator = true
)
public class GenerateChangelogMojo extends AbstractMojo {

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_OUTPUT_FILE, property = OUTPUT_FILE_PROPERTY)
    private String outputFile;

    @Parameter(defaultValue = DEFAULT_CONFIG_FILE, property = CONFIG_FILE_PROPERTY)
    private String configFile;

    @Override
    public void execute() {
        getLog().info(GENERATE_COMMAND_START_LOG);
        GenerateProjectCommand.of(DEFAULT_PATH, inputDir, outputFile, configFile).execute(false);
        getLog().info(GENERATE_COMMAND_END_LOG);
    }
}
