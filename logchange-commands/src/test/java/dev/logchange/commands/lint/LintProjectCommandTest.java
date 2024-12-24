package dev.logchange.commands.lint;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LintProjectCommandTest {

    private static final String PATH = "src/test/resources/LintProjectCommandTest/";
    private static final String INPUT_DIR = "changelog";
    private static final String UNRELEASED = "unreleased";
    private static final String CONFIG_FILE = "logchange-config.yml";
    private static final String TEST_FILE = "test-entry.yml";
    private static final String OUTPUT_FILE = "CHANGELOG.md";

    @Test
    void shouldSuccessfullyLintProject() {
        // given:
        String VALID_PATH = PATH + "valid";
        File changelogDir = new File(VALID_PATH + "/" + INPUT_DIR);
        File unreleasedDir = new File(VALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED);
        File entry = new File(VALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + TEST_FILE);
        File config = new File(VALID_PATH + "/" + INPUT_DIR + "/" + CONFIG_FILE);
        assertTrue(changelogDir.exists());
        assertTrue(unreleasedDir.exists());
        assertTrue(config.exists());
        assertTrue(entry.exists());

        // when-then:
        assertDoesNotThrow(() -> LintProjectCommand.of(VALID_PATH, INPUT_DIR, VALID_PATH + OUTPUT_FILE, CONFIG_FILE).validate());
    }

    @Test
    void shouldSuccessfullyLintProjectWithoutConfig() {
        // given:
        String VALID_PATH = PATH + "validWithoutConfig";
        File changelogDir = new File(VALID_PATH + "/" + INPUT_DIR);
        File unreleasedDir = new File(VALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED);
        File entry = new File(VALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + TEST_FILE);
        File config = new File(VALID_PATH + "/" + INPUT_DIR + "/" + CONFIG_FILE);
        assertTrue(changelogDir.exists());
        assertTrue(unreleasedDir.exists());
        assertFalse(config.exists());
        assertTrue(entry.exists());

        // when-then:
        assertDoesNotThrow(() -> LintProjectCommand.of(VALID_PATH, INPUT_DIR, VALID_PATH + OUTPUT_FILE, CONFIG_FILE).validate());
    }

    @Test
    void shouldThrowExceptionWhenMissingChangelogDir() {
        // given:
        String INVALID_PATH = PATH + "invalidMissingChangelogDirectory";
        File changelogDir = new File(INVALID_PATH + "/" + INPUT_DIR);
        File unreleasedDir = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED);
        File entry = new File(INVALID_PATH + "/" + TEST_FILE);
        File config = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + CONFIG_FILE);
        assertFalse(changelogDir.exists());
        assertFalse(unreleasedDir.exists());
        assertFalse(config.exists());
        assertTrue(entry.exists());

        // when-then:
        Exception exception = assertThrows(RuntimeException.class, () -> LintProjectCommand.of(INVALID_PATH, INPUT_DIR, INVALID_PATH + OUTPUT_FILE, CONFIG_FILE).validate());

        // then:
        assertEquals("There is no src/test/resources/LintProjectCommandTest/invalidMissingChangelogDirectory/changelog directory in this project !!!", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithInvalidYmlEntry() {
        // given:
        String expectedOutput = "Errors found:\n" +
                "Errors in src\\test\\resources\\LintProjectCommandTest\\invalidSyntax\\changelog\\unreleased\\invalid-entry.yml:\n" +
                "\tUnknown property [issue] with value [100]\n" +
                "\n";
        String INVALID_PATH = PATH + "invalidSyntax";
        File changelogDir = new File(INVALID_PATH + "/" + INPUT_DIR);
        File unreleasedDir = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED);
        File entry = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + "invalid-entry.yml");
        File config = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + CONFIG_FILE);
        assertTrue(changelogDir.exists());
        assertTrue(unreleasedDir.exists());
        assertFalse(config.exists());
        assertTrue(entry.exists());

        // when-then:
        Exception exception = assertThrows(RuntimeException.class, () -> LintProjectCommand.of(INVALID_PATH, INPUT_DIR, INVALID_PATH + OUTPUT_FILE, CONFIG_FILE).validate());

        // then:
        assertThat(exception.getMessage()).isEqualToIgnoringWhitespace(expectedOutput);
    }
}
