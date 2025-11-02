package dev.logchange.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * Utility class to resolve the resource path for a given test class.
 * <p>
 * This class provides a method to retrieve the path to the resource directory
 * corresponding to the provided test class. It is typically useful in test
 * scenarios where resources are bundled alongside test classes, and the
 * relative path to these resources needs to be resolved.
 */
public class TestResourcePath {

    public static Path getPath(Class<?> testClass) {
        return Paths.get(testClass.getClassLoader().getResource(testClass.getSimpleName()).getPath());
    }

    public static Path copyToTempDir(Path sourceDir, Path tempDir) throws IOException {
        try (Stream<Path> stream = Files.walk(sourceDir)) {
            stream.forEach(source -> {
                try {
                    Path destination = tempDir.resolve(sourceDir.relativize(source).toString());
                    if (Files.isDirectory(source)) {
                        Files.createDirectories(destination);
                    } else {
                        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Error while copying test files", e);
                }
            });
        }
        return tempDir;
    }
}
