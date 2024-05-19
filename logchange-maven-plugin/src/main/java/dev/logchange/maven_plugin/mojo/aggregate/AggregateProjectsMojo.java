package dev.logchange.maven_plugin.mojo.aggregate;

import dev.logchange.core.application.config.ConfigRepository;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.config.FileConfigRepository;
import dev.logchange.maven_plugin.util.ConfigFile;
import dev.logchange.maven_plugin.util.Dir;
import dev.logchange.maven_plugin.util.GitKeep;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;

import static dev.logchange.maven_plugin.Constants.*;
import static dev.logchange.maven_plugin.mojo.GenerateChangelogMojo.findChangelogDirectory;
import static dev.logchange.maven_plugin.mojo.GenerateChangelogMojo.findConfig;

@Mojo(name = AGGREGATE_COMMAND, defaultPhase = LifecyclePhase.NONE, requiresProject = false)
public class AggregateProjectsMojo extends AbstractMojo {


    @Parameter(property = AGGREGATE_VERSION_DIR_MVN_PROPERTY, required = true)
    private String aggregateVersionDir;

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_MVN_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_CONFIG_FILE, property = CONFIG_FILE_MVN_PROPERTY)
    private String configFile;

    @Parameter(defaultValue = DEFAULT_AGGREGATE_OUTPUT_FILE, property = OUTPUT_FILE_MVN_PROPERTY)
    private String outputFile;

    @Override
    public void execute() {
        getLog().info("Aggregate changelog version for different projects");
        aggregate(aggregateVersionDir, inputDir, configFile, outputFile);
        getLog().info("Aggregate successful");
    }

    private void aggregate(String aggregateVersionDir, String yamlFilesDirectory, String configFile, String outputFile) {
        File changelogDirectory = findChangelogDirectory("./" + yamlFilesDirectory, getLog());
        Config config = findConfig("./" + yamlFilesDirectory + "/" + configFile, true, getLog());


    }

}
