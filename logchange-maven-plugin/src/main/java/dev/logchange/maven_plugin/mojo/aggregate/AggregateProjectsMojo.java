package dev.logchange.maven_plugin.mojo.aggregate;

import dev.logchange.core.application.changelog.repository.AggregatedVersionQuery;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.application.changelog.service.aggregate.AggregateProjectsVersionService;
import dev.logchange.core.domain.changelog.command.AggregateProjectsVersionUseCase;
import dev.logchange.core.domain.changelog.command.AggregateProjectsVersionUseCase.AggregateChangelogsVersionsCommand;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.changelog.FileVersionSummaryRepository;
import dev.logchange.core.infrastructure.query.changelog.FileAggregatedVersionQuery;
import dev.logchange.core.infrastructure.query.file.FileQuery;
import dev.logchange.maven_plugin.util.Dir;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

import static dev.logchange.maven_plugin.Constants.*;
import static dev.logchange.maven_plugin.mojo.GenerateChangelogMojo.findChangelogDirectory;
import static dev.logchange.maven_plugin.mojo.GenerateChangelogMojo.findConfig;

@Mojo(name = AGGREGATE_COMMAND, defaultPhase = LifecyclePhase.NONE, requiresProject = false)
public class AggregateProjectsMojo extends AbstractMojo {

    @Parameter(property = AGGREGATE_VERSION_MVN_PROPERTY, required = true)
    private String aggregateVersion;

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_MVN_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_CONFIG_FILE, property = CONFIG_FILE_MVN_PROPERTY)
    private String configFile;

    @Override
    public void execute() {
        getLog().info("Aggregate changelog version for different projects");
        aggregate(aggregateVersion, inputDir, configFile);
        getLog().info("Aggregate successful");
    }

    private void aggregate(String aggregateVersion, String yamlFilesDirectory, String configFile) {
        File changelogDirectory = findChangelogDirectory("./" + yamlFilesDirectory, getLog());
        Config config = findConfig("./" + yamlFilesDirectory + "/" + configFile, true, getLog());
        AggregateChangelogsVersionsCommand command = AggregateChangelogsVersionsCommand.of(config.getAggregates(), aggregateVersion);

        createVersionDir(changelogDirectory.getPath(), command.getVersion().getDirName());

        VersionSummaryRepository vsr = new FileVersionSummaryRepository(changelogDirectory, config);
        AggregatedVersionQuery avr = new FileAggregatedVersionQuery(command.getVersion(), new FileQuery());
        AggregateProjectsVersionUseCase aggregateChangelogsVersionsService = new AggregateProjectsVersionService(avr, vsr);

        aggregateChangelogsVersionsService.handle(command);
    }

    public void createVersionDir(String inputDir, String versionDir) {
        Dir.of(getLog(), inputDir + "/" + versionDir).create();
    }
}
