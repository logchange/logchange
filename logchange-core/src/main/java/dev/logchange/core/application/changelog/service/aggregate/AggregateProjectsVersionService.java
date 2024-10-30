package dev.logchange.core.application.changelog.service.aggregate;

import dev.logchange.core.application.changelog.repository.AggregatedVersionQuery;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.application.file.query.TarGzQuery;
import dev.logchange.core.domain.changelog.command.AggregateProjectsVersionUseCase;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.changelog.model.version.Version;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.io.IOException;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Log
@RequiredArgsConstructor
public class AggregateProjectsVersionService implements AggregateProjectsVersionUseCase {

    private final AggregatedVersionQuery aggregatedVersionQuery;
    private final VersionSummaryRepository versionSummaryRepository;
    private final TarGzQuery tarGzQuery;

    @Override
    public void handle(AggregateChangelogsVersionsCommand command) {
        log.info("Started aggregating command");

        List<Exception> exceptions = new ArrayList<>();
        List<ChangelogVersion> changelogVersions = new ArrayList<>();

        command.getAggregates().getProjects().forEach(project -> {
            try {
                Path extractedProjectChangelogDir = tarGzQuery.get(project.getUrl(), project.getInputDir());
                Optional<ChangelogVersion> result = aggregatedVersionQuery.find(extractedProjectChangelogDir, project.getName());

                if (result.isPresent()) {
                    changelogVersions.add(result.get());
                } else {
                    log.warning("Version " + command.getVersion().getValue() + " in project " + project.getName() + " not found!");
                }
            } catch (IOException e) {
                exceptions.add(e);
            }
        });

        if (!exceptions.isEmpty()) {
            throw new YMLAggregationException(exceptions);
        }

        ChangelogVersion aggregatedVersion = mergeVersions(changelogVersions, command.getVersion());
        versionSummaryRepository.save(aggregatedVersion);
    }

    private ChangelogVersion mergeVersions(List<ChangelogVersion> versions, Version version) {
        log.info("Merging changelog versions");
        List<ChangelogEntry> mergedEntries = versions.stream()
                .filter(Objects::nonNull)
                .flatMap(changelogVersion -> changelogVersion.getEntries().stream())
                .collect(Collectors.toList());

        OffsetDateTime latestReleaseDateTime = versions.stream()
                .filter(Objects::nonNull)
                .map(ChangelogVersion::getReleaseDateTime)
                .max(Comparator.naturalOrder())
                .orElse(OffsetDateTime.now());

        return ChangelogVersion.builder()
                .version(version)
                .releaseDateTime(latestReleaseDateTime)
                .entries(mergedEntries)
                .build();
    }
}
