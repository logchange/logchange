package dev.logchange.core.infrastructure.query.changelog;

import dev.logchange.core.application.changelog.repository.AggregatedVersionQuery;
import dev.logchange.core.application.file.query.FileQuery;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.entry.ChangelogModule;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersionEntriesGroup;
import dev.logchange.core.domain.changelog.model.version.Version;
import dev.logchange.core.format.release_date.FileReleaseDateTime;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntry;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntryParseException;
import dev.logchange.core.format.yml.config.YMLChangelogException;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@CustomLog
@RequiredArgsConstructor
public class FileAggregatedVersionFinder implements AggregatedVersionQuery {

    private final Version version;
    private final FileQuery reader;

    @Override
    public Optional<ChangelogVersion> find(Path changelogDirectory, String projectName) {
        return reader.readFiles(changelogDirectory.toFile())
                .filter(this::isVersionDirectory)
                .findFirst()
                .map(file -> getChangelogVersion(file, projectName));
    }

    private boolean isVersionDirectory(File file) {
        return file.isDirectory() && file.getName().equals(version.getDirName());
    }


    private ChangelogVersion getChangelogVersion(File versionDirectory, String projectName) {
        return ChangelogVersion.builder()
                .version(version)
                .releaseDateTime(FileReleaseDateTime.getFromDir(versionDirectory))
                .entriesGroups(getEntries(versionDirectory, projectName))
                .build();
    }

    private List<ChangelogVersionEntriesGroup> getEntries(File versionDirectory, String projectName) {
        List<Exception> exceptions = new ArrayList<>();
        ChangelogModule module = new ChangelogModule(projectName);
        List<ChangelogEntry> entries = reader.readYmlFiles(versionDirectory)
                .map((file) -> {
                    try {
                        return YMLChangelogEntry.of(reader.readFileContent(file), file.getPath());
                    } catch (YMLChangelogEntryParseException e) {
                        exceptions.add(e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(YMLChangelogEntry::to)
                .map(entry -> entry.addProjectAsModule(module))
                .collect(Collectors.toList());

        if (!exceptions.isEmpty()) {
            throw new YMLChangelogException(exceptions);
        }

        return ChangelogVersionEntriesGroup.ofEntriesKeepingOrder(entries);
    }
}
