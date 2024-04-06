package dev.logchange.maven_plugin.mojo.release;

import dev.logchange.core.format.release_date.ReleaseDate;
import dev.logchange.maven_plugin.mojo.GenerateChangelogMojo;
import dev.logchange.maven_plugin.mojo.init.InitProjectMojo;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;

import static dev.logchange.maven_plugin.Constants.*;

@Mojo(name = RELEASE_COMMAND, defaultPhase = LifecyclePhase.NONE)
public class ReleaseVersionMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, property = UNRELEASED_VERSION_DIR_MVN_PROPERTY)
    private String unreleasedVersionDir;

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_MVN_PROPERTY)
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
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Begin preparation from new changelog release");

        String unreleasedDir = inputDir + "/" + unreleasedVersionDir;
        String newDirName = inputDir + "/" + "v" + getVersion();

        GenerateChangelogMojo generateChangelogMojo = new GenerateChangelogMojo();
        generateChangelogMojo.setLog(getLog());

        generateChangelogMojo.validate(outputFile, inputDir, configFile);

        ReleaseDate.addToDir(unreleasedDir);
        removeGitKeep(unreleasedDir);
        renameUnreleasedDir(unreleasedDir, newDirName);

        generateChangelogMojo.executeGenerate(outputFile, inputDir, configFile, isGenerateChangesXml, xmlOutputFile);

        new InitProjectMojo().createUnreleased(inputDir, unreleasedVersionDir);
        getLog().info("New changelog release successful");
    }

    private String getVersion() {
        String version = project.getVersion();

        if (version.contains("-")) {
            return version.substring(0, version.indexOf("-"));
        } else {
            return version;
        }
    }

    private void renameUnreleasedDir(String unreleasedDirName, String newDirName) {
        File unreleasedDir = new File(unreleasedDirName);
        File newDir = new File(newDirName);
        if (unreleasedDir.renameTo(newDir)) {
            getLog().info("Renamed " + unreleasedDirName + " to " + newDirName + " successful");
        } else {
            // TODO: throw exception
        }
    }

    private void removeGitKeep(String unreleasedDir) {
        File gitKeep = new File(unreleasedDir + "/" + GIT_KEEP);
        if (gitKeep.delete()) {
            getLog().info("Deleted: " + gitKeep.getName());
        } else {
            getLog().warn(gitKeep.getName() + " cannot be deleted.");
        }
    }
}
