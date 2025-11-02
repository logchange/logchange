package dev.logchange.commands.generate;

import dev.logchange.utils.TestResourcePath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static dev.logchange.utils.TestResourcePath.copyToTempDir;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenerateProjectCommandTest {

    private static final Path PATH = TestResourcePath.getPath(GenerateProjectCommandTest.class);
    private static final String INPUT_DIR = "changelog";
    private static final String UNRELEASED = "unreleased";
    private static final String UNRELEASED_VERSIONED = "unreleased-1.2.3";
    private static final String CONFIG_FILE = "logchange-config.yml";
    private static final String TEST_FILE = "test-entry.yml";
    private static final String OUTPUT_FILE = "CHANGELOG.md";

    @Test
    void onlyUnreleasedDir(@TempDir Path tempDir) throws IOException {
        // given:
        Path VALID_PATH = copyToTempDir(PATH.resolve("onlyUnreleasedDir"), tempDir);
        Path VALID_INPUT_DIR = VALID_PATH.resolve(INPUT_DIR);

        File entry = VALID_INPUT_DIR.resolve(UNRELEASED).resolve(TEST_FILE).toFile();
        File outputFile = VALID_PATH.resolve(OUTPUT_FILE).toFile();
        File expectedChangelog = VALID_PATH.resolve("EXPECTED_CHANGELOG.md").toFile();

        assertTrue(entry.exists());
        assertFalse(outputFile.exists());
        assertTrue(expectedChangelog.exists());

        // when:
        GenerateProjectCommand.of(VALID_PATH.toString(), INPUT_DIR, VALID_PATH.resolve(OUTPUT_FILE).toString(), CONFIG_FILE).execute(false);

        // then:
        assertTrue(outputFile.exists());
        String expectedContent = new String(Files.readAllBytes(expectedChangelog.toPath()), StandardCharsets.UTF_8);
        String actualContent = new String(Files.readAllBytes(outputFile.toPath()), StandardCharsets.UTF_8);
        assertThat(actualContent).isEqualToIgnoringWhitespace(expectedContent);
    }

    @Test
    void missingReleaseDateTxt(@TempDir Path tempDir) throws IOException {
        // given:
        Path VALID_PATH = copyToTempDir(PATH.resolve("missingReleaseDateTxt"), tempDir);
        Path VALID_INPUT_DIR = VALID_PATH.resolve(INPUT_DIR);

        File entry = VALID_INPUT_DIR.resolve(UNRELEASED).resolve(TEST_FILE).toFile();
        File outputFile = VALID_PATH.resolve(OUTPUT_FILE).toFile();
        File expectedChangelog = VALID_PATH.resolve("EXPECTED_CHANGELOG.md").toFile();

        assertTrue(entry.exists());
        assertFalse(outputFile.exists());
        assertTrue(expectedChangelog.exists());

        // when:
        GenerateProjectCommand.of(VALID_PATH.toString(), INPUT_DIR, VALID_PATH.resolve(OUTPUT_FILE).toString(), CONFIG_FILE).execute(false);

        // then:
        assertTrue(outputFile.exists());
        String expectedContent = new String(Files.readAllBytes(expectedChangelog.toPath()), StandardCharsets.UTF_8);
        String actualContent = new String(Files.readAllBytes(outputFile.toPath()), StandardCharsets.UTF_8);
        assertThat(actualContent).isEqualToIgnoringWhitespace(expectedContent);
    }

    @Test
    void versionedUnreleasedDir(@TempDir Path tempDir) throws IOException {
        // given:
        Path VALID_PATH = copyToTempDir(PATH.resolve("versionedUnreleasedDir"), tempDir);
        Path VALID_INPUT_DIR = VALID_PATH.resolve(INPUT_DIR);

        File entry = VALID_INPUT_DIR.resolve(UNRELEASED_VERSIONED).resolve(TEST_FILE).toFile();
        File outputFile = VALID_PATH.resolve(OUTPUT_FILE).toFile();
        File expectedChangelog = VALID_PATH.resolve("EXPECTED_CHANGELOG.md").toFile();

        assertTrue(entry.exists());
        assertFalse(outputFile.exists());
        assertTrue(expectedChangelog.exists());

        // when:
        GenerateProjectCommand.of(VALID_PATH.toString(), INPUT_DIR, VALID_PATH.resolve(OUTPUT_FILE).toString(), CONFIG_FILE).execute(false);

        // then:
        assertTrue(outputFile.exists());
        String expectedContent = new String(Files.readAllBytes(expectedChangelog.toPath()), StandardCharsets.UTF_8);
        String actualContent = new String(Files.readAllBytes(outputFile.toPath()), StandardCharsets.UTF_8);
        assertThat(actualContent).isEqualToIgnoringWhitespace(expectedContent);
    }
}
