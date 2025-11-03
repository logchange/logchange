package dev.logchange.commands.aggregate;

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

class AggregateVersionCommandTest {

    private static final Path PATH = TestResourcePath.getPath(AggregateVersionCommandTest.class);
    private static final String INPUT_DIR = "changelog";
    private static final String CONFIG_FILE = "logchange-config.yml";
    private static final String VERSION_DIR = "v0.4.0";
    private static final String VERSION_SUMMARY_FILE = "version-summary.md";

    @Test
    void shouldAggregateVersion(@TempDir Path tempDir) throws IOException {
        // given:
        Path VALID_PATH = copyToTempDir(PATH, tempDir);
        Path VALID_INPUT_DIR = VALID_PATH.resolve(INPUT_DIR);

        File versionDir = VALID_INPUT_DIR.resolve(VERSION_DIR).toFile();
        File versionSummary = VALID_INPUT_DIR.resolve(VERSION_DIR).resolve(VERSION_SUMMARY_FILE).toFile();
        File expectedSummary = VALID_PATH.resolve("EXPECTED-SUMMARY.md").toFile();
        assertFalse(versionDir.exists());
        assertFalse(versionSummary.exists());
        assertTrue(expectedSummary.exists());

        // when:
        AggregateVersionCommand.of(VALID_PATH.toString(), "0.4.0", INPUT_DIR, CONFIG_FILE).execute();

        // then:
        assertTrue(versionDir.exists());
        assertTrue(versionSummary.exists());
        String expectedContent = new String(Files.readAllBytes(expectedSummary.toPath()), StandardCharsets.UTF_8);
        String actualContent = new String(Files.readAllBytes(versionSummary.toPath()), StandardCharsets.UTF_8);
        assertThat(actualContent).isEqualToIgnoringWhitespace(expectedContent);
    }
}