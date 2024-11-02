package dev.logchange.core.infrastructure.query.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class FileReaderTest {

    private FileReader fileReader;

    @BeforeEach
    void setUp() {
        fileReader = new FileReader();
    }

    @Test
    void testReadFilesReturnsStreamOfFiles(@TempDir Path tempDir) throws IOException {
        File file1 = Files.createFile(tempDir.resolve("file1.txt")).toFile();
        File file2 = Files.createFile(tempDir.resolve("file2.txt")).toFile();

        try (Stream<File> fileStream = fileReader.readFiles(tempDir.toFile())) {
            List<File> files = fileStream.collect(Collectors.toList());
            assertEquals(2, files.size());
            assertTrue(files.contains(file1));
            assertTrue(files.contains(file2));
        }
    }

    @Test
    void testReadFilesReturnsEmptyStreamWhenDirectoryIsEmpty(@TempDir Path tempDir) {
        try (Stream<File> fileStream = fileReader.readFiles(tempDir.toFile())) {
            assertEquals(0, fileStream.count());
        }
    }

    @Test
    void testReadFilesReturnsEmptyStreamWhenDirectoryIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            try (Stream<File> fileStream =  fileReader.readFiles(null)) {

            }
        });

        assertEquals("Input directory cannot be null!", exception.getMessage());

    }

    @Test
    void testReadYmlFilesReturnsOnlyYamlFilesInSortedOrder(@TempDir Path tempDir) throws IOException {
        File file1 = Files.createFile(tempDir.resolve("config.yml")).toFile();
        File file2 = Files.createFile(tempDir.resolve("data.yaml")).toFile();
        File file3 = Files.createFile(tempDir.resolve("other.txt")).toFile();

        try (Stream<File> ymlFilesStream = fileReader.readYmlFiles(tempDir.toFile())) {
            List<File> ymlFiles = ymlFilesStream.collect(Collectors.toList());

            assertEquals(2, ymlFiles.size());
            assertEquals(file1, ymlFiles.get(0));
            assertEquals(file2, ymlFiles.get(1));
            assertFalse(ymlFiles.contains(file3));
        }
    }

    @Test
    void testReadFileContentReturnsInputStream() throws IOException {
        File tempFile = File.createTempFile("testFile", ".txt");
        Files.write(tempFile.toPath(), "Test content".getBytes());

        try (InputStream inputStream = fileReader.readFileContent(tempFile)) {
            assertNotNull(inputStream);
            String content = new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors.joining("\n"));
            assertEquals("Test content", content);
        } finally {
            tempFile.delete();
        }
    }

    @Test
    void testReadFileContentThrowsExceptionWhenFileNotFound() {
        File nonExistentFile = new File("non_existent_file.txt");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            try (InputStream inputStream = fileReader.readFileContent(nonExistentFile)) {

            }
        });

        assertEquals("Cannot find entry file: non_existent_file.txt", exception.getMessage());
    }
}
