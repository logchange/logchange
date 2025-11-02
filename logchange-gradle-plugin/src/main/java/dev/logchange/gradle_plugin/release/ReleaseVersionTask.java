package dev.logchange.gradle_plugin.release;

import dev.logchange.commands.release.ReleaseVersionCommand;
import dev.logchange.core.format.release_date.ReleaseDateOption;
import dev.logchange.gradle_plugin.LogchangePluginExtension;
import lombok.CustomLog;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

import static dev.logchange.commands.Constants.*;

@Setter
@CustomLog
public abstract class ReleaseVersionTask extends DefaultTask {

    private LogchangePluginExtension extension;

    private Project project;

    private String versionToRelease;

    private String releaseDate;

    @Option(option = VERSION_TO_RELEASE_PROPERTY, description = VERSION_TO_RELEASE_OPTION_DESCRIPTION_EMPTY)
    public void setVersionToRelease(String versionToRelease) {
        this.versionToRelease = versionToRelease;
    }

    @Option(option = RELEASE_DATE_PROPERTY, description = RELEASE_DATE_OPTION_DESCRIPTION)
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @TaskAction
    public void doRelease() {
        String version = ReleaseVersionCommand.getVersion(getVersion());
        log.info(RELEASE_COMMAND_START_LOG + version);
        ReleaseVersionCommand.of(
                DEFAULT_PATH,
                version,
                extension.getUnreleasedVersionDir(),
                extension.getInputDir(),
                extension.getOutputFile(),
                extension.getConfigFile(),
                extension.isGenerateChangesXml(),
                extension.getXmlOutputFile(),
                ReleaseDateOption.of(releaseDate)
        ).execute();
        log.info(RELEASE_COMMAND_END_LOG);
    }

    @Internal
    public String getVersion() {
        log.info("versionToRelease: " + versionToRelease + " projectVersion: " + project.getVersion());
        if (StringUtils.isNotBlank(versionToRelease)) {
            return versionToRelease;
        }

        if (isVersionDefined(project)) {
            return project.getVersion().toString();
        }

        throw new IllegalStateException("No version defined for projectVersion: " + project.getVersion() + " nor --versionToRelease used");
    }


    boolean isVersionDefined(Project project) {
        return Project.DEFAULT_VERSION != project.getVersion();
    }
}


