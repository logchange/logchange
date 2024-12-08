package dev.logchange.core.application.file.query;

import java.io.File;
import java.io.InputStream;
import java.util.stream.Stream;

public interface FileQuery {

    Stream<File> readFiles(File directory);

    Stream<File> readYmlFiles(File versionsDirectory);

    InputStream readFileContent(File entry);
}
