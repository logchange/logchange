package dev.logchange.maven_plugin.mojo;

import dev.logchange.maven_plugin.util.Dir;
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
        getLog().info("Initialize project for logchange-maven-plugin");
        createEmptyChangelogFile(outputFile);
        executeInit(inputDir, unreleasedVersionDir);
        getLog().info("Initialize project successful");
    }

    public void executeInit(String inputDir, String unreleasedVersionDir) {
        Dir.of(getLog(), inputDir).create();
        Dir.of(getLog(), inputDir + "/" + unreleasedVersionDir).create();
        GitKeep.of(getLog(), inputDir + "/" + unreleasedVersionDir + "/").create();
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
            String msg = "An error occurred while creating empty changelog.";
            getLog().error(msg, e);
            throw new RuntimeException(msg);
        }
    }
}
