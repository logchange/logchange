package dev.logchange.maven_plugin.mojo.aggregate;

import dev.logchange.core.application.changelog.service.aggregate.AggregateChangelogsVersionsService;
import dev.logchange.core.domain.changelog.command.AggregateChangelogsVersionsUseCase.AggregateChangelogsVersionsCommand;
import dev.logchange.core.domain.config.model.Config;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

import static dev.logchange.maven_plugin.Constants.*;
import static dev.logchange.maven_plugin.mojo.GenerateChangelogMojo.findChangelogDirectory;
import static dev.logchange.maven_plugin.mojo.GenerateChangelogMojo.findConfig;
import static dev.logchange.maven_plugin.mojo.init.InitProjectMojo.createEmptyChangelogFile;

@Mojo(name = AGGREGATE_COMMAND, defaultPhase = LifecyclePhase.NONE, requiresProject = false)
public class AggregateProjectsMojo extends AbstractMojo {


    @Parameter(property = AGGREGATE_VERSION_MVN_PROPERTY, required = true)
    private String aggregateVersion;

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_MVN_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_CONFIG_FILE, property = CONFIG_FILE_MVN_PROPERTY)
    private String configFile;

    @Parameter(defaultValue = DEFAULT_AGGREGATE_OUTPUT_FILE, property = OUTPUT_FILE_MVN_PROPERTY)
    private String outputFile;

    @Override
    public void execute() {
        getLog().info("Aggregate changelog version for different projects");
        aggregate(aggregateVersion, inputDir, configFile, outputFile);
        getLog().info("Aggregate successful");
    }

    private void aggregate(String aggregateVersion, String yamlFilesDirectory, String configFile, String outputFile) {
        File changelogDirectory = findChangelogDirectory("./" + yamlFilesDirectory, getLog());
        Config config = findConfig("./" + yamlFilesDirectory + "/" + configFile, true, getLog());
        createEmptyChangelogFile(outputFile, getLog());
        AggregateChangelogsVersionsCommand command = AggregateChangelogsVersionsCommand.of(config.getAggregates(), aggregateVersion);
        AggregateChangelogsVersionsService aggregateChangelogsVersionsService = new AggregateChangelogsVersionsService();
        aggregateChangelogsVersionsService.handle(command);
    }

}
