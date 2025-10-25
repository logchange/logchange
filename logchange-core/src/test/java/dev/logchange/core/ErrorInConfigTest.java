package dev.logchange.core;

import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntry;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntryConfigException;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogInvalidConfigValuesException;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorInConfigTest {

    private static final String PATH = "src/test/resources/ErrorInConfigTest/";

    @BeforeEach
    void init() throws IOException {
        new File(PATH + "CHANGELOG.md").createNewFile();
    }

    @AfterEach
    void cleanup() {
        new File(PATH + "CHANGELOG.md").delete();
    }

    @Test
    void shouldFailWithPointerToError() throws IOException {
        // given:
        File changelogTestConfigFile = new File(PATH + "changelog/unreleased/test-task.yml");

        ArrayList<Pair<String, String>> invalidParams = new ArrayList<>();
        invalidParams
                .add(Pair.of("authorss", "[{nick=marwin1991, url=https://github.com/marwin1991}]"));
        invalidParams.add(Pair.of("merge_requestss", "[1, 2]"));

        YMLChangelogEntryConfigException expectedError = new YMLChangelogEntryConfigException(
                changelogTestConfigFile.getPath(), invalidParams);

        // then:
        YMLChangelogEntryConfigException actualError =
                Assertions.assertThrows(YMLChangelogEntryConfigException.class,
                        () -> YMLChangelogEntry.of(
                                Files.newInputStream(changelogTestConfigFile.toPath()),
                                changelogTestConfigFile.getPath()));

        assertThat(actualError.path).isEqualTo(expectedError.path);
        assertThat(actualError.getMessage()).isEqualTo(expectedError.getMessage());
    }

    @Test
    void shouldNotMatchInvalidFieldValue() throws IOException {
        // given:
        File changelogTestConfigFile = new File(PATH + "changelog/unreleased/test-task2.yml");

        YMLChangelogInvalidConfigValuesException expectedError =
                new YMLChangelogInvalidConfigValuesException(changelogTestConfigFile.getPath(),
                        new ArrayList<String>() {
                            {
                                add("Cannot match YMLChangelogEntryType for string: fix - Available types: [added, changed, deprecated, removed, fixed, security, dependency_update, other].");
                            }
                        });

        // then:
        YMLChangelogInvalidConfigValuesException actualError =
                Assertions.assertThrows(YMLChangelogInvalidConfigValuesException.class,
                        () -> YMLChangelogEntry.of(
                                Files.newInputStream(changelogTestConfigFile.toPath()),
                                changelogTestConfigFile.getPath()));

        assertThat(actualError.path).isEqualTo(expectedError.path);
        assertThat(actualError.getMessage()).isEqualTo(expectedError.getMessage());
    }
    
    @Test
    void shouldFailWhenLinksIsNotList() throws IOException {
        // given:
        File changelogTestConfigFile = new File(PATH + "changelog/unreleased/test-task-links-invalid.yml");

        // then: deserialization should report invalid structure (e.g., links expects a list)
        YMLChangelogInvalidConfigValuesException actualError =
                Assertions.assertThrows(YMLChangelogInvalidConfigValuesException.class,
                        () -> YMLChangelogEntry.of(
                                Files.newInputStream(changelogTestConfigFile.toPath()),
                                changelogTestConfigFile.getPath()));

        assertThat(actualError.path).isEqualTo(changelogTestConfigFile.getPath());
        assertThat(actualError.getMessage()).contains("Errors in ");
    }
}
