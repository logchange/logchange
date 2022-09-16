package dev.logchange.maven_plugin.mojo;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.application.changelog.service.generate.GenerateChangelogService;
import dev.logchange.core.application.config.ConfigRepository;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogRepository;
import dev.logchange.core.infrastructure.persistance.changelog.FileVersionSummaryRepository;
import dev.logchange.core.infrastructure.persistance.config.FileConfigRepository;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

import static dev.logchange.maven_plugin.Constants.*;

@Mojo(name = GENERATE_COMMAND, defaultPhase = LifecyclePhase.NONE)
public class GenerateChangelogMojo extends AbstractMojo {

    @Parameter(defaultValue = "", property = HEADING_MVN_PROPERTY)
    private String heading;

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_MVN_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_OUTPUT_FILE, property = OUTPUT_FILE_MVN_PROPERTY)
    private String outputFile;

    @Parameter(defaultValue = DEFAULT_CONFIG_FILE, property = CONFIG_FILE_MVN_PROPERTY)
    private String configFile;

    @Override
    public void execute() {
        executeGenerate(outputFile, inputDir, configFile);
    }

    public void executeGenerate(String finalChangelogName, String yamlFilesDirectory, String configFile) {
        getLog().info("Started generating " + finalChangelogName);
        File changelogDirectory = findChangelogDirectory("./" + yamlFilesDirectory);

        Config config = findConfig("./" + yamlFilesDirectory + "/" + configFile);

        ChangelogRepository repository = new FileChangelogRepository(changelogDirectory, new File(finalChangelogName), config);
        VersionSummaryRepository versionSummaryRepository = new FileVersionSummaryRepository(changelogDirectory, config);
        GenerateChangelogUseCase generateChangelog = new GenerateChangelogService(repository, versionSummaryRepository);
        GenerateChangelogUseCase.GenerateChangelogCommand command = GenerateChangelogUseCase.GenerateChangelogCommand.of(heading);

        generateChangelog.handle(command);

        getLog().info("Generating " + finalChangelogName + " successful");
    }

    private File findChangelogDirectory(String directoryPath) {
        File changelogDir = new File(directoryPath);
        if (!changelogDir.exists()) {
            getLog().error("There is no " + directoryPath + " directory in this project !!!");
            throw new RuntimeException("No changelog directory");
        }

        if (!changelogDir.isDirectory()) {
            getLog().error("File " + directoryPath + " is not a directory !!!");
            throw new RuntimeException("File " + directoryPath + " is not a directory");
        }

        return changelogDir;
    }

    private Config findConfig(String path) {
        File configFile = new File(path);

        if (!configFile.exists()) {
            getLog().info("There is no config file:  " + path + " for this project, using defaults");
            return Config.EMPTY;
        }

        if (configFile.isDirectory()) {
            getLog().error("File " + path + " is a directory !!!");
            throw new RuntimeException("File " + path + " is a directory !!!");
        }

        ConfigRepository configRepository = new FileConfigRepository(configFile);
        return configRepository.find();
    }
}