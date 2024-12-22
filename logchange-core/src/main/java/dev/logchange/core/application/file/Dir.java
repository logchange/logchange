package dev.logchange.core.application.file;

import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

@Log
public class Dir {
    private final Path path;

    private Dir(Path path) {
        this.path = path;
    }

    public static Dir of(Path path) {
        return new Dir(path);
    }

    public void create() {
        File newDir = path.toFile();
        if (!newDir.exists()) {
            if (newDir.mkdir()) {
                log.info("Created: " + newDir.getName());
            } else {
                log.warning(newDir.getName() + " cannot be created.");
            }
        } else {
            log.warning(newDir.getName() + " already exists.");
        }
    }

    public static Path createTmp() {
        try {
            return Files.createTempDirectory("tmp");
        } catch (IOException e) {
            String msg = "Cannot proceed without temporary directory!";
            log.severe(msg);
            throw new IllegalStateException(msg, e);
        }
    }

    public static void delete(Path directory) {
        try (Stream<Path> paths = Files.walk(directory)) {
            paths.sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    log.warning("Failed to delete " + path + ": " + e.getMessage());
                }
            });
            log.info("Directory " + directory + " deleted.");
        } catch (IOException e) {
            log.severe("Error walking file tree to delete directory: " + e.getMessage());
        }
    }

    public static File find(String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            String msg =  String.format("There is no %s directory in this project !!!", directoryPath);
            log.severe(msg);
            throw new RuntimeException(msg);
        }

        if (!dir.isDirectory()) {
            String msg = String.format("File %s is not a directory !!!", directoryPath);
            log.severe(msg);
            throw new RuntimeException(msg);
        }

        return dir;
    }
}
