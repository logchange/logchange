package dev.logchange.gradle_plugin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static dev.logchange.commands.Constants.*;

@Getter
@Setter
@ToString
public class LogchangePluginExtension {

    private String rootPath = DEFAULT_PATH;
    private String inputDir = DEFAULT_INPUT_DIR;
    private String unreleasedVersionDir = DEFAULT_UNRELEASED_VERSION_DIR;
    private String outputFile = DEFAULT_OUTPUT_FILE;
    private String configFile = DEFAULT_CONFIG_FILE;
    private boolean generateChangesXml = false;
    private String xmlOutputFile = DEFAULT_XML_OUTPUT_FILE;

}
