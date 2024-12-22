package dev.logchange.core.application.file;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DirTest {

    private static final String PATH = "src/test/resources/DirTest/";
    private static final String DIR_NAME = "test";


    @AfterEach
    void cleanUp() {
        new File(PATH + DIR_NAME).delete();
    }

    @Test
    void shouldCreateDir() {
        // given:
        assertFalse(new File(PATH + DIR_NAME).exists());

        // when:
        Dir.of(Paths.get(PATH, DIR_NAME)).create();

        // then:
        assertTrue(new File(PATH + DIR_NAME).exists());
    }

    @Test
    void shouldCreateTemporaryDir() {
        // when:
        Path tmp = Dir.createTmp();

        // then:
        assertTrue(tmp.toFile().exists());
        assertTrue(tmp.toFile().isDirectory());
        tmp.toFile().delete();
    }

    @Test
    void shouldDeleteDirectory() {
        // given:
        File newDir = new File(PATH + DIR_NAME);
        newDir.mkdir();

        // when:
        Dir.delete(newDir.toPath());

        // then:
        assertFalse(newDir.exists());
    }

    @Test
    void shouldDeleteDirectoryWithContent() throws IOException {
        // given:
        File newDir = new File(PATH + DIR_NAME);
        newDir.mkdir();
        File newFile = new File(PATH + DIR_NAME + "/test.txt");
        newFile.createNewFile();

        // when:
        Dir.delete(newDir.toPath());

        // then:
        assertFalse(newDir.exists());
        assertFalse(newFile.exists());
    }

    @Test
    void shouldFindDirectory() {
        // given:
        File newDir = new File(PATH + DIR_NAME);
        newDir.mkdir();

        // when:
        File file = Dir.find(PATH + DIR_NAME);

        // then:
        assertTrue(file.exists());
        assertTrue(file.isDirectory());
    }

    @Test
    void shouldNotFindDirectoryAndThrowException() {
        // when:
        Exception exception = assertThrows(RuntimeException.class, () -> Dir.find(PATH + DIR_NAME));

        // then:
        assertEquals("There is no src/test/resources/DirTest/test directory in this project !!!", exception.getMessage());
    }

    @Test
    void shouldFindFileAndThrowException() throws IOException {
        // given:
        File newFile = new File(PATH + DIR_NAME);
        newFile.createNewFile();

        // when:
        Exception exception = assertThrows(RuntimeException.class, () -> Dir.find(PATH + DIR_NAME));

        // then:
        assertEquals("File src/test/resources/DirTest/test is not a directory !!!", exception.getMessage());
    }

}