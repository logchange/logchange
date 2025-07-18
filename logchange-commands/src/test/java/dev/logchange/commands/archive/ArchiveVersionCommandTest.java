package dev.logchange.commands.archive;

import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

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

        try {
            // when:
            ArchiveVersionCommand.of(TEST_PATH, INPUT_DIR, "1.0.2", CONFIG_FILE).execute();

            // then:
            assertTrue(unreleasedDir.exists(), "unreleased dir does not exist");
            assertFalse(archivedVersionFile.exists(), "archivedVersionFile does not exist");
            assertFalse(versionDir.exists(), "versionDir does not exist");
            assertFalse(secondVersionDir.exists(), "secondVersionDir does not exist");
            assertTrue(thirdVersionDir.exists(), "thirdVersionDir does not exist");
            assertTrue(archiveFile.exists(), "archive file does not exist");
            String expectedContent = FileUtils.fileRead(expectedArchive, "UTF-8");
            String actualContent = FileUtils.fileRead(archiveFile, "UTF-8");
            assertThat(actualContent).isEqualToIgnoringWhitespace(expectedContent);
        } finally {
            // cleanup:
            Files.delete(archiveFile.toPath());
        }

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

        final byte[] content = Files.readAllBytes(expectedArchive.toPath());
        try {
            // when:
            ArchiveVersionCommand.of(TEST_PATH, INPUT_DIR, "1.0.2", CONFIG_FILE).execute();

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
        } finally {
            Files.write(expectedArchive.toPath(), content);
        }
    }
}
