package dev.logchange.core.application.file;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GitKeepTest {

    private static final String PATH = "src/test/resources/GitKeepTest/";
    private static final String GIT_KEEP_NAME = ".gitkeep";

    @AfterEach
    void cleanUp() {
        new File(PATH + GIT_KEEP_NAME).delete();
    }

    @Test
    void shouldCreatGitKeep() {
        // given:
        File gitKeepFile = new File(PATH + GIT_KEEP_NAME);
        assertFalse(gitKeepFile.exists());

        // when:
        GitKeep.of(Paths.get(PATH, GIT_KEEP_NAME)).create();

        // then:
        assertTrue(gitKeepFile.exists());
    }

    @Test
    void shouldNotThrowExceptionWhenGitKeepAlreadyExists() throws IOException {
        // given:
        File gitKeepFile = new File(PATH + GIT_KEEP_NAME);
        gitKeepFile.createNewFile();
        assertTrue(gitKeepFile.exists());

        // when:
        GitKeep.of(Paths.get(PATH, GIT_KEEP_NAME)).create();

        // then:
        assertTrue(gitKeepFile.exists());
    }

}