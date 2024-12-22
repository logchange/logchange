package dev.logchange.maven_plugin.mojo.release;

import dev.logchange.commands.release.ReleaseVersionCommand;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import static dev.logchange.commands.Constants.*;

@Mojo(name = RELEASE_COMMAND, defaultPhase = LifecyclePhase.NONE)
public class ReleaseVersionMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, property = UNRELEASED_VERSION_DIR_MVN_PROPERTY)
    private String unreleasedVersionDir;

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_OUTPUT_FILE, property = OUTPUT_FILE_MVN_PROPERTY)
    private String outputFile;

    @Parameter(defaultValue = DEFAULT_CONFIG_FILE, property = CONFIG_FILE_MVN_PROPERTY)
    private String configFile;

    @Parameter(defaultValue = "false", property = GENERATE_CHANGES_XML_PROPERTY)
    private boolean isGenerateChangesXml;

    @Parameter(defaultValue = DEFAULT_XML_OUTPUT_FILE, property = XML_OUTPUT_FILE_PROPERTY)
    private String xmlOutputFile;

    @Override
    public void execute() {
        String version = ReleaseVersionCommand.getVersion(project.getVersion());
        getLog().info(RELEASE_COMMAND_START_LOG + version);
        ReleaseVersionCommand.of(
                version,
                unreleasedVersionDir,
                inputDir,
                outputFile,
                configFile,
                isGenerateChangesXml,
                xmlOutputFile).execute();
        getLog().info(RELEASE_COMMAND_END_LOG);
    }
}
