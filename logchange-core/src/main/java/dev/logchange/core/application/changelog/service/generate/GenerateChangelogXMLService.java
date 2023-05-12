package dev.logchange.core.application.changelog.service.generate;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryAuthor;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import org.apache.maven.plugins.changes.model.Action;
import org.apache.maven.plugins.changes.model.Body;
import org.apache.maven.plugins.changes.model.ChangesDocument;
import org.apache.maven.plugins.changes.model.Release;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenerateChangelogXMLService implements GenerateChangelogUseCase {

    private final ChangelogRepository changelogRepository;

    public GenerateChangelogXMLService(ChangelogRepository changelogRepository) {
        this.changelogRepository = changelogRepository;
    }

    @Override
    public void handle(GenerateChangelogCommand command) {
        Changelog changelog = changelogRepository.find();
        ChangesDocument changesDocument = mapChangelogToChangesDocument(changelog);

        changelogRepository.saveXML(changesDocument);
    }

    /**
     * Maps a Changelog object to Maven Changes release.
     * @param changelog changelog
     * @return {@link Release}
     */
    private ChangesDocument mapChangelogToChangesDocument(Changelog changelog) {
        ChangesDocument changesDocument = new ChangesDocument();
        List<Release> changelogReleases = mapVersionsToReleases(changelog.getVersions().getVersions());

        // TODO parse archive

        Body changesBody = new Body();
        changelogReleases.forEach(changesBody::addRelease);
        changesDocument.setBody(changesBody);

        return changesDocument;
    }

    /**
     * Maps data from logchange model to maven changes model.
     * @param versions list of versions to map
     * @return {@link List} of {@link Release}
     */
    private List<Release> mapVersionsToReleases(List<ChangelogVersion> versions) {
        List<Release> releases = new ArrayList<>();
        for (ChangelogVersion version : versions) {
            if (version.getReleaseDateTime() == null || version.getEntries().isEmpty()) continue;
            Release release = new Release();
            release.setVersion(version.getVersion().getValue());
            release.setDateRelease(version.getReleaseDateTime().toLocalDate().toString());

            List<Action> actions = new ArrayList<>();
            version.getEntries().forEach(changelogEntry -> {
                Action action = new Action();
                action.setDev(authorsToString(changelogEntry.getAuthors()));
                action.setType(changelogEntry.getType().getType()); // TODO map changelogentrytype to changes xml type
                action.setAction(changelogEntry.getTitle().getValue());
                actions.add(action);
            });
            release.setActions(actions);

            releases.add(release);
        }
        return releases;
    }

    private String authorsToString(List<ChangelogEntryAuthor> authors) {
        StringBuilder builder = new StringBuilder();

        Iterator<ChangelogEntryAuthor> iterator = authors.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next().getName());
            if (iterator.hasNext()) builder.append(",");
        }
        return builder.toString();
    }
}
