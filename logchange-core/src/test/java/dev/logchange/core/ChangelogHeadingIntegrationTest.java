package dev.logchange.core;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.application.changelog.service.generate.GenerateChangelogService;
import dev.logchange.core.application.config.ConfigRepository;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
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

import static org.assertj.core.api.Assertions.assertThat;

public class ChangelogHeadingIntegrationTest {

    private static final String PATH = "src/test/resources/ChangelogHeadingIntegrationTest/";

    @BeforeEach
    void init() throws IOException {
        new File(PATH + "CHANGELOG.md").createNewFile();
    }


    @AfterEach
    void cleanup() {
        new File(PATH + "CHANGELOG.md").delete();
    }

    @Test
    void shouldMatchExpectedChangelog() throws IOException {
        //given:
        File changelogInputDir = new File(PATH + "changelog");
        File changelogOutputFile = new File(PATH + "CHANGELOG.md");
        File configFile = new File(PATH + "changelog/logchange-config.yml");
        File expectedChangelogOutputFile = new File(PATH + "EXPECTED_CHANGELOG.md");

        ConfigRepository configRepository = new FileConfigRepository(configFile);
        Config config = configRepository.find();

        FileRepository fr = FileRepository.of(changelogOutputFile);
        ChangelogRepository repository = new FileChangelogRepository(changelogInputDir, config, new FileReader(), fr, fr);
        VersionSummaryRepository versionSummaryRepository = new FileVersionSummaryRepository(changelogInputDir, config);
        GenerateChangelogUseCase generateChangelogUseCase = new GenerateChangelogService(repository, versionSummaryRepository);
        GenerateChangelogUseCase.GenerateChangelogCommand command = GenerateChangelogUseCase.GenerateChangelogCommand.of();

        //when:
        generateChangelogUseCase.handle(command);

        //then:
        String expectedContent = FileUtils.fileRead(expectedChangelogOutputFile, "UTF-8");
        String actualContent = FileUtils.fileRead(changelogOutputFile, "UTF-8");
        assertThat(actualContent).isEqualToIgnoringNewLines(expectedContent);
    }
}
