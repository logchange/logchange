package dev.logchange.core.application.changelog.service.aggregate;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.*;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;

@Log
@RequiredArgsConstructor
public class TarGzService {

    private final Path path;
    private final String url;
    private final String inputDir;

    public static void main(String[] args) {
        try {
            Path outputPath = FileSystems.getDefault().getPath(".");
            String fileUrl = "https://github.com/logchange/hofund/archive/refs/heads/master.tar.gz";
            String inputDir = "/changelog";
            TarGzService tarGzService = new TarGzService(outputPath, fileUrl, inputDir);
            tarGzService.get();
            System.out.println("Download and extraction completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void get() throws IOException {
        log.info("Starting download from URL: " + url);
        File tarGzFile = downloadFile();
        log.info("Download completed. Extracting to: " + path.toString());
        extractTarGz(tarGzFile, path.toFile());
        log.info("Extraction completed successfully.");
    }

    private File downloadFile() throws IOException {
        URL url = new URL(this.url);
        File tempFile = Files.createTempFile("download", ".tar.gz").toFile();
        try (InputStream in = url.openStream();
             FileOutputStream out = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[10 * 1024];
            int bytesRead;
            int totalBytesRead = 0;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                log.info("Downloaded " + totalBytesRead + " bytes...");
            }
        }
        return tempFile;
    }

    private void extractTarGz(File tarGzFile, File destDir) throws IOException {
        try (FileInputStream fis = new FileInputStream(tarGzFile);
             GZIPInputStream gis = new GZIPInputStream(fis);
             TarArchiveInputStream tis = new TarArchiveInputStream(gis)) {
            TarArchiveEntry entry;
            String baseDir = null;

            while ((entry = tis.getNextEntry()) != null) {
                String entryName = entry.getName();

                if (baseDir == null && entry.isDirectory()) {
                    baseDir = entryName.split("/")[0];
                }

                if (entryName.startsWith(baseDir + "/" + inputDir)) {
                    File outputFile = new File(destDir, entryName);

                    if (entry.isDirectory()) {
                        if (!outputFile.exists()) {
                            outputFile.mkdirs();
                            log.info("Created directory: " + outputFile.getAbsolutePath());
                        }
                    } else {
                        File parent = outputFile.getParentFile();
                        if (!parent.exists()) {
                            parent.mkdirs();
                            log.info("Created parent directory: " + parent.getAbsolutePath());
                        }
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
            }
        }
    }
}
