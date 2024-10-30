package dev.logchange.core.application.changelog.service.aggregate;

import dev.logchange.core.application.changelog.repository.AggregatedVersionQuery;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.domain.changelog.command.AggregateProjectsVersionUseCase;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.changelog.model.version.Version;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log
@RequiredArgsConstructor
public class AggregateProjectsVersionService implements AggregateProjectsVersionUseCase {

    private final AggregatedVersionQuery aggregatedVersionQuery;
    private final VersionSummaryRepository versionSummaryRepository;

    @Override
    public void handle(AggregateChangelogsVersionsCommand command) {
        log.info("Started aggregating command");

        Path tmpDir = createTmpDir();
        List<Exception> exceptions = new ArrayList<>();
        List<ChangelogVersion> changelogVersions = new ArrayList<>();
        TarGzService tarGzService = new TarGzService(tmpDir);

        command.getAggregates().getProjects().forEach(project -> {
            try {
                Path extractedProjectChangelogDir = tarGzService.get(project.getUrl(), project.getInputDir());
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

        deleteTmpDir(tmpDir);

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

        return ChangelogVersion.builder()
                .version(version)
                .releaseDateTime(OffsetDateTime.now())
                .entries(mergedEntries)
                .build();
    }

    private Path createTmpDir() {
        try {
            return Files.createTempDirectory("tmp");
        } catch (IOException e) {
            String msg = "Cannot proceed without temporary directory!";
            log.severe(msg);
            throw new IllegalStateException(msg, e);
        }
    }

    public void deleteTmpDir(Path directory) {
        try (Stream<Path> paths = Files.walk(directory)) {
            paths.sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    log.warning("Failed to delete " + path + ": " + e.getMessage());
                }
            });
        } catch (IOException e) {
            log.severe("Error walking file tree to delete directory: " + e.getMessage());
        }
    }
}
