package dev.logchange.commands.archive;

import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArchiveVersionCommandTest {

    private static final String EXPECTED_ARCHIVE_MD = "EXPECTED_ARCHIVE.md";
    private static final String PATH = "src/test/resources/ArchiveVersionCommandTest";
    private static final String INPUT_DIR = "changelog";
    private static final String CONFIG_FILE = "logchange-config.yml";
    private static final String ARCHIVE_FILE = "archive.md";

    @Test
    void whenArchiveNotExists() throws IOException {
        // given:
        String TEST_PATH = PATH + "/whenArchiveNotExists";
        File unreleasedDir = new File(TEST_PATH + "/" + INPUT_DIR + "/unreleased");
        File versionDir = new File(TEST_PATH + "/" + INPUT_DIR + "/v1.0.1");
        File secondVersionDir = new File(TEST_PATH + "/" + INPUT_DIR + "/v1.0.2");
        File thirdVersionDir = new File(TEST_PATH + "/" + INPUT_DIR + "/v1.0.3");
        File archivedVersionFile = new File(TEST_PATH + "/" + INPUT_DIR + "/archive-1.0.0.md");
        File archiveFile = new File(TEST_PATH + "/" + INPUT_DIR + "/" + ARCHIVE_FILE);
        File expectedArchive = new File(TEST_PATH + "/" + EXPECTED_ARCHIVE_MD);

        assertTrue(unreleasedDir.exists());
        assertTrue(archivedVersionFile.exists());
        assertTrue(versionDir.exists());
        assertTrue(secondVersionDir.exists());
        assertTrue(thirdVersionDir.exists());
        assertFalse(archiveFile.exists());
        assertTrue(expectedArchive.exists());

        // when:
        ArchiveVersionCommand.of(TEST_PATH, INPUT_DIR, "1.0.2", CONFIG_FILE).execute(false);

        // then:
        assertTrue(unreleasedDir.exists());
        assertFalse(archivedVersionFile.exists());
        assertFalse(versionDir.exists());
        assertFalse(secondVersionDir.exists());
        assertTrue(thirdVersionDir.exists());
        assertTrue(archiveFile.exists());
        String expectedContent = FileUtils.fileRead(expectedArchive, "UTF-8");
        String actualContent = FileUtils.fileRead(archiveFile, "UTF-8");
        assertThat(actualContent).isEqualToIgnoringWhitespace(expectedContent);

        // cleanup:
        new File(TEST_PATH + "/" + INPUT_DIR + "/" + ARCHIVE_FILE).delete();
    }

    @Test
    void whenArchiveExists() throws IOException {
        // given:
        String TEST_PATH = PATH + "/whenArchiveExists";
        File unreleasedDir = new File(TEST_PATH + "/" + INPUT_DIR + "/unreleased");
        File versionDir = new File(TEST_PATH + "/" + INPUT_DIR + "/v1.0.1");
        File secondVersionDir = new File(TEST_PATH + "/" + INPUT_DIR + "/v1.0.2");
        File thirdVersionDir = new File(TEST_PATH + "/" + INPUT_DIR + "/v1.0.3");
        File archivedVersionFile = new File(TEST_PATH + "/" + INPUT_DIR + "/archive-1.0.0.md");
        File archiveFile = new File(TEST_PATH + "/" + INPUT_DIR + "/" + ARCHIVE_FILE);
        File expectedArchive = new File(TEST_PATH + "/" + EXPECTED_ARCHIVE_MD);

        assertTrue(unreleasedDir.exists());
        assertTrue(archivedVersionFile.exists());
        assertTrue(versionDir.exists());
        assertTrue(secondVersionDir.exists());
        assertTrue(thirdVersionDir.exists());
        assertTrue(archiveFile.exists());
        assertTrue(expectedArchive.exists());

        // when:
        ArchiveVersionCommand.of(TEST_PATH, INPUT_DIR, "1.0.2", CONFIG_FILE).execute(false);

        // then:
        assertTrue(unreleasedDir.exists());
        assertFalse(archivedVersionFile.exists());
        assertFalse(versionDir.exists());
        assertFalse(secondVersionDir.exists());
        assertTrue(thirdVersionDir.exists());
        assertTrue(archiveFile.exists());
        String expectedContent = FileUtils.fileRead(expectedArchive, "UTF-8");
        String actualContent = FileUtils.fileRead(archiveFile, "UTF-8");
        assertThat(actualContent).isEqualToIgnoringWhitespace(expectedContent);

        // cleanup:
//        new File(TEST_PATH + "/" + INPUT_DIR + "/" + ARCHIVE_FILE).delete();
    }
}