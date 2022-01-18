package dev.logchange.maven_plugin.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;

@Mojo(name = "init", defaultPhase = LifecyclePhase.NONE)
public class InitProjectMojo extends AbstractMojo {

    public static final String GIT_KEEP = ".gitkeep";

    @Parameter(defaultValue = "CHANGELOG.md", property = "finalChangelogName")
    private String finalChangelogName;

    @Parameter(defaultValue = "changelog", property = "yamlFilesDirectory")
    private String yamlFilesDirectory;

    @Parameter(defaultValue = "unreleased", property = "unreleasedVersionDirectory")
    private String unreleasedVersionDirectory;

    @Override
    public void execute() {
        getLog().info("Initialize project for keep-changelog maven plugin");
        generateChangelog(finalChangelogName);
        generateChangelogDirUnreleasedDirGitKeep(yamlFilesDirectory + "/" + unreleasedVersionDirectory + "/");
        getLog().info("Initialize project successful");
    }

    public void generateChangelog(String path) {

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

    public void generateChangelogDirUnreleasedDirGitKeep(String path) {

        try {
            File yamlFilesDirectory = new File(path);
            if (!yamlFilesDirectory.exists()) {
                if (yamlFilesDirectory.mkdir()) {
                    getLog().info("Created: " + yamlFilesDirectory.getName());
                } else {
                    getLog().warn(yamlFilesDirectory.getName() + " cannot be created.");
                }
            } else {
                getLog().warn(yamlFilesDirectory.getName() + " already exists.");
            }

            File gitKeep = new File(path + GIT_KEEP);
            if (gitKeep.createNewFile()) {
                getLog().info("Created: " + gitKeep.getName());
            } else {
                getLog().warn(gitKeep.getName() + " already exists.");
            }
        } catch (IOException e) {
            getLog().error("An error occurred while creating empty .gitkeep.", e);
        }
    }


}
