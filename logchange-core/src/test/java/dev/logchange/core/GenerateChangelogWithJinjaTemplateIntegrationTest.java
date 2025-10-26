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
import dev.logchange.utils.TestResourcePath;
import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class GenerateChangelogWithJinjaTemplateIntegrationTest {

    private static final Path PATH = TestResourcePath.getPath(GenerateChangelogWithJinjaTemplateIntegrationTest.class);

    @Test
    void shouldMatchExpectedChangelog() throws IOException {
        // given:
        File changelogInputDir = PATH.resolve("changelog").toFile();
        File changelogOutputFile = PATH.resolve("CHANGELOG.md").toFile();

        File configFile = PATH.resolve("changelog/logchange-config.yml").toFile();
        File expectedOutputFile = PATH.resolve("expected-my-changelog.html").toFile();
        File expectedChangelogMdOutputFile = PATH.resolve("expected-CHANGELOG.md").toFile();

        ConfigRepository configRepository = FileConfigRepository.of(configFile);
        Config config = configRepository.find();

        FileRepository fr = FileRepository.of(changelogOutputFile);
        ChangelogRepository repository = new FileChangelogRepository(PATH.toString(), changelogInputDir, config, new FileReader(), fr, fr);
        VersionSummaryRepository versionSummaryRepository = new FileVersionSummaryRepository(changelogInputDir, config);
        GenerateChangelogUseCase generateChangelogUseCase = new GenerateChangelogService(repository, versionSummaryRepository);
        GenerateChangelogUseCase.GenerateChangelogCommand command = GenerateChangelogUseCase.GenerateChangelogCommand.of();

        // when:
        generateChangelogUseCase.handle(command);
        File myChangelogFile = PATH.resolve("my-changelog.html").toFile();
        File changelogMdFile = PATH.resolve("CHANGELOG.md").toFile();

        // then:
        String expectedContent = FileUtils.fileRead(expectedOutputFile);
        String actualContent = FileUtils.fileRead(myChangelogFile);
        assertThat(actualContent).isEqualTo(expectedContent);

        String expectedContentMd = FileUtils.fileRead(expectedChangelogMdOutputFile);
        String actualContentMd = FileUtils.fileRead(changelogMdFile);
        assertThat(actualContentMd).isEqualTo(expectedContentMd);
    }
}
