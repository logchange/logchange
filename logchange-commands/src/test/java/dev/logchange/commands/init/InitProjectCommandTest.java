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
    private static final String ARCHIVE_FILE = "archive.md";

    @Test
    void shouldInitializeProject() {
        // given:
        String rootPath = PATH + "initializeProject/";
        File gitKeepFile = new File(rootPath + INPUT_DIR + "/" + UNRELEASED + "/" + GIT_KEEP_FILE);
        File unreleasedDir = new File(rootPath + INPUT_DIR + "/" + UNRELEASED);
        File configFile = new File(rootPath + INPUT_DIR + "/" + CONFIG_FILE);
        File changelogDir = new File(rootPath + INPUT_DIR);
        File changelogFile = new File(rootPath + OUTPUT_FILE);
        File archiveFile = new File(rootPath + INPUT_DIR + "/" + ARCHIVE_FILE);

        assertFalse(changelogDir.exists());
        assertFalse(changelogFile.exists());
        assertFalse(unreleasedDir.exists());
        assertFalse(configFile.exists());
        assertFalse(gitKeepFile.exists());
        assertFalse(archiveFile.exists());

        // when:
        InitProjectCommand.of(rootPath, INPUT_DIR, UNRELEASED, OUTPUT_FILE).execute();

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

        assertFalse(archiveFile.exists());

        // cleanup:
        gitKeepFile.delete();
        unreleasedDir.delete();
        configFile.delete();
        changelogDir.delete();
        changelogFile.delete();
    }

    @Test
    void shouldInitializeProjectWithArchive() {
        // given:
        String rootPath = PATH + "initializeProjectWithArchive/";
        File gitKeepFile = new File(rootPath + INPUT_DIR + "/" + UNRELEASED + "/" + GIT_KEEP_FILE);
        File unreleasedDir = new File(rootPath + INPUT_DIR + "/" + UNRELEASED);
        File configFile = new File(rootPath + INPUT_DIR + "/" + CONFIG_FILE);
        File changelogDir = new File(rootPath + INPUT_DIR);
        File changelogFile = new File(rootPath + OUTPUT_FILE);
        File archiveFile = new File(rootPath + INPUT_DIR + "/" + ARCHIVE_FILE);

        assertFalse(changelogDir.exists());
        assertTrue(changelogFile.exists());
        assertFalse(unreleasedDir.exists());
        assertFalse(configFile.exists());
        assertFalse(gitKeepFile.exists());
        assertFalse(archiveFile.exists());

        // when:
        InitProjectCommand.of(rootPath, INPUT_DIR, UNRELEASED, OUTPUT_FILE).execute();

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

        assertTrue(archiveFile.exists());
        assertTrue(archiveFile.isFile());

        // cleanup:
        gitKeepFile.delete();
        unreleasedDir.delete();
        configFile.delete();
        archiveFile.delete();
        changelogDir.delete();
    }
}