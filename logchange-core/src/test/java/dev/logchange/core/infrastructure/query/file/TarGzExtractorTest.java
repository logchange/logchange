package dev.logchange.core.infrastructure.query.file;

import dev.logchange.core.application.file.query.TarGzQuery;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TarGzExtractorTest {

    private static final Pattern VERSION_PATTERN = Pattern.compile("v\\d+\\.\\d+\\.\\d+");

    @Test
    void testGetWithValidUrlAndInputDir() throws IOException {
        // given:
        File tempDir = Files.createTempDirectory("tmp").toFile();
        TarGzQuery tarGzQuery = new TarGzExtractor(tempDir.toPath());
        Path expectedPath = tempDir.toPath().resolve("logchange-main").resolve("changelog");

        // when:
        Path path = tarGzQuery.get("https://github.com/logchange/logchange/archive/refs/heads/main.tar.gz", "changelog");

        // then:
        try {
            assertNotNull(path);
            assertTrue(path.toString().contains("changelog"));
            assertEquals(expectedPath, path);
            File changelogDir = path.toFile();
            assertTrue(changelogDir.isDirectory());
            for (File file : Objects.requireNonNull(changelogDir.listFiles())) {
                if (file.isDirectory()) {
                    assertTrue(file.getName().equals("unreleased") || VERSION_PATTERN.matcher(file.getName()).matches(), "Unexpected dir: " + file.getName());
                } else {
                    assertEquals("logchange-config.yml", file.getName(), "Unexpected file: " + file.getName());
                }
            }
        } finally {
            // cleanup:
            deleteTmpDir(tempDir.toPath());
        }
    }

    @Test
    void shouldThrowExceptionWithInvalidURL() throws IOException {
        // given:
        File tempDir = Files.createTempDirectory("tmp").toFile();
        TarGzQuery tarGzQuery = new TarGzExtractor(tempDir.toPath());
        String invalidURL = "invalidURL";

        // when-then:
        try {
            assertThrows(IOException.class, () -> tarGzQuery.get(invalidURL, "changelog"));
        } finally {
            // cleanup:
            tempDir.delete();
        }
    }

    @Test
    void shouldNotFindAnyFileWithInvalidChangelogInputDir() throws IOException {
        // given:
        File tempDir = Files.createTempDirectory("tmp").toFile();
        TarGzQuery tarGzQuery = new TarGzExtractor(tempDir.toPath());
        String invalidDir = "invalid_dir";

        // when-then:
        try {
            tarGzQuery.get("https://github.com/logchange/logchange/archive/refs/heads/main.tar.gz", invalidDir);
            assertEquals(0, Objects.requireNonNull(tempDir.listFiles()).length);
        } finally {
            // cleanup:
            tempDir.delete();
        }
    }

    public void deleteTmpDir(Path directory) throws IOException {
        try (Stream<Path> paths = Files.walk(directory)) {
            paths.sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
