package dev.logchange.core;

import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntry;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntryParseException;
import dev.logchange.core.format.yml.changelog.entry.YmlInvalidProperty;
import dev.logchange.utils.TestResourcePath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorInYamlEntryTest {

    private static final Path PATH = TestResourcePath.getPath(ErrorInYamlEntryTest.class);

    @Test
    void shouldFailWithPointerToError() {
        // given:
        File changelogTestConfigFile = PATH.resolve("changelog/unreleased/test-task.yml").toFile();

        ArrayList<YmlInvalidProperty> invalidParams = new ArrayList<>();
        invalidParams
                .add(YmlInvalidProperty.invalidProperty("authorss", "[{nick=marwin1991, url=https://github.com/marwin1991}]"));
        invalidParams.add(YmlInvalidProperty.invalidProperty("merge_requestss", "[1, 2]"));

        YMLChangelogEntryParseException expectedError = new YMLChangelogEntryParseException(
                changelogTestConfigFile.getPath(), invalidParams);

        // when-then:
        YMLChangelogEntryParseException actualError =
                Assertions.assertThrows(YMLChangelogEntryParseException.class,
                        () -> YMLChangelogEntry.of(
                                Files.newInputStream(changelogTestConfigFile.toPath()),
                                changelogTestConfigFile.getPath()));

        assertThat(actualError.getYmlEntryPath()).isEqualTo(expectedError.getYmlEntryPath());
        assertThat(actualError.getMessage()).isEqualTo(expectedError.getMessage());
    }

    @Test
    void shouldNotMatchInvalidFieldValue() {
        // given:
        File changelogTestConfigFile = PATH.resolve("changelog/unreleased/test-task2.yml").toFile();

        YMLChangelogEntryParseException expectedError =
                new YMLChangelogEntryParseException(changelogTestConfigFile.getPath(),
                        Collections.singleton(YmlInvalidProperty.unknownError("?", "Cannot match YMLChangelogEntryType for string: fix - Available types: [added, changed, deprecated, removed, fixed, security, dependency_update, other].")));

        // when-then:
        YMLChangelogEntryParseException actualError =
                Assertions.assertThrows(YMLChangelogEntryParseException.class,
                        () -> YMLChangelogEntry.of(
                                Files.newInputStream(changelogTestConfigFile.toPath()),
                                changelogTestConfigFile.getPath()));

        assertThat(actualError.getYmlEntryPath()).isEqualTo(expectedError.getYmlEntryPath());
        assertThat(actualError.getMessage()).isEqualTo(expectedError.getMessage());
    }
}
