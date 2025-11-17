package dev.logchange.core.infrastructure.query.file;

import dev.logchange.core.application.file.query.TarGzQuery;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;

@CustomLog
@RequiredArgsConstructor
public class TarGzExtractor implements TarGzQuery {

    private final Path extractionPath;

    @Override
    public Path get(String projectUrl, String projectInputDir) throws IOException {
        log.info("Starting download from URL: " + projectUrl);
        File tarGzFile = downloadFile(projectUrl);
        log.info("Download completed. Extracting to: " + extractionPath.toString());
        return extractTarGz(tarGzFile, projectInputDir);
    }

    private File downloadFile(String projectUrl) throws IOException {
        URL url = new URL(projectUrl);
        File tempFile = Files.createTempFile("download", ".tar.gz").toFile();

        try (InputStream in = url.openStream();
             FileOutputStream out = new FileOutputStream(tempFile)) {
            copyStreamWithProgress(in, out);
        }

        return tempFile;
    }

    private void copyStreamWithProgress(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[10 * 1024];
        int bytesRead;
        int totalBytesRead = 0;
        int MB_LOG_STEP = 1024 * 1024;

        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            totalBytesRead += bytesRead;

            int currentChunk = totalBytesRead / MB_LOG_STEP;
            int previousChunk = (totalBytesRead - bytesRead) / MB_LOG_STEP;

            if (currentChunk > previousChunk) {
                log.info("Downloaded " + totalBytesRead + " bytes...");
            }
        }
    }

    private Path extractTarGz(File tarGzFile, String projectChangelogDir) throws IOException {
        try (FileInputStream fis = new FileInputStream(tarGzFile);
             GZIPInputStream gis = new GZIPInputStream(fis);
             TarArchiveInputStream tis = new TarArchiveInputStream(gis)) {

            String baseDir = extractBaseDirectory(tis);
            extractEntries(tis, baseDir + projectChangelogDir);
            return this.extractionPath.resolve(baseDir).resolve(projectChangelogDir);
        } finally {
            log.info("Deleting downloaded archive file");
            boolean deleted = tarGzFile.delete();
            if (!deleted) {
                log.warn("Cannot delete downloaded archive file: " + tarGzFile.getAbsolutePath());
            }
        }
    }

    private String extractBaseDirectory(TarArchiveInputStream tis) throws IOException {
        TarArchiveEntry entry;

        while ((entry = tis.getNextEntry()) != null) {
            if (entry.isDirectory()) {
                return entry.getName();
            }
        }

        throw new IOException("Base directory not found in archive.");
    }

    private void extractEntries(TarArchiveInputStream tis, String changelogDirPath) throws IOException {
        System.out.println("Started extracting from " + changelogDirPath);
        TarArchiveEntry entry;

        while ((entry = tis.getNextEntry()) != null) {
            String entryName = entry.getName();

            if (entryName.startsWith(changelogDirPath)) {
                File outputFile = new File(extractionPath.toFile(), entryName);

                if (entry.isDirectory()) {
                    createDirectoryIfNotExists(outputFile);
                } else {
                    extractFile(tis, outputFile);
                }

            }
        }
        log.info("Extraction completed successfully.");
    }

    private void createDirectoryIfNotExists(File dir) {
        if (!dir.exists()) {
            dir.mkdirs();
            log.info("Created directory: " + dir.getAbsolutePath());
        }
    }

    private void extractFile(TarArchiveInputStream tis, File outputFile) throws IOException {
        createDirectoryIfNotExists(outputFile.getParentFile());

        try (FileOutputStream fos = new FileOutputStream(outputFile);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] buffer = new byte[1024];
            int len;

            while ((len = tis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            log.info("Extracted file: " + outputFile.getAbsolutePath());
        }
    }
}

