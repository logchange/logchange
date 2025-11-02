package dev.logchange.maven_plugin.mojo.release;

import dev.logchange.commands.release.ReleaseVersionCommand;
import dev.logchange.core.format.release_date.ReleaseDateOption;
import lombok.CustomLog;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.util.Objects;

import static dev.logchange.commands.Constants.*;
import static org.apache.maven.project.MavenProject.EMPTY_PROJECT_VERSION;

@CustomLog
@Mojo(name = RELEASE_COMMAND,
        defaultPhase = LifecyclePhase.NONE,
        requiresProject = false,
        threadSafe = true,
        requiresDependencyResolution = ResolutionScope.NONE,
        aggregator = true
)
public class ReleaseVersionMojo extends AbstractMojo {

    private static final String NO_POM_XML_VERSION = "1";
    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;
    @Parameter(defaultValue = "", property = VERSION_TO_RELEASE_PROPERTY)
    private String versionToRelease;
    @Parameter(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, property = UNRELEASED_VERSION_DIR_PROPERTY)
    private String unreleasedVersionDir;
    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_PROPERTY)
    private String inputDir;
    @Parameter(defaultValue = DEFAULT_OUTPUT_FILE, property = OUTPUT_FILE_PROPERTY)
    private String outputFile;
    @Parameter(defaultValue = DEFAULT_CONFIG_FILE, property = CONFIG_FILE_PROPERTY)
    private String configFile;
    @Parameter(defaultValue = "false", property = GENERATE_CHANGES_XML_PROPERTY)
    private boolean isGenerateChangesXml;
    @Parameter(defaultValue = DEFAULT_XML_OUTPUT_FILE, property = XML_OUTPUT_FILE_PROPERTY)
    private String xmlOutputFile;
    @Parameter(defaultValue = "", property = RELEASE_DATE_PROPERTY)
    private String releaseDate;

    @Override
    public void execute() {
        String version = ReleaseVersionCommand.getVersion(getVersion());
        getLog().info(RELEASE_COMMAND_START_LOG + version);
        ReleaseVersionCommand.of(
                DEFAULT_PATH,
                version,
                unreleasedVersionDir,
                inputDir,
                outputFile,
                configFile,
                isGenerateChangesXml,
                xmlOutputFile,
                ReleaseDateOption.of(releaseDate)
        ).execute();
        getLog().info(RELEASE_COMMAND_END_LOG);
    }

    private String getVersion() {
        if (StringUtils.isNotBlank(versionToRelease)) {
            log.info("Version from versionToRelease is: " + versionToRelease);
            return versionToRelease;
        }

        if (project != null && !Objects.equals(project.getVersion(), EMPTY_PROJECT_VERSION) && !Objects.equals(project.getVersion(), NO_POM_XML_VERSION)) {
            String projectVersion = project.getVersion();
            log.info("Version from project is: " + projectVersion);
            return project.getVersion();
        }

        throw new IllegalStateException("No version is defined in the project, it is not a Maven project, and the --versionToRelease option was not used.");
    }
}
