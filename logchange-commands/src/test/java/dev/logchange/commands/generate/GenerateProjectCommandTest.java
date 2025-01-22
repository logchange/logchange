package dev.logchange.commands.generate;

import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenerateProjectCommandTest {

    private static final String PATH = "src/test/resources/GenerateProjectCommandTest";
    private static final String INPUT_DIR = "changelog";
    private static final String UNRELEASED = "unreleased/";
    private static final String CONFIG_FILE = "logchange-config.yml";
    private static final String TEST_FILE = "test-entry.yml";
    private static final String OUTPUT_FILE = "CHANGELOG.md";

    @Test
    void onlyUnreleasedDir() throws IOException {
        // given:
        String TEST_PATH = PATH + "/onlyUnreleasedDir";
        File entry = new File(TEST_PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + TEST_FILE);
        File outputFile = new File(TEST_PATH + "/" + OUTPUT_FILE);
        File expectedChangelog = new File(TEST_PATH + "/" + "EXPECTED_CHANGELOG.md");
        assertTrue(entry.exists());
        assertFalse(outputFile.exists());
        assertTrue(expectedChangelog.exists());

        // when:
        GenerateProjectCommand.of(TEST_PATH, INPUT_DIR, TEST_PATH + "/" + OUTPUT_FILE, CONFIG_FILE).execute(false);

        // then:
        assertTrue(outputFile.exists());
        String expectedContent = FileUtils.fileRead(expectedChangelog, "UTF-8");
        String actualContent = FileUtils.fileRead(outputFile, "UTF-8");
        assertThat(actualContent).isEqualToIgnoringWhitespace(expectedContent);

        // cleanup:
        new File(TEST_PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + "version-summary.md").delete();
        new File(TEST_PATH + "/" + OUTPUT_FILE).delete();
    }

    @Test
    void missingReleaseDateTxt() throws IOException {
        // given:
        String TEST_PATH = PATH + "/missingReleaseDateTxt";
        File entry = new File(TEST_PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + TEST_FILE);
        File outputFile = new File(TEST_PATH + "/" + OUTPUT_FILE);
        File expectedChangelog = new File(TEST_PATH + "/" + "EXPECTED_CHANGELOG.md");
        assertTrue(entry.exists());
        assertFalse(outputFile.exists());
        assertTrue(expectedChangelog.exists());

        // when:
        GenerateProjectCommand.of(TEST_PATH, INPUT_DIR, TEST_PATH + "/" + OUTPUT_FILE, CONFIG_FILE).execute(false);

        // then:
        assertTrue(outputFile.exists());
        String expectedContent = FileUtils.fileRead(expectedChangelog, "UTF-8");
        String actualContent = FileUtils.fileRead(outputFile, "UTF-8");
        assertThat(actualContent).isEqualToIgnoringWhitespace(expectedContent);

        // cleanup:
        new File(TEST_PATH + "/" + OUTPUT_FILE).delete();
    }
}
