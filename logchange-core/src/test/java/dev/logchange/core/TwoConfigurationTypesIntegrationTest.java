package dev.logchange.core;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.application.changelog.service.generate.GenerateChangelogService;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.domain.config.model.Heading;
import dev.logchange.core.domain.config.model.aggregate.Aggregates;
import dev.logchange.core.domain.config.model.labels.*;
import dev.logchange.core.domain.config.model.templates.Templates;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogRepository;
import dev.logchange.core.infrastructure.persistance.changelog.FileVersionSummaryRepository;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import dev.logchange.core.infrastructure.query.file.FileReader;
import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TwoConfigurationTypesIntegrationTest {

    private static final String PATH = "src/test/resources/TwoConfigurationTypesIntegrationTest/";

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

        FileRepository fr = FileRepository.of(changelogOutputFile);
        ChangelogRepository repository = new FileChangelogRepository(changelogInputDir, Config.EMPTY, new FileReader(), fr, fr);
        VersionSummaryRepository versionSummaryRepository = new FileVersionSummaryRepository(changelogInputDir, Config.EMPTY);
        GenerateChangelogUseCase generateChangelogUseCase = new GenerateChangelogService(repository, versionSummaryRepository);
        GenerateChangelogUseCase.GenerateChangelogCommand command = GenerateChangelogUseCase.GenerateChangelogCommand.of();

        //when:
        generateChangelogUseCase.handle(command);

        //then:
        String expectedContent = FileUtils.fileRead(expectedChangelogOutputFile, "UTF-8");
        String actualContent = FileUtils.fileRead(changelogOutputFile, "UTF-8");
        assertThat(actualContent).isEqualToIgnoringNewLines(expectedContent);
    }

    @Test
    void shouldMatchExpectedChangelogWithCustomConfiguration() throws IOException {
        //given:
        File changelogInputDir = new File(PATH + "changelog");
        File changelogOutputFile = new File(PATH + "CHANGELOG.md");
        File expectedChangelogOutputFile = new File(PATH + "EXPECTED_CUSTOM_CHANGELOG.md");
        Config config = prepareCustomConfig();

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

    private static Config prepareCustomConfig() {
        Map<String, String> entryTypesLabels = new HashMap<>();

        entryTypesLabels.put(DEFAULT_ENTRY_TYPE_ADDED, "A");
        entryTypesLabels.put(DEFAULT_ENTRY_TYPE_CHANGED, "C");
        entryTypesLabels.put(DEFAULT_ENTRY_TYPE_DEPRECATED, "D");
        entryTypesLabels.put(DEFAULT_ENTRY_TYPE_REMOVED, "R");
        entryTypesLabels.put(DEFAULT_ENTRY_TYPE_FIXED, "F");
        entryTypesLabels.put(DEFAULT_ENTRY_TYPE_SECURITY, "S");
        entryTypesLabels.put(DEFAULT_ENTRY_TYPE_DEPENDENCY_UPDATE, "DU");
        entryTypesLabels.put(DEFAULT_ENTRY_TYPE_OTHER, "O");

        TypesLabels typesLabels = TypesLabels.builder()
                .numberOfChanges(NumberOfChangesLabels.builder()
                        .singular("modification")
                        .plural("modifications")
                        .build())
                .entryTypesLabels(entryTypesLabels)
                .build();

        ConfigurationLabels configurations = ConfigurationLabels.builder()
                .heading("Configuration modifications")
                .type("Kind")
                .actions(ConfigurationActionLabels.builder()
                        .add("Introduced")
                        .update("Adjusted")
                        .delete("Removed")
                        .build())
                .withDefaultValue("with predefined value")
                .description("Summary")
                .build();


        return Config.builder()
                .heading(Heading.of("My heading"))
                .labels(Labels.builder()
                        .unreleased("Not released")
                        .importantNotes("Important remarks")
                        .types(typesLabels)
                        .configuration(configurations)
                        .build())
                .templates(Templates.builder().entryFormat("${title} ${issues} ${merge_requests}").build())
                .aggregates(Aggregates.EMPTY)
                .build();
    }
}
