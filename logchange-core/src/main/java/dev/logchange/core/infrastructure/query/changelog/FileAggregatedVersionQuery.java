package dev.logchange.core.infrastructure.query.changelog;

import dev.logchange.core.application.changelog.repository.AggregatedVersionQuery;
import dev.logchange.core.application.file.repository.FileReader;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.changelog.model.version.Version;
import dev.logchange.core.format.release_date.ReleaseDate;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntry;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntryConfigException;
import dev.logchange.core.format.yml.config.YMLChangelogException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
@RequiredArgsConstructor
public class FileAggregatedVersionQuery implements AggregatedVersionQuery {

    private final Version version;
    private final FileReader reader;

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
                .entries(getEntries(versionDirectory, projectName))
                .releaseDateTime(ReleaseDate.getFromDir(versionDirectory))
                .build();
    }

    private List<ChangelogEntry> getEntries(File versionDirectory, String projectName) {
        List<Exception> exceptions = new ArrayList<>();

        List<ChangelogEntry> entries = reader.readYmlFiles(versionDirectory)
                .map((file) -> {
                    try {
                        return YMLChangelogEntry.of(reader.readFileContent(file), file.getPath())
                                .withPrefix(projectName);
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

        return entries;
    }
}
