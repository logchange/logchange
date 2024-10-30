package dev.logchange.maven_plugin.mojo;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.application.changelog.service.generate.GenerateChangelogService;
import dev.logchange.core.application.changelog.service.generate.GenerateChangelogXMLService;
import dev.logchange.core.application.config.ConfigRepository;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.domain.changelog.command.ValidateChangelogUseCase;
import dev.logchange.core.domain.changelog.command.ValidateChangelogUseCase.ValidateChangelogCommand;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogRepository;
import dev.logchange.core.infrastructure.persistance.changelog.FileVersionSummaryRepository;
import dev.logchange.core.infrastructure.persistance.config.FileConfigRepository;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import dev.logchange.core.infrastructure.query.file.FileQuery;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

import static dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase.GenerateChangelogCommand;
import static dev.logchange.maven_plugin.Constants.*;

@Mojo(name = GENERATE_COMMAND, defaultPhase = LifecyclePhase.NONE)
public class GenerateChangelogMojo extends AbstractMojo {

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_MVN_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_OUTPUT_FILE, property = OUTPUT_FILE_MVN_PROPERTY)
    private String outputFile;

    @Parameter(defaultValue = DEFAULT_CONFIG_FILE, property = CONFIG_FILE_MVN_PROPERTY)
    private String configFile;

    @Override
    public void execute() {
        executeGenerate(outputFile, inputDir, configFile, false, "");
    }

    public void validate(String finalChangelogName, String yamlFilesDirectory, String configFile) {
        getLog().info("Started validation of " + yamlFilesDirectory + " and " + configFile);
        File changelogDirectory = findChangelogDirectory("./" + yamlFilesDirectory, getLog());
        Config config = findConfig("./" + yamlFilesDirectory + "/" + configFile, false, getLog());

        FileRepository fr = FileRepository.of(new File(finalChangelogName));
        ChangelogRepository repository = new FileChangelogRepository(changelogDirectory, config, new FileQuery(), fr, fr);
        VersionSummaryRepository versionSummaryRepository = new FileVersionSummaryRepository(changelogDirectory, config);
        ValidateChangelogUseCase validateChangelog = new GenerateChangelogService(repository, versionSummaryRepository);
        ValidateChangelogCommand command = ValidateChangelogCommand.of();

        validateChangelog.handle(command);
        getLog().info("Validation of " + yamlFilesDirectory + " and " + configFile + " successful");
    }

    public void executeGenerate(String finalChangelogName, String yamlFilesDirectory, String configFile, Boolean isXml, String xmlOutputFile) {
        getLog().info("Started generating " + finalChangelogName);
        File changelogDirectory = findChangelogDirectory("./" + yamlFilesDirectory, getLog());

        Config config = findConfig("./" + yamlFilesDirectory + "/" + configFile, false, getLog());

        FileRepository fr = FileRepository.of(new File(finalChangelogName));
        ChangelogRepository repository = new FileChangelogRepository(changelogDirectory, config, new FileQuery(), fr, fr);
        VersionSummaryRepository versionSummaryRepository = new FileVersionSummaryRepository(changelogDirectory, config);
        GenerateChangelogUseCase generateChangelog = new GenerateChangelogService(repository, versionSummaryRepository);
        GenerateChangelogCommand command = GenerateChangelogCommand.of();

        generateChangelog.handle(command);

        getLog().info("Generating " + finalChangelogName + " successful");

        if (isXml) {
            generateChangesXml(xmlOutputFile, changelogDirectory, config, command);
        }
    }

    private void generateChangesXml(String xmlOutputFile, File changelogDirectory, Config config, GenerateChangelogCommand command) {
        FileRepository fr = FileRepository.of(new File(xmlOutputFile));
        ChangelogRepository repository = new FileChangelogRepository(changelogDirectory, config, new FileQuery(), fr, fr);
        GenerateChangelogUseCase generateChangelogXml = new GenerateChangelogXMLService(repository);

        generateChangelogXml.handle(command);
        getLog().info("Generating " + xmlOutputFile + " successful");
    }

    public static File findChangelogDirectory(String directoryPath, Log log) {
        File changelogDir = new File(directoryPath);
        if (!changelogDir.exists()) {
            log.error("There is no " + directoryPath + " directory in this project !!!");
            throw new RuntimeException("No changelog directory");
        }

        if (!changelogDir.isDirectory()) {
            log.error("File " + directoryPath + " is not a directory !!!");
            throw new RuntimeException("File " + directoryPath + " is not a directory");
        }

        return changelogDir;
    }

    public static Config findConfig(String path, boolean required, Log log) {
        File configFile = new File(path);

        if (!configFile.exists()) {
            if (required) {
                String msg = "There is no config file:  " + path + " for this project, but it is required for this action!";
                log.error(msg);
                throw new RuntimeException(msg);
            } else {
                log.info("There is no config file:  " + path + " for this project, using defaults");
                return Config.EMPTY;
            }
        }

        if (configFile.isDirectory()) {
            String msg = "File " + path + " is a directory !!!";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        ConfigRepository configRepository = new FileConfigRepository(configFile);
        return configRepository.find();
    }
}