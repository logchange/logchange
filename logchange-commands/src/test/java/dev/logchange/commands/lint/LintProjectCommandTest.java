package dev.logchange.commands.lint;

import dev.logchange.utils.TestResourcePath;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LintProjectCommandTest {

    private static final Path PATH = TestResourcePath.getPath(LintProjectCommandTest.class);
    private static final String INPUT_DIR = "changelog";
    private static final String UNRELEASED = "unreleased";
    private static final String CONFIG_FILE = "logchange-config.yml";
    private static final String TEST_FILE = "test-entry.yml";
    private static final String OUTPUT_FILE = "CHANGELOG.md";

    @Test
    void shouldSuccessfullyLintProject() {
        // given:
        Path VALID_PATH = PATH.resolve("valid");
        File changelogDir = VALID_PATH.resolve(INPUT_DIR).toFile();
        File unreleasedDir = new File(VALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED);
        File entry = new File(VALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + TEST_FILE);
        File config = new File(VALID_PATH + "/" + INPUT_DIR + "/" + CONFIG_FILE);
        assertTrue(changelogDir.exists());
        assertTrue(unreleasedDir.exists());
        assertTrue(config.exists());
        assertTrue(entry.exists());

        // when-then:
        assertDoesNotThrow(() -> LintProjectCommand.of(VALID_PATH.toString(), INPUT_DIR, VALID_PATH + OUTPUT_FILE, CONFIG_FILE).validate());
    }

    @Test
    void shouldSuccessfullyLintProjectWithoutConfig() {
        // given:
        Path VALID_PATH = PATH.resolve("validWithoutConfig");
        File changelogDir = new File(VALID_PATH + "/" + INPUT_DIR);
        File unreleasedDir = new File(VALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED);
        File entry = new File(VALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + TEST_FILE);
        File config = new File(VALID_PATH + "/" + INPUT_DIR + "/" + CONFIG_FILE);
        assertTrue(changelogDir.exists());
        assertTrue(unreleasedDir.exists());
        assertFalse(config.exists());
        assertTrue(entry.exists());

        // when-then:
        assertDoesNotThrow(() -> LintProjectCommand.of(VALID_PATH.toString(), INPUT_DIR, VALID_PATH + OUTPUT_FILE, CONFIG_FILE).validate());
    }

    @Test
    void shouldThrowExceptionWhenMissingChangelogDir() {
        // given:
        Path INVALID_PATH = PATH.resolve("invalidMissingChangelogDirectory");
        File changelogDir = new File(INVALID_PATH + "/" + INPUT_DIR);
        File unreleasedDir = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED);
        File entry = new File(INVALID_PATH + "/" + TEST_FILE);
        File config = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + CONFIG_FILE);
        assertFalse(changelogDir.exists());
        assertFalse(unreleasedDir.exists());
        assertFalse(config.exists());
        assertTrue(entry.exists());

        // when-then:
        Exception exception = assertThrows(RuntimeException.class, () -> LintProjectCommand.of(INVALID_PATH.toString(), INPUT_DIR, INVALID_PATH + OUTPUT_FILE, CONFIG_FILE).validate());

        // then:
        assertEquals("There is no " + INVALID_PATH + "/changelog directory in this project !!!", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithInvalidYmlEntry() {
        // given:
        Path INVALID_PATH = PATH.resolve("invalidSyntax");
        String expectedOutput = "Errors found:\n" +
                "Errors in " + INVALID_PATH + "/changelog/unreleased/invalid-entry.yml:\n" +
                "\tUnknown property [issue] with value [100]\n" +
                "\n";
        File changelogDir = new File(INVALID_PATH + "/" + INPUT_DIR);
        File unreleasedDir = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED);
        File entry = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + "invalid-entry.yml");
        File config = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + CONFIG_FILE);
        assertTrue(changelogDir.exists());
        assertTrue(unreleasedDir.exists());
        assertFalse(config.exists());
        assertTrue(entry.exists());

        // when-then:
        Exception exception = assertThrows(RuntimeException.class, () -> LintProjectCommand.of(INVALID_PATH.toString(), INPUT_DIR, INVALID_PATH + OUTPUT_FILE, CONFIG_FILE).validate());

        // then:
        assertThat(exception.getMessage()).isEqualToIgnoringWhitespace(expectedOutput);
    }

    @Test
    void shouldThrowExceptionWithMissingTitleAndTypeInYmlEntry() {
        // given:
        Path INVALID_PATH = PATH.resolve("invalidMissingTitleAndType");

        String expectedOutput = "Errors found:\n" +
                "Errors in " + INVALID_PATH + "/changelog/unreleased/invalid-entry.yml:\n" +
                "\tError for property [title] - Title cannot be blank!\n" +
                "\tMissing type property!\n" +
                "\n";

        File changelogDir = new File(INVALID_PATH + "/" + INPUT_DIR);
        File unreleasedDir = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED);
        File entry = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + "invalid-entry.yml");
        File config = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + CONFIG_FILE);
        assertTrue(changelogDir.exists());
        assertTrue(unreleasedDir.exists());
        assertFalse(config.exists());
        assertTrue(entry.exists());

        // when-then:
        Exception exception = assertThrows(RuntimeException.class, () -> LintProjectCommand.of(INVALID_PATH.toString(), INPUT_DIR, INVALID_PATH + OUTPUT_FILE, CONFIG_FILE).validate());

        // then:
        assertThat(exception.getMessage()).isEqualToIgnoringWhitespace(expectedOutput);
    }

    @Test
    void shouldThrowExceptionWithInvalidLinksInYmlEntry() {
        // given:
        Path INVALID_PATH = PATH.resolve("invalidLinks");
        String expectedOutput = "Errors found:\n" +
                "Errors in " + INVALID_PATH + "/changelog/unreleased/invalid-entry.yml:\n" +
                "\tError for property [links] - Link url cannot be blank! Current value name: TEST#1111 url: null\n" +
                "\tError for property [links] - Link url cannot be blank! Current value name: TEST#1112 url: null\n" +
                "\n";
        File changelogDir = new File(INVALID_PATH + "/" + INPUT_DIR);
        File unreleasedDir = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED);
        File entry = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + "invalid-entry.yml");
        File config = new File(INVALID_PATH + "/" + INPUT_DIR + "/" + CONFIG_FILE);
        assertTrue(changelogDir.exists());
        assertTrue(unreleasedDir.exists());
        assertFalse(config.exists());
        assertTrue(entry.exists());

        // when-then:
        Exception exception = assertThrows(RuntimeException.class, () -> LintProjectCommand.of(INVALID_PATH.toString(), INPUT_DIR, INVALID_PATH + OUTPUT_FILE, CONFIG_FILE).validate());

        // then:
        assertThat(exception.getMessage()).isEqualToIgnoringWhitespace(expectedOutput);
    }
}
