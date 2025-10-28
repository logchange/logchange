package dev.logchange.core.infrastructure.query.file;

import dev.logchange.core.application.file.query.TarGzQuery;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Buffer;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.GZIPOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class TarGzExtractorTest {

    private static final Pattern VERSION_PATTERN = Pattern.compile("v\\d+\\.\\d+\\.\\d+");

    private static MockWebServer server;

    @BeforeEach
    public void init() {
        server = new MockWebServer();
    }

    @Test
    void testGetWithValidUrlAndInputDir() throws IOException {
        // given:
        File tempDir = Files.createTempDirectory("tmp").toFile();
        TarGzQuery tarGzQuery = new TarGzExtractor(tempDir.toPath());
        Path expectedPath = tempDir.toPath().resolve("logchange-main").resolve("changelog");

        byte[] archive = createSampleTarGz();
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .addHeader("Content-Type", "application/gzip")
                .setBody(new Buffer().write(archive)));
        try {
            server.start();
            String url = server.url("/archive/refs/heads/main.tar.gz").toString();

            // when:
            Path path = tarGzQuery.get(url, "changelog");

            // then:
            assertEquals(1, Objects.requireNonNull(tempDir.listFiles()).length);
            assertNotNull(path);
            assertTrue(path.toString().contains("changelog"));
            assertEquals(expectedPath, path);
            File changelogDir = path.toFile();
            assertTrue(changelogDir.isDirectory());
            assertEquals(3, Objects.requireNonNull(changelogDir.listFiles()).length);
            for (File file : Objects.requireNonNull(changelogDir.listFiles())) {
                if (file.isDirectory()) {
                    assertTrue(file.getName().equals("unreleased") || VERSION_PATTERN.matcher(file.getName()).matches(), "Unexpected dir: " + file.getName());
                    assertEquals(1, Objects.requireNonNull(file.listFiles()).length);
                } else {
                    assertEquals("logchange-config.yml", file.getName(), "Unexpected file: " + file.getName());
                }
            }
        } finally {
            // cleanup:
            deleteTmpDir(tempDir.toPath());
            server.shutdown();
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

        byte[] archive = createSampleTarGz();
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .addHeader("Content-Type", "application/gzip")
                .setBody(new Buffer().write(archive)));

        // when-then:
        try {
            server.start();
            String url = server.url("/archive/refs/heads/main.tar.gz").toString();
            tarGzQuery.get(url, invalidDir);
            assertEquals(0, Objects.requireNonNull(tempDir.listFiles()).length);
        } finally {
            // cleanup:
            tempDir.delete();
            server.shutdown();
        }
    }

    private byte[] createSampleTarGz() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzos = new GZIPOutputStream(baos);
             TarArchiveOutputStream taos = new TarArchiveOutputStream(gzos)) {

            taos.setLongFileMode(TarArchiveOutputStream.LONGFILE_POSIX);

            writeDirectoryEntry(taos, "logchange-main/");
            writeDirectoryEntry(taos, "logchange-main/changelog/");
            writeDirectoryEntry(taos, "logchange-main/changelog/unreleased/");
            writeDirectoryEntry(taos, "logchange-main/changelog/v1.0.0/");

            byte[] file = "title: test".getBytes(StandardCharsets.UTF_8);
            writeFileEntry(taos, "logchange-main/changelog/logchange-config.yml", file);
            writeFileEntry(taos, "logchange-main/changelog/unreleased/new-entry.yml", file);
            writeFileEntry(taos, "logchange-main/changelog/v1.0.0/old-entry.yml", file);
            taos.finish();
        }
        return baos.toByteArray();
    }

    private void writeDirectoryEntry(TarArchiveOutputStream taos, String name) throws IOException {
        TarArchiveEntry entry = new TarArchiveEntry(name);
        entry.setModTime(0);
        entry.setMode(0755);
        taos.putArchiveEntry(entry);
        taos.closeArchiveEntry();
    }

    private void writeFileEntry(TarArchiveOutputStream taos, String name, byte[] content) throws IOException {
        TarArchiveEntry entry = new TarArchiveEntry(name);
        entry.setSize(content.length);
        entry.setModTime(0);
        entry.setMode(0644);
        taos.putArchiveEntry(entry);
        taos.write(content);
        taos.closeArchiveEntry();
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
