package dev.logchange.core.application.file;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GitKeepTest {

    private static final String PATH = "src/test/resources/GitKeepTest/";
    private static final String GIT_KEEP_NAME = ".gitkeepTest";

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
        GitKeep.of(PATH).create(GIT_KEEP_NAME);

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
        GitKeep.of(PATH).create(GIT_KEEP_NAME);

        // then:
        assertTrue(gitKeepFile.exists());
    }

}