package dev.logchange.core.infrastructure.persistance.file;

import dev.logchange.core.application.file.repository.FileWriter;
import dev.logchange.core.application.file.repository.XmlFileWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.maven.plugins.changes.model.ChangesDocument;
import org.apache.maven.plugins.changes.model.io.xpp3.ChangesXpp3Writer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Log
@RequiredArgsConstructor(staticName = "of")
public class FileRepository implements FileWriter, XmlFileWriter {

    private final File outputFile;

    @Override
    public void write(String content) {
        log.info("Writing markdown content to file: " + outputFile.getName());
        try (OutputStream os = Files.newOutputStream(outputFile.toPath());
             PrintWriter out = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8))) {

            out.println(content);

        } catch (IOException e) {
            String message = "Could not save markdown to file: " + outputFile + " because: " + e.getMessage();
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
