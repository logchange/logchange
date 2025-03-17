package dev.logchange.maven_plugin.mojo.archive;

import dev.logchange.commands.archive.ArchiveVersionCommand;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import static dev.logchange.commands.Constants.*;

@Mojo(name = ARCHIVE_COMMAND,
        defaultPhase = LifecyclePhase.NONE,
        requiresProject = false,
        threadSafe = true,
        requiresDependencyResolution = ResolutionScope.NONE,
        aggregator = true
)
public class ArchiveVersionMojo extends AbstractMojo {

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_OUTPUT_FILE, property = OUTPUT_FILE_PROPERTY)
    private String version;

    @Parameter(defaultValue = DEFAULT_CONFIG_FILE, property = CONFIG_FILE_PROPERTY)
    private String configFile;

    @Override
    public void execute() {
        getLog().info(ARCHIVE_COMMAND_START_LOG);
        ArchiveVersionCommand.of(DEFAULT_PATH, inputDir, version, configFile).execute(false);
        getLog().info(ARCHIVE_COMMAND_END_LOG);
    }
}
