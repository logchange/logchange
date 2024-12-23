package dev.logchange.commands.aggregate;

import dev.logchange.core.application.changelog.repository.AggregatedVersionQuery;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.application.changelog.service.aggregate.AggregateProjectsVersionService;
import dev.logchange.core.application.config.ConfigFile;
import dev.logchange.core.application.file.Dir;
import dev.logchange.core.application.file.query.TarGzQuery;
import dev.logchange.core.domain.changelog.command.AggregateProjectsVersionUseCase;
import dev.logchange.core.domain.changelog.command.AggregateProjectsVersionUseCase.AggregateChangelogsVersionsCommand;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.changelog.FileVersionSummaryRepository;
import dev.logchange.core.infrastructure.query.changelog.FileAggregatedVersionFinder;
import dev.logchange.core.infrastructure.query.file.FileReader;
import dev.logchange.core.infrastructure.query.file.TarGzExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log
@RequiredArgsConstructor(staticName = "of")
public class AggregateVersionCommand {

    private final String aggregateVersion;
    private final String inputDir;
    private final String configFile;

    public void execute() {
        log.info("Started aggregating " + aggregateVersion + " version");
        File changelogDirectory = Dir.find("./" + inputDir);

        String configPath = "./" + inputDir + "/" + configFile;
        Config config = getConfig(configPath);
        AggregateChangelogsVersionsCommand command = AggregateChangelogsVersionsCommand.of(config.getAggregates(), aggregateVersion);

        createVersionDir(changelogDirectory.getPath(), command.getVersion().getDirName());
        Path tmpDir = Dir.createTmp();

        TarGzQuery tarGzQuery = new TarGzExtractor(tmpDir);
        VersionSummaryRepository vsr = new FileVersionSummaryRepository(changelogDirectory, config);
        AggregatedVersionQuery avr = new FileAggregatedVersionFinder(command.getVersion(), new FileReader());
        AggregateProjectsVersionUseCase aggregateChangelogsVersionsService = new AggregateProjectsVersionService(avr, vsr, tarGzQuery);

        try {
            aggregateChangelogsVersionsService.handle(command);
        } finally {
            log.info("Deleting tmp directory");
            Dir.delete(tmpDir);
        }
        log.info("Aggregating " + aggregateVersion + " version successful");
    }

    private static Config getConfig(String configPath) {
        return ConfigFile.find(configPath).orElseThrow(() -> {
            String msg = String.format("There is no config file: %s for this project, but it is required for this action!", configPath);
            log.severe(msg);
            return new RuntimeException(msg);
        });
    }

    public void createVersionDir(String inputDir, String versionDir) {
        Dir.of(Paths.get(inputDir, versionDir)).create();
    }
}
