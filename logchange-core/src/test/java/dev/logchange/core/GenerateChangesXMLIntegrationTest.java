package dev.logchange.core;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.application.changelog.service.generate.GenerateChangelogXMLService;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xmlunit.builder.Input;

import java.io.File;
import java.io.IOException;

import static org.xmlunit.assertj3.XmlAssert.assertThat;

public class GenerateChangesXMLIntegrationTest {

    private static final String PATH = "src/test/resources/GenerateChangesXMLIntegrationTest/";

    @BeforeEach
    void init() throws IOException {
        new File(PATH + "Changes.xml").createNewFile();
    }


    @AfterEach
    void cleanup() {
        new File(PATH + "Changes.xml").delete();
    }

    @Test
    void shouldMatchExpectedChangesXml() {
        // given:
        File changelogInputDir = new File(PATH + "changelog");
        File changesXmlOutputFile = new File(PATH + "Changes.xml");
        File expectedChangesXmlOutputFile = new File(PATH + "EXPECTED_Changes.xml");


        ChangelogRepository repository = new FileChangelogRepository(changelogInputDir, changesXmlOutputFile, Config.EMPTY);
        GenerateChangelogUseCase generateChangelogUseCase = new GenerateChangelogXMLService(repository);
        GenerateChangelogUseCase.GenerateChangelogCommand command = GenerateChangelogUseCase.GenerateChangelogCommand.of();

        // when:
        generateChangelogUseCase.handle(command);

        // then:
        assertThat(Input.fromFile(changesXmlOutputFile))
                .and(Input.fromFile(expectedChangesXmlOutputFile))
                .ignoreWhitespace()
                .areIdentical();
    }
}
