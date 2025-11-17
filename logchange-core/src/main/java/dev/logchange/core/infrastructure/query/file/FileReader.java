package dev.logchange.core.infrastructure.query.file;

import dev.logchange.core.application.file.query.FileQuery;
import lombok.CustomLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

@CustomLog
public class FileReader implements FileQuery {

    /**
     * Returns: The returning stream of files is not sorted.
     */
    @Override
    public Stream<File> readFiles(File directory) {
        if (directory == null) {
            throw new IllegalArgumentException("Input directory cannot be null!");
        }
        File[] entriesFiles = directory.listFiles();

        if (entriesFiles == null) {
            return Stream.empty();
        }
        return Arrays.stream(entriesFiles);
    }

    /**
     * Returns: The returning stream of files is sorted and filtered.
     */
    @Override
    public Stream<File> readYmlFiles(File versionDirectory) {
        return readFiles(versionDirectory)
                .filter(file -> file.getName().contains(".yml") || file.getName().contains(".yaml"))
                .sorted((f1, f2) -> Comparator.comparing(File::getName).compare(f1, f2));
    }

    /**
     * Returns: Input stream of file content.
     */
    @Override
    public InputStream readFileContent(File entry) {
        try {
            return new FileInputStream(entry);
        } catch (FileNotFoundException e) {
            String message = "Cannot find entry file: " + entry.getName();
            log.error(message);
            throw new IllegalArgumentException(message);
        }
    }
}
