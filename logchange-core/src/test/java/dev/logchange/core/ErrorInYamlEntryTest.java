package dev.logchange.core;

import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntry;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntryException;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogInvalidConfigValuesException;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorInYamlEntryTest {

    private static final Path PATH = TestResourcePath.getPath(ErrorInYamlEntryTest.class);

    @Test
    void shouldFailWithPointerToError() {
        // given:
        File changelogTestConfigFile = PATH.resolve("changelog/unreleased/test-task.yml").toFile();

        ArrayList<Pair<String, String>> invalidParams = new ArrayList<>();
        invalidParams
                .add(Pair.of("authorss", "[{nick=marwin1991, url=https://github.com/marwin1991}]"));
        invalidParams.add(Pair.of("merge_requestss", "[1, 2]"));

        YMLChangelogEntryException expectedError = new YMLChangelogEntryException(
                changelogTestConfigFile.getPath(), invalidParams);

        // when-then:
        YMLChangelogEntryException actualError =
                Assertions.assertThrows(YMLChangelogEntryException.class,
                        () -> YMLChangelogEntry.of(
                                Files.newInputStream(changelogTestConfigFile.toPath()),
                                changelogTestConfigFile.getPath()));

        assertThat(actualError.path).isEqualTo(expectedError.path);
        assertThat(actualError.getMessage()).isEqualTo(expectedError.getMessage());
    }

    @Test
    void shouldNotMatchInvalidFieldValue() {
        // given:
        File changelogTestConfigFile = PATH.resolve("changelog/unreleased/test-task2.yml").toFile();

        YMLChangelogInvalidConfigValuesException expectedError =
                new YMLChangelogInvalidConfigValuesException(changelogTestConfigFile.getPath(),
                        new ArrayList<String>() {
                            {
                                add("Cannot match YMLChangelogEntryType for string: fix - Available types: [added, changed, deprecated, removed, fixed, security, dependency_update, other].");
                            }
                        });

        // when-then:
        YMLChangelogInvalidConfigValuesException actualError =
                Assertions.assertThrows(YMLChangelogInvalidConfigValuesException.class,
                        () -> YMLChangelogEntry.of(
                                Files.newInputStream(changelogTestConfigFile.toPath()),
                                changelogTestConfigFile.getPath()));

        assertThat(actualError.path).isEqualTo(expectedError.path);
        assertThat(actualError.getMessage()).isEqualTo(expectedError.getMessage());
    }
}
