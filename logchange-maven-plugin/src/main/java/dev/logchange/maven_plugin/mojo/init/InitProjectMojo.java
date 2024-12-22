package dev.logchange.maven_plugin.mojo.init;

import dev.logchange.commands.init.InitProjectCommand;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import static dev.logchange.commands.Constants.*;

@Mojo(name = INIT_COMMAND, defaultPhase = LifecyclePhase.NONE)
public class InitProjectMojo extends AbstractMojo {

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, property = UNRELEASED_VERSION_DIR_MVN_PROPERTY)
    private String unreleasedVersionDir;

    @Parameter(defaultValue = DEFAULT_OUTPUT_FILE, property = OUTPUT_FILE_MVN_PROPERTY)
    private String outputFile;

    @Override
    public void execute() {
        getLog().info(INIT_COMMAND_START_LOG);
        InitProjectCommand.of(DEFAULT_PATH, inputDir, unreleasedVersionDir, outputFile).execute();
        getLog().info(INIT_COMMAND_END_LOG);
    }
}
