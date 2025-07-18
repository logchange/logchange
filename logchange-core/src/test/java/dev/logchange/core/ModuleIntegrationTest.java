package dev.logchange.core;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.application.changelog.service.generate.GenerateChangelogService;
import dev.logchange.core.application.config.ConfigRepository;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogRepository;
import dev.logchange.core.infrastructure.persistance.changelog.FileVersionSummaryRepository;
import dev.logchange.core.infrastructure.persistance.config.FileConfigRepository;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import dev.logchange.core.infrastructure.query.file.FileReader;
import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class ModuleIntegrationTest {

    private static final Path PATH = Paths.get("src/test/resources/ModuleIntegrationTest/");

    private static final Path changelogPath = PATH.resolve("CHANGELOG.md");

    @BeforeEach
    void init() throws IOException {
        System.out.println(Paths.get(".").toAbsolutePath().normalize());
        Files.createFile(changelogPath);
    }


    @AfterEach
    void cleanup() throws IOException {
        Files.delete(changelogPath);
        ChangelogEntryType.clear();
    }

    @Test
    void shouldMatchExpectedChangelog() throws IOException {
        // given:
        Path changelogInputDir = PATH.resolve("changelog");
        Path changelogOutputFile = PATH.resolve("CHANGELOG.md");
        Path expectedChangelogOutputFile = PATH.resolve("EXPECTED_CHANGELOG.md");
        Path configFile = PATH.resolve(Paths.get("changelog", "logchange-config.yml"));

        FileRepository fr = FileRepository.of(changelogOutputFile.toFile());
        ConfigRepository cr = FileConfigRepository.of(configFile.toFile());
        Config config = cr.find();
        ChangelogRepository repository = new FileChangelogRepository(PATH.toString(), changelogInputDir.toFile(), config, new FileReader(), fr, fr);
        VersionSummaryRepository versionSummaryRepository = new FileVersionSummaryRepository(changelogInputDir.toFile(), config);
        GenerateChangelogUseCase generateChangelogUseCase = new GenerateChangelogService(repository, versionSummaryRepository);
        GenerateChangelogUseCase.GenerateChangelogCommand command = GenerateChangelogUseCase.GenerateChangelogCommand.of();

        // when:
        generateChangelogUseCase.handle(command);

        // then:

        String expectedContent =  FileUtils.fileRead(expectedChangelogOutputFile.toFile());
        String actualContent = FileUtils.fileRead(changelogOutputFile.toFile());
        assertThat(actualContent).isEqualToIgnoringNewLines(expectedContent);
    }
}
