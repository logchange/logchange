package dev.logchange.core.application.changelog.repository;

import dev.logchange.core.domain.changelog.model.Changelog;
import org.apache.maven.plugins.changes.model.ChangesDocument;

public interface ChangelogRepository {
    Changelog findMarkdown();

    Changelog findXML();

    void save(Changelog changelog);

    void saveXML(ChangesDocument changesDocument);
}
