package dev.logchange.commands.aggregate;

import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AggregateVersionCommandTest {

    private static final String PATH = "src/test/resources/AggregateVersionCommandTest";
    private static final String INPUT_DIR = "changelog";
    private static final String CONFIG_FILE = "logchange-config.yml";
    private static final String VERSION_DIR = "v0.4.0";
    private static final String VERSION_SUMMARY_FILE = "version-summary.md";

    @Test
    void shouldAggregateVersion() throws IOException {
        // given:
        File versionDir = new File(PATH + "/" + INPUT_DIR + "/" + VERSION_DIR);
        File versionSummary = new File(PATH + "/" + INPUT_DIR + "/" + VERSION_DIR + "/" + VERSION_SUMMARY_FILE);
        File expectedSummary = new File(PATH + "/EXPECTED-SUMMARY.md");
        assertFalse(versionDir.exists());
        assertFalse(versionSummary.exists());
        assertTrue(expectedSummary.exists());

        // when:
        AggregateVersionCommand.of(PATH, "0.4.0", INPUT_DIR, CONFIG_FILE).execute();

        // then:
        assertTrue(versionDir.exists());
        assertTrue(versionSummary.exists());
        String expectedContent = FileUtils.fileRead(expectedSummary, "UTF-8");
        String actualContent = FileUtils.fileRead(versionSummary, "UTF-8");
        assertThat(actualContent).isEqualToIgnoringWhitespace(expectedContent);

        // cleanup:
        versionSummary.delete();
        versionDir.delete();
    }
}