package dev.logchange.core;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.application.changelog.service.generate.GenerateChangelogService;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase.GenerateChangelogCommand;
import dev.logchange.core.infrastructure.changelog.FileChangelogRepository;
import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateChangelogIntegrationTest {

    private static final String PATH = "src/test/resources/GenerateChangelogIntegrationTest/";

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
        File expectedChangelogOutputFile = new File(PATH + "EXPECTED_CHANGELOG.md");
        String heading = "";

        ChangelogRepository repository = new FileChangelogRepository(changelogInputDir, changelogOutputFile);
        GenerateChangelogUseCase generateChangelogUseCase = new GenerateChangelogService(repository);
        GenerateChangelogCommand command = GenerateChangelogCommand.of(heading);

        //when:
        generateChangelogUseCase.handle(command);

        //then:
        String expectedContent = FileUtils.fileRead(expectedChangelogOutputFile);
        String actualContent = FileUtils.fileRead(changelogOutputFile);
        assertEquals(expectedContent, actualContent);
    }
}
