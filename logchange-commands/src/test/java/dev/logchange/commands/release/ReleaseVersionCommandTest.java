package dev.logchange.commands.release;

import dev.logchange.core.format.release_date.ReleaseDateOption;
import dev.logchange.utils.TestResourcePath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static dev.logchange.utils.TestResourcePath.copyToTempDir;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReleaseVersionCommandTest {

    private static final Path PATH = TestResourcePath.getPath(ReleaseVersionCommandTest.class);
    private static final String INPUT_DIR = "changelog";
    private static final String UNRELEASED = "unreleased";
    private static final String UNRELEASED_WITH_VERSION = "unreleased-1.0.0";

    private static final String CONFIG_FILE = "logchange-config.yml";
    private static final String TEST_FILE = "test-entry.yml";
    private static final String OUTPUT_FILE = "CHANGELOG.md";
    private static final String XML_OUTPUT_FILE = "changes.xml";
    private static final String GIT_KEEP = ".gitkeep";
    private static final String VERSION_TO_RELEASE = "1.0.0";
    private static final String VERSION_DIR = "v1.0.0";
    private static final String VERSION_SUMMARY_FILE = "version-summary.md";
    private static final String RELEASE_DATE_FILE = "release-date.txt";

    @Test
    void shouldReleaseVersion(@TempDir Path tempDir) throws IOException {
        // given:
        Path VALID_PATH = copyToTempDir(PATH.resolve("valid"), tempDir);
        Path VALID_INPUT_DIR = VALID_PATH.resolve(INPUT_DIR);
        File changelogDir = VALID_INPUT_DIR.toFile();
        File unreleasedDir = VALID_INPUT_DIR.resolve(UNRELEASED).toFile();
        File config = VALID_INPUT_DIR.resolve(CONFIG_FILE).toFile();
        File entry = VALID_INPUT_DIR.resolve(UNRELEASED).resolve(TEST_FILE).toFile();

        File outputFile = VALID_PATH.resolve(OUTPUT_FILE).toFile();
        File createdGitKeep = VALID_INPUT_DIR.resolve(UNRELEASED).resolve(GIT_KEEP).toFile();

        File versionDirectory = VALID_INPUT_DIR.resolve(VERSION_DIR).toFile();
        File movedEntry = VALID_INPUT_DIR.resolve(VERSION_DIR).resolve(TEST_FILE).toFile();
        File releaseDateFile = VALID_INPUT_DIR.resolve(VERSION_DIR).resolve(RELEASE_DATE_FILE).toFile();
        File versionSummaryFile = VALID_INPUT_DIR.resolve(VERSION_DIR).resolve(VERSION_SUMMARY_FILE).toFile();

        assertTrue(changelogDir.exists());
        assertTrue(unreleasedDir.exists());
        assertTrue(config.exists());
        assertTrue(entry.exists());

        assertFalse(outputFile.exists());
        assertFalse(createdGitKeep.exists());

        assertFalse(versionDirectory.exists());
        assertFalse(movedEntry.exists());
        assertFalse(releaseDateFile.exists());
        assertFalse(versionSummaryFile.exists());

        // when:
        ReleaseVersionCommand.of(
                VALID_PATH.toString(),
                VERSION_TO_RELEASE,
                UNRELEASED,
                INPUT_DIR,
                VALID_PATH.resolve(OUTPUT_FILE).toString(),
                CONFIG_FILE,
                false,
                XML_OUTPUT_FILE,
                ReleaseDateOption.of("")).execute();

        // then:
        assertTrue(outputFile.exists());
        assertTrue(createdGitKeep.exists());
        assertTrue(versionDirectory.exists());
        assertFalse(entry.exists());
        assertTrue(movedEntry.exists());
        assertTrue(releaseDateFile.exists());
        assertTrue(versionSummaryFile.exists());
    }

    @Test
    void shouldReleaseVersionWithoutConfig() throws IOException {
        // given:
        String VALID_PATH = PATH + "/validWithoutConfig/";
        String VALID_INPUT_DIR = VALID_PATH + INPUT_DIR + "/";
        File changelogDir = new File(VALID_INPUT_DIR);
        File unreleasedDir = new File(VALID_INPUT_DIR + UNRELEASED);
        File config = new File(VALID_INPUT_DIR + CONFIG_FILE);
        File entry = new File(VALID_INPUT_DIR + UNRELEASED + "/" + TEST_FILE);

        File outputFile = new File(VALID_PATH + OUTPUT_FILE);
        File createdGitKeep = new File(VALID_INPUT_DIR + UNRELEASED + "/" + GIT_KEEP);

        File versionDirectory = new File(VALID_INPUT_DIR + VERSION_DIR);
        File movedEntry = new File(VALID_INPUT_DIR + VERSION_DIR + "/" + TEST_FILE);
        File releaseDateFile = new File(VALID_INPUT_DIR + VERSION_DIR + "/" + RELEASE_DATE_FILE);
        File versionSummaryFile = new File(VALID_INPUT_DIR + VERSION_DIR + "/" + VERSION_SUMMARY_FILE);

        assertTrue(changelogDir.exists());
        assertTrue(unreleasedDir.exists());
        assertFalse(config.exists());
        assertTrue(entry.exists());

        assertFalse(outputFile.exists());
        assertFalse(createdGitKeep.exists());

        assertFalse(versionDirectory.exists());
        assertFalse(movedEntry.exists());
        assertFalse(releaseDateFile.exists());
        assertFalse(versionSummaryFile.exists());

        // when:
        ReleaseVersionCommand.of(
                VALID_PATH,
                VERSION_TO_RELEASE,
                UNRELEASED,
                INPUT_DIR,
                VALID_PATH + OUTPUT_FILE,
                CONFIG_FILE,
                false,
                XML_OUTPUT_FILE,
                ReleaseDateOption.of(null)).execute();

        // then:
        assertTrue(outputFile.exists());
        assertTrue(createdGitKeep.exists());
        assertTrue(versionDirectory.exists());
        assertFalse(entry.exists());
        assertTrue(movedEntry.exists());
        assertTrue(releaseDateFile.exists());
        assertTrue(versionSummaryFile.exists());
    }

    @Test
    void shouldReleaseVersionWhenUnreleasedDirWithVersion() throws IOException {
        // given:
        String VALID_PATH = PATH + "/unreleasedDirWithVersion/";
        String VALID_INPUT_DIR = VALID_PATH + INPUT_DIR + "/";
        File changelogDir = new File(VALID_INPUT_DIR);
        File unreleasedDir = new File(VALID_INPUT_DIR + UNRELEASED_WITH_VERSION);
        File config = new File(VALID_INPUT_DIR + CONFIG_FILE);
        File entry = new File(VALID_INPUT_DIR + UNRELEASED_WITH_VERSION + "/" + TEST_FILE);

        File outputFile = new File(VALID_PATH + OUTPUT_FILE);
        File createdGitKeep = new File(VALID_INPUT_DIR + UNRELEASED + "/" + GIT_KEEP);

        File versionDirectory = new File(VALID_INPUT_DIR + VERSION_DIR);
        File movedEntry = new File(VALID_INPUT_DIR + VERSION_DIR + "/" + TEST_FILE);
        File releaseDateFile = new File(VALID_INPUT_DIR + VERSION_DIR + "/" + RELEASE_DATE_FILE);
        File versionSummaryFile = new File(VALID_INPUT_DIR + VERSION_DIR + "/" + VERSION_SUMMARY_FILE);

        assertTrue(changelogDir.exists());
        assertTrue(unreleasedDir.exists());
        assertTrue(config.exists());
        assertTrue(entry.exists());

        assertFalse(outputFile.exists());
        assertFalse(createdGitKeep.exists());

        assertFalse(versionDirectory.exists());
        assertFalse(movedEntry.exists());
        assertFalse(releaseDateFile.exists());
        assertFalse(versionSummaryFile.exists());

        // when:
        ReleaseVersionCommand.of(
                VALID_PATH,
                VERSION_TO_RELEASE,
                UNRELEASED,
                INPUT_DIR,
                VALID_PATH + OUTPUT_FILE,
                CONFIG_FILE,
                false,
                XML_OUTPUT_FILE,
                ReleaseDateOption.of("")).execute();

        // then:
        assertTrue(outputFile.exists());
        assertTrue(createdGitKeep.exists());
        assertTrue(versionDirectory.exists());
        assertFalse(entry.exists());
        assertTrue(movedEntry.exists());
        assertTrue(releaseDateFile.exists());
        assertTrue(versionSummaryFile.exists());
    }

    @Test
    void shouldThrowExceptionWhenMissingChangelogDirectory() {
        // given:
        String INVALID_PATH = PATH + "/invalidMissingChangelogDirectory/";
        String INVALID_INPUT_DIR = INVALID_PATH + INPUT_DIR + "/";
        File changelogDir = new File(INVALID_INPUT_DIR);
        File unreleasedDir = new File(INVALID_INPUT_DIR + UNRELEASED);
        File config = new File(INVALID_INPUT_DIR + CONFIG_FILE);
        File entry = new File(INVALID_PATH + TEST_FILE);

        File outputFile = new File(INVALID_PATH + OUTPUT_FILE);
        File createdGitKeep = new File(INVALID_INPUT_DIR + UNRELEASED + "/" + GIT_KEEP);

        File versionDirectory = new File(INVALID_INPUT_DIR + VERSION_DIR);
        File movedEntry = new File(INVALID_INPUT_DIR + VERSION_DIR + "/" + TEST_FILE);
        File releaseDateFile = new File(INVALID_INPUT_DIR + VERSION_DIR + "/" + RELEASE_DATE_FILE);
        File versionSummaryFile = new File(INVALID_INPUT_DIR + VERSION_DIR + "/" + VERSION_SUMMARY_FILE);

        assertFalse(changelogDir.exists());
        assertFalse(unreleasedDir.exists());
        assertFalse(config.exists());
        assertTrue(entry.exists());

        assertFalse(outputFile.exists());
        assertFalse(createdGitKeep.exists());

        assertFalse(versionDirectory.exists());
        assertFalse(movedEntry.exists());
        assertFalse(releaseDateFile.exists());
        assertFalse(versionSummaryFile.exists());

        // when:
        Exception exception = assertThrows(RuntimeException.class, () -> ReleaseVersionCommand.of(
                INVALID_PATH,
                VERSION_TO_RELEASE,
                UNRELEASED,
                INPUT_DIR,
                INVALID_PATH + OUTPUT_FILE,
                CONFIG_FILE,
                false,
                XML_OUTPUT_FILE,
                ReleaseDateOption.of("")).execute());

        // then:
        assertThat(exception.getMessage()).startsWith("THERE IS NO DIRECTORY TO RELEASE FROM!");
        assertFalse(outputFile.exists());
        assertFalse(createdGitKeep.exists());
        assertFalse(versionDirectory.exists());
        assertTrue(entry.exists());
        assertFalse(movedEntry.exists());
        assertFalse(releaseDateFile.exists());
        assertFalse(versionSummaryFile.exists());
    }


    @Test
    void shouldThrowExceptionWhenInvalidYmlFile() {
        // given:
        String expectedError = "Errors found:\n" +
                "Errors in target/test-classes/ReleaseVersionCommandTest/invalidYML/changelog/unreleased/invalid-entry.yml:\n" +
                "\tUnknown property [issue] with value [100]\n" +
                "\n";
        String INVALID_PATH = PATH + "/invalidYML";
        String INVALID_INPUT_DIR = INVALID_PATH + "/" + INPUT_DIR;
        File changelogDir = new File(INVALID_INPUT_DIR);
        File unreleasedDir = new File(INVALID_INPUT_DIR + "/" + UNRELEASED);
        File config = new File(INVALID_INPUT_DIR + "/" + CONFIG_FILE);
        File entry = new File(INVALID_INPUT_DIR + "/" + UNRELEASED + "/invalid-entry.yml");

        File outputFile = new File(INVALID_PATH + "/" + OUTPUT_FILE);
        File createdGitKeep = new File(INVALID_INPUT_DIR + "/" + UNRELEASED + "/" + GIT_KEEP);

        File versionDirectory = new File(INVALID_INPUT_DIR + "/" + VERSION_DIR);
        File movedEntry = new File(INVALID_INPUT_DIR + "/" + VERSION_DIR + "/" + TEST_FILE);
        File releaseDateFile = new File(INVALID_INPUT_DIR + "/" + VERSION_DIR + "/" + RELEASE_DATE_FILE);
        File versionSummaryFile = new File(INVALID_INPUT_DIR + "/" + VERSION_DIR + "/" + VERSION_SUMMARY_FILE);

        assertTrue(changelogDir.exists());
        assertTrue(unreleasedDir.exists());
        assertFalse(config.exists());
        assertTrue(entry.exists());

        assertFalse(outputFile.exists());
        assertFalse(createdGitKeep.exists());

        assertFalse(versionDirectory.exists());
        assertFalse(movedEntry.exists());
        assertFalse(releaseDateFile.exists());
        assertFalse(versionSummaryFile.exists());

        // when:
        Exception exception = assertThrows(RuntimeException.class, () -> ReleaseVersionCommand.of(
                INVALID_PATH,
                VERSION_TO_RELEASE,
                UNRELEASED,
                INPUT_DIR,
                INVALID_PATH + OUTPUT_FILE,
                CONFIG_FILE,
                false,
                XML_OUTPUT_FILE,
                ReleaseDateOption.of("")).execute());

        // then:
        assertThat(exception.getMessage()).isEqualToIgnoringWhitespace(expectedError);
        assertFalse(outputFile.exists());
        assertFalse(createdGitKeep.exists());
        assertFalse(versionDirectory.exists());
        assertTrue(entry.exists());
        assertFalse(movedEntry.exists());
        assertFalse(releaseDateFile.exists());
        assertFalse(versionSummaryFile.exists());
    }

    @Test
    void shouldReleaseVersionWithoutReleaseDateFile_when_OptionNone() throws IOException {
        // given:
        String VALID_PATH = PATH + "/valid/";
        String VALID_INPUT_DIR = VALID_PATH + INPUT_DIR + "/";
        File unreleasedDir = new File(VALID_INPUT_DIR + UNRELEASED);
        File entry = new File(VALID_INPUT_DIR + UNRELEASED + "/" + TEST_FILE);
        File versionDirectory = new File(VALID_INPUT_DIR + VERSION_DIR);
        File movedEntry = new File(VALID_INPUT_DIR + VERSION_DIR + "/" + TEST_FILE);
        File releaseDateFile = new File(VALID_INPUT_DIR + VERSION_DIR + "/" + RELEASE_DATE_FILE);

        assertTrue(unreleasedDir.exists());
        assertTrue(entry.exists());
        assertFalse(versionDirectory.exists());

        // when:
        ReleaseVersionCommand.of(
                VALID_PATH,
                VERSION_TO_RELEASE,
                UNRELEASED,
                INPUT_DIR,
                VALID_PATH + OUTPUT_FILE,
                CONFIG_FILE,
                false,
                XML_OUTPUT_FILE,
                ReleaseDateOption.of("none")).execute();

        // then:
        assertTrue(versionDirectory.exists());
        assertTrue(movedEntry.exists());
        assertFalse(releaseDateFile.exists());
    }

    @Test
    void shouldReleaseVersionWithSpecificReleaseDate_when_OptionProvided() throws IOException {
        // given:
        String VALID_PATH = PATH + "/valid/";
        String VALID_INPUT_DIR = VALID_PATH + INPUT_DIR + "/";
        File unreleasedDir = new File(VALID_INPUT_DIR + UNRELEASED);
        File entry = new File(VALID_INPUT_DIR + UNRELEASED + "/" + TEST_FILE);
        File versionDirectory = new File(VALID_INPUT_DIR + VERSION_DIR);
        File movedEntry = new File(VALID_INPUT_DIR + VERSION_DIR + "/" + TEST_FILE);
        File releaseDateFile = new File(VALID_INPUT_DIR + VERSION_DIR + "/" + RELEASE_DATE_FILE);

        assertTrue(unreleasedDir.exists());
        assertTrue(entry.exists());
        assertFalse(versionDirectory.exists());

        // when:
        String date = "2025-11-30";
        ReleaseVersionCommand.of(
                VALID_PATH,
                VERSION_TO_RELEASE,
                UNRELEASED,
                INPUT_DIR,
                VALID_PATH + OUTPUT_FILE,
                CONFIG_FILE,
                false,
                XML_OUTPUT_FILE,
                ReleaseDateOption.of(date)).execute();

        // then:
        assertTrue(versionDirectory.exists());
        assertTrue(movedEntry.exists());
        assertTrue(releaseDateFile.exists());
        assertThat(Files.readAllLines(releaseDateFile.toPath())).isEqualTo(Collections.singletonList(date));
    }
}