package dev.logchange.maven_plugin.mojo;

import dev.logchange.maven_plugin.util.GitKeep;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;

import static dev.logchange.maven_plugin.Constants.*;

@Mojo(name = INIT_COMMAND, defaultPhase = LifecyclePhase.NONE)
public class InitProjectMojo extends AbstractMojo {

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_MVN_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, property = UNRELEASED_VERSION_DIR_MVN_PROPERTY)
    private String unreleasedVersionDir;

    @Parameter(defaultValue = DEFAULT_OUTPUT_FILE, property = OUTPUT_FILE_MVN_PROPERTY)
    private String outputFile;

    @Override
    public void execute() {
        getLog().info("Initialize project for keep-changelog maven plugin");
        createEmptyChangelogFile(outputFile);
        createInputDir(inputDir + "/" + unreleasedVersionDir + "/");
        getLog().info("Initialize project successful");
    }

    public void createEmptyChangelogFile(String path) {
        try {
            File changelog = new File(path);
            if (changelog.createNewFile()) {
                getLog().info("Created: " + changelog.getName());
            } else {
                getLog().warn(changelog.getName() + " already exists.");
            }
        } catch (IOException e) {
            getLog().error("An error occurred while creating empty changelog.");
        }
    }

    public void createInputDir(String path) {
        File newInputDir = new File(path);
        if (!newInputDir.exists()) {
            if (newInputDir.mkdir()) {
                getLog().info("Created: " + newInputDir.getName());
            } else {
                getLog().warn(newInputDir.getName() + " cannot be created.");
            }
        } else {
            getLog().warn(newInputDir.getName() + " already exists.");
        }
        GitKeep.of(getLog(), path).create();
    }




}
