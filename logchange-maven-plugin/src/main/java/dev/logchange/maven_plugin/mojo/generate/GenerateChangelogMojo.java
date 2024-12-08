package dev.logchange.maven_plugin.mojo.generate;

import dev.logchange.commands.generate.GenerateProjectCommand;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import static dev.logchange.commands.Constants.*;

@Mojo(name = GENERATE_COMMAND, defaultPhase = LifecyclePhase.NONE)
public class GenerateChangelogMojo extends AbstractMojo {

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_MVN_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_OUTPUT_FILE, property = OUTPUT_FILE_MVN_PROPERTY)
    private String outputFile;

    @Parameter(defaultValue = DEFAULT_CONFIG_FILE, property = CONFIG_FILE_MVN_PROPERTY)
    private String configFile;

    @Override
    public void execute() {
        getLog().info("Running generate command...");
        GenerateProjectCommand.of(inputDir, outputFile, configFile).execute(false);
        getLog().info("Changelog successfully generated");
    }
}
