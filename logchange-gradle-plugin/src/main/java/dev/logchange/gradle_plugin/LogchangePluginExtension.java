package dev.logchange.gradle_plugin;

import static dev.logchange.commands.Constants.DEFAULT_INPUT_DIR;
import static dev.logchange.commands.Constants.DEFAULT_UNRELEASED_VERSION_DIR;

public class LogchangePluginExtension {

    private String inputDir = DEFAULT_INPUT_DIR;
    private String unreleasedVersionDir = DEFAULT_UNRELEASED_VERSION_DIR;

    public String getInputDir() {
        return inputDir;
    }

    public void setInputDir(String inputDir) {
        this.inputDir = inputDir;
    }

    public String getUnreleasedVersionDir() {
        return unreleasedVersionDir;
    }

    public void setUnreleasedVersionDir(String unreleasedVersionDir) {
        this.unreleasedVersionDir = unreleasedVersionDir;
    }

    @Override
    public String toString() {
        return "LogchangePluginExtension{" +
                "inputDir='" + inputDir + '\'' +
                ", unreleasedVersionDir='" + unreleasedVersionDir + '\'' +
                '}';
    }
}
