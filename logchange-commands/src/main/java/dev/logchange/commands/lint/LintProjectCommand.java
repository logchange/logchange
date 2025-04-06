package dev.logchange.commands.lint;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.application.changelog.service.generate.GenerateChangelogService;
import dev.logchange.core.application.config.ConfigFile;
import dev.logchange.core.application.file.Dir;
import dev.logchange.core.domain.changelog.command.ValidateChangelogUseCase;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogRepository;
import dev.logchange.core.infrastructure.persistance.changelog.FileVersionSummaryRepository;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import dev.logchange.core.infrastructure.query.file.FileReader;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;

import java.io.File;

@CustomLog
@RequiredArgsConstructor(staticName = "of")
public class LintProjectCommand {

    private final String rootPath;
    private final String inputDir;
    private final String outputFile;
    private final String configFile;

    public void validate() {
        log.info("Started validation of " + inputDir + " and " + configFile);
        File changelogDirectory = Dir.find(rootPath + "/" + inputDir);
        String configPath = rootPath + "/" + inputDir + "/" + configFile;
        Config config = ConfigFile.find(configPath).orElseGet(() -> {
            log.info("There is no config file:  " + configPath + " for this project, using defaults");
            return Config.EMPTY;
        });

        FileRepository fr = FileRepository.of(new File(outputFile));
        ChangelogRepository repository = new FileChangelogRepository(rootPath, changelogDirectory, config, new FileReader(), fr, fr);
        VersionSummaryRepository versionSummaryRepository = new FileVersionSummaryRepository(changelogDirectory, config);
        ValidateChangelogUseCase validateChangelog = new GenerateChangelogService(repository, versionSummaryRepository);
        ValidateChangelogUseCase.ValidateChangelogCommand command = ValidateChangelogUseCase.ValidateChangelogCommand.of();

        validateChangelog.handle(command);
        log.info("Validation of " + inputDir + " and " + configFile + " successful");
    }
}
