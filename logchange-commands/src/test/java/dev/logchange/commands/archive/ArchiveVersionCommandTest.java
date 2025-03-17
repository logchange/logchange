package dev.logchange.commands.archive;

import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArchiveVersionCommandTest {

    private static final String PATH = "src/test/resources/ArchiveVersionCommandTest";
    private static final String INPUT_DIR = "changelog";
    private static final String CONFIG_FILE = "logchange-config.yml";
    private static final String ARCHIVE_FILE = "archive.md";

    @Test
    void onlyUnreleasedDir() throws IOException {
        // given:
        String TEST_PATH = PATH + "/success";
        File versionDir = new File(TEST_PATH + "/" + INPUT_DIR + "/v1.0.1");
        File secondVersionDir = new File(TEST_PATH + "/" + INPUT_DIR + "/v1.0.2");
        File archiveVersionFile = new File(TEST_PATH + "/" + INPUT_DIR + "/archive-1.0.0.md");
        File archiveFile = new File(TEST_PATH + "/" + INPUT_DIR + "/" + ARCHIVE_FILE);
        File expectedArchive = new File(TEST_PATH + "/" + "EXPECTED_ARCHIVE.md");

        assertTrue(archiveVersionFile.exists());
        assertTrue(versionDir.exists());
        assertTrue(secondVersionDir.exists());
        assertFalse(archiveFile.exists());
        assertTrue(expectedArchive.exists());

        // when:
        ArchiveVersionCommand.of(TEST_PATH, INPUT_DIR, "1.0.2", CONFIG_FILE).execute(false);

        // then:
        assertTrue(archiveFile.exists());
        String expectedContent = FileUtils.fileRead(expectedArchive, "UTF-8");
        String actualContent = FileUtils.fileRead(archiveFile, "UTF-8");
        assertThat(actualContent).isEqualToIgnoringWhitespace(expectedContent);

        // cleanup:
//        new File(TEST_PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + "version-summary.md").delete();
//        new File(TEST_PATH + "/" + OUTPUT_FILE).delete();
    }
}