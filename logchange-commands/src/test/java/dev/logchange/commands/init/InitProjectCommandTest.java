package dev.logchange.commands.init;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class InitProjectCommandTest {

    private static final String PATH = "src/test/resources/InitProjectCommandTest/";
    private static final String INPUT_DIR = "changelog/";
    private static final String UNRELEASED = "unreleased/";
    private static final String CONFIG_FILE = "logchange-config.yml";
    private static final String GIT_KEEP_FILE = ".gitkeep";
    private static final String OUTPUT_FILE = "CHANGELOG.md";

    @AfterEach
    void cleanup() {
        new File(PATH + INPUT_DIR + UNRELEASED + GIT_KEEP_FILE).delete();
        new File(PATH + INPUT_DIR + UNRELEASED).delete();
        new File(PATH + INPUT_DIR + CONFIG_FILE).delete();
        new File(PATH + INPUT_DIR).delete();
        new File(PATH + OUTPUT_FILE).delete();
    }

    @Test
    void shouldInitializeProject() {
        // given:
        File gitKeepFile = new File(PATH + INPUT_DIR + UNRELEASED + GIT_KEEP_FILE);
        File unreleasedDir = new File(PATH + INPUT_DIR + UNRELEASED);
        File configFile = new File(PATH + INPUT_DIR + CONFIG_FILE);
        File changelogDir = new File(PATH + INPUT_DIR);
        File changelogFile = new File(PATH + OUTPUT_FILE);

        assertFalse(changelogDir.exists());
        assertFalse(changelogFile.exists());
        assertFalse(unreleasedDir.exists());
        assertFalse(configFile.exists());
        assertFalse(gitKeepFile.exists());

        // when:
        InitProjectCommand.of(PATH, INPUT_DIR, UNRELEASED, OUTPUT_FILE).execute();

        // then:
        assertTrue(changelogDir.exists());
        assertTrue(changelogDir.isDirectory());

        assertTrue(unreleasedDir.exists());
        assertTrue(unreleasedDir.isDirectory());

        assertTrue(configFile.exists());
        assertTrue(configFile.isFile());

        assertTrue(gitKeepFile.exists());
        assertTrue(gitKeepFile.isFile());

        assertTrue(changelogFile.exists());
        assertTrue(changelogFile.isFile());
    }
}