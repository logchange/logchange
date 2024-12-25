package dev.logchange.commands.generate;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.application.changelog.service.generate.GenerateChangelogService;
import dev.logchange.core.application.changelog.service.generate.GenerateChangelogXMLService;
import dev.logchange.core.application.config.ConfigFile;
import dev.logchange.core.application.file.Dir;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogRepository;
import dev.logchange.core.infrastructure.persistance.changelog.FileVersionSummaryRepository;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import dev.logchange.core.infrastructure.query.file.FileReader;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.io.File;

@Log
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(staticName = "of")
public class GenerateProjectCommand {

    private final String rootPath;
    private final String inputDir;
    private final String outputFile;
    private final String configFile;
    private String xmlOutputFile;

    public GenerateProjectCommand withXml(String xmlOutputFile) {
        return new GenerateProjectCommand(rootPath, inputDir, outputFile, configFile, xmlOutputFile);
    }

    public void execute(Boolean xml) {
        log.info("Started generating " + outputFile);
        File changelogDirectory = Dir.find(rootPath + "/" + inputDir);

        String configPath = rootPath + "/" + inputDir + "/" + configFile;
        Config config = ConfigFile.find(configPath).orElseGet(() -> {
            log.info("There is no config file:  " + configPath + " for this project, using defaults");
            return Config.EMPTY;
        });

        FileRepository fr = FileRepository.of(new File(outputFile));
        ChangelogRepository repository = new FileChangelogRepository(changelogDirectory, config, new FileReader(), fr, fr);
        VersionSummaryRepository versionSummaryRepository = new FileVersionSummaryRepository(changelogDirectory, config);
        GenerateChangelogUseCase generateChangelog = new GenerateChangelogService(repository, versionSummaryRepository);
        GenerateChangelogUseCase.GenerateChangelogCommand command = GenerateChangelogUseCase.GenerateChangelogCommand.of();

        generateChangelog.handle(command);

        log.info("Generating " + outputFile + " successful");

        if (xml) {
            generateChangesXml(xmlOutputFile, changelogDirectory, config, command);
        }
    }

    private void generateChangesXml(String xmlOutputFile, File changelogDirectory, Config config, GenerateChangelogUseCase.GenerateChangelogCommand command) {
        log.info("Started generating XML changelog file");
        FileRepository fr = FileRepository.of(new File(xmlOutputFile));
        ChangelogRepository repository = new FileChangelogRepository(changelogDirectory, config, new FileReader(), fr, fr);
        GenerateChangelogUseCase generateChangelogXml = new GenerateChangelogXMLService(repository);
        generateChangelogXml.handle(command);
        log.info("Generating " + xmlOutputFile + " successful");
    }
}
