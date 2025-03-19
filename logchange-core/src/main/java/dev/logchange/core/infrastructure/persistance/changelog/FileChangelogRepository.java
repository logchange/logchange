package dev.logchange.core.infrastructure.persistance.changelog;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.application.file.query.FileQuery;
import dev.logchange.core.application.file.repository.FileWriter;
import dev.logchange.core.application.file.repository.XmlFileWriter;
import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.domain.changelog.model.archive.ChangelogArchive;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersionEntriesGroup;
import dev.logchange.core.domain.changelog.model.version.Version;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.changelog.MDChangelog;
import dev.logchange.core.format.release_date.FileReleaseDateTime;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntry;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntryConfigException;
import dev.logchange.core.format.yml.config.YMLChangelogException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.maven.plugins.changes.model.ChangesDocument;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

import static dev.logchange.core.Constants.TEMPLATES_DIR_NAME;

@Log
@RequiredArgsConstructor
public class FileChangelogRepository implements ChangelogRepository {

    private final File inputDirectory;
    private final Config config;

    private final FileQuery reader;
    private final FileWriter writer;
    private final XmlFileWriter xmlWriter;

    @Override
    public Changelog findMarkdown() {
        List<ChangelogVersion> versions = new LinkedList<>();
        List<ChangelogArchive> archives = new LinkedList<>();

        log.info("Querying changelog files...");
        this.reader.readFiles(inputDirectory).forEach(file -> {
            if (isVersionDirectory(file)) {
                versions.add(getChangelogVersion(file));
            }
            if (isArchive(file)) {
                archives.add(getChangelogArchive(file));
            }
        });
        versions.sort(Collections.reverseOrder());
        archives.sort(Collections.reverseOrder());
        return Changelog.of(versions, archives);
    }

    @Override
    public Changelog findXML() {
        List<ChangelogVersion> versions = new LinkedList<>();
        List<ChangelogArchive> archives = new LinkedList<>();

        this.reader.readFiles(inputDirectory).forEach(file -> {
            if (isVersionDirectory(file)) {
                versions.add(getChangelogVersion(file));
            }
            if (isXmlArchive(file)) {
                archives.add(getChangelogArchive(file));
            }
        });
        versions.sort(Collections.reverseOrder());
        return Changelog.of(versions, archives);
    }

    @Override
    public void save(Changelog changelog) {
        String md = new MDChangelog(config, changelog).toMD();
        writer.write(md);
    }

    @Override
    public void saveXML(ChangesDocument changesDocument) {
        this.xmlWriter.writeXml(changesDocument);
    }

    private boolean isVersionDirectory(File file) {
        return file.isDirectory() && !file.getName().equals(TEMPLATES_DIR_NAME);
    }

    private boolean isArchive(File file) {
        return file.getName().startsWith("archive");
    }

    private boolean isXmlArchive(File file) {
        return file.getName().startsWith("archive") && file.getName().endsWith(".xml");
    }

    private ChangelogVersion getChangelogVersion(File versionDirectory) {
        return ChangelogVersion.builder()
                .version(getVersion(versionDirectory))
                // used to skip "v" from directories names
                // we can use "(?!\.)(\d+(\.\d+)+)([-.][A-Z]+)?(?![\d.])$" to get version and skipp all letters before version number
                // but we have to make exception for "unreleased" string as it is not matching this regexp
                .entriesGroups(getEntries(versionDirectory))
                .releaseDateTime(FileReleaseDateTime.getFromDir(versionDirectory))
                .build();
    }

    private ChangelogArchive getChangelogArchive(File file) {
        try {
            return ChangelogArchive.of(file.getName(), Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.severe("Error while getting changelog archive from file: " + e.getMessage());
            throw new IllegalStateException(e.getMessage());
        }
    }

    private Version getVersion(File versionDirectory) {
        return Version.of(versionDirectory.getName().replace("v", ""));
    }

    private List<ChangelogVersionEntriesGroup> getEntries(File versionDirectory) {
        ChangelogEntryType.setEntryTypes(config.getEntryTypes());
        List<Exception> exceptions = new ArrayList<>();

        List<ChangelogEntry> entries = reader.readYmlFiles(versionDirectory)
                .map((file) -> {
                    try {
                        return YMLChangelogEntry.of(reader.readFileContent(file), file.getPath());
                    } catch (YMLChangelogEntryConfigException e) {
                        exceptions.add(e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(YMLChangelogEntry::to)
                .collect(Collectors.toList());

        if (!exceptions.isEmpty()) {
            throw new YMLChangelogException(exceptions);
        }

        return ChangelogVersionEntriesGroup.ofEntriesKeepingOrder(entries);
    }
}
