package dev.logchange.core.application.file.repository;

import org.apache.maven.plugins.changes.model.ChangesDocument;

public interface XmlFileWriter {

    void writeXml(ChangesDocument changesDocument);
}
