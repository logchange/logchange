package dev.logchange.core.application.changelog.service.archive;

import dev.logchange.core.application.changelog.repository.ChangelogPersistence;
import dev.logchange.core.application.changelog.repository.ChangelogQuery;
import dev.logchange.core.domain.changelog.command.ArchiveUseCase;
import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.domain.changelog.model.archive.ChangelogArchive;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import lombok.CustomLog;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CustomLog
public class ArchiveService implements ArchiveUseCase {

    private final ChangelogPersistence changelogPersistence;
    private final ChangelogQuery changelogQuery;

    public ArchiveService(ChangelogPersistence changelogPersistence, ChangelogQuery changelogQuery) {
        this.changelogPersistence = changelogPersistence;
        this.changelogQuery = changelogQuery;
    }

    @Override
    public List<String> handle(ArchiveCommand command) {
        log.info("Fetching changelog...");
        Changelog changelog = changelogQuery.findMarkdown();

        log.info("Filtering versions to be archived...");
        List<ChangelogVersion> versionsToBeArchived = changelog.getVersions().getVersions().stream()
                .filter(changelogVersion -> changelogVersion.getVersion().compareTo(command.getVersion()) <= 0)
                .filter(v -> !v.getVersion().isUnreleased())
                .collect(Collectors.toList());

        List<ChangelogArchive> archives = changelog.getArchives().getArchives();

        log.info("Creating changelog archive...");
        Changelog archive = Changelog.of(versionsToBeArchived, archives);
        log.info("Saving changelog archive...");
        changelogPersistence.save(archive);

        return Stream.concat(
                        archives.stream().map(ChangelogArchive::getFileName),
                        versionsToBeArchived.stream().map(changelogVersion -> changelogVersion.getVersion().getDirName()))
                .collect(Collectors.toList());
    }
}
