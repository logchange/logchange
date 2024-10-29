package dev.logchange.core.infrastructure.persistance.file;

import dev.logchange.core.application.file.repository.FileWriter;
import dev.logchange.core.application.file.repository.XmlFileWriter;
import dev.logchange.core.application.file.repository.YmlFileReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.maven.plugins.changes.model.ChangesDocument;
import org.apache.maven.plugins.changes.model.io.xpp3.ChangesXpp3Writer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

@Log
@RequiredArgsConstructor(staticName = "of")
public class FileRepository implements FileWriter, YmlFileReader, XmlFileWriter {

    private final File outputFile;


    @Override
    public void write(String content) {
        try (OutputStream os = Files.newOutputStream(outputFile.toPath());
             PrintWriter out = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8))) {

            out.println(content);

        } catch (IOException e) {
            String message = "Could not save markdown to file: " + outputFile + " because: " + e.getMessage();
            log.severe(message);
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Returns: The returning stream of files is not sorted.
     */
    @Override
    public Stream<File> readFiles(File directory) {
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

    @Override
    public InputStream readFileContent(File entry) {
        try {
            return new FileInputStream(entry);
        } catch (FileNotFoundException e) {
            String message = "Cannot find entry file: " + entry.getName();
            log.severe(message);
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void writeXml(ChangesDocument changesDocument) {
        ChangesXpp3Writer changesXmlWriter = new ChangesXpp3Writer();

        try (Writer writer = new java.io.FileWriter(outputFile)) {
            changesXmlWriter.write(writer, changesDocument);
        } catch (IOException e) {
            String message = "Could not save changes document to file: " + outputFile + " because: " + e.getMessage();
            log.severe(message);
            throw new IllegalArgumentException(message);
        }
    }
}
