package dev.logchange.core.application.changelog.service.aggregate;

import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.*;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;

@RequiredArgsConstructor
public class TarGzService {

    private final Path path;
    private final String url;

    public void get() throws IOException {
        File tarGzFile = downloadFile();
        extractTarGz(tarGzFile, path.toFile());
    }

    private File downloadFile() throws IOException {
        URL url = new URL(this.url);
        File tempFile = Files.createTempFile("download", ".tar.gz").toFile();
        try (InputStream in = url.openStream();
             FileOutputStream out = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        return tempFile;
    }

    private void extractTarGz(File tarGzFile, File destDir) throws IOException {
        try (FileInputStream fis = new FileInputStream(tarGzFile);
             GZIPInputStream gis = new GZIPInputStream(fis);
             TarArchiveInputStream tis = new TarArchiveInputStream(gis)) {
            TarArchiveEntry entry;
            while ((entry = tis.getNextEntry()) != null) {
                File outputFile = new File(destDir, entry.getName());
                if (entry.isDirectory()) {
                    if (!outputFile.exists()) {
                        outputFile.mkdirs();
                    }
                } else {
                    try (FileOutputStream fos = new FileOutputStream(outputFile);
                         BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = tis.read(buffer)) != -1) {
                            bos.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            Path outputPath = FileSystems.getDefault().getPath(".");
            String fileUrl = "https://github.com/logchange/hofund/archive/refs/heads/master.tar.gz";
            TarGzService tarGzService = new TarGzService(outputPath, fileUrl);
            tarGzService.get();
            System.out.println("Download and extraction completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
