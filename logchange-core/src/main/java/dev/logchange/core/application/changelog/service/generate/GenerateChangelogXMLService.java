package dev.logchange.core.application.changelog.service.generate;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryAuthor;
import dev.logchange.core.domain.changelog.model.entry.ChangesXMLEntryType;
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
        Changelog changelog = changelogRepository.findXML();
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



        Body changesBody = new Body();
        changelogReleases.forEach(changesBody::addRelease);
        changesDocument.setBody(changesBody);

        return changesDocument;
    }

    /**
     * Maps a list of {@link ChangelogVersion} from logchange model to maven changes {@link Release}.
     * @param versions list of versions
     * @return {@link List} of {@link Release}
     */
    private List<Release> mapVersionsToReleases(List<ChangelogVersion> versions) {
        List<Release> releases = new ArrayList<>();
        for (ChangelogVersion version : versions) {
            // if releaseDateTime or list of entries are empty, assume the version directory is empty
            if (version.getReleaseDateTime() == null || version.getEntries().isEmpty()) {
                continue;
            }
            Release release = new Release();
            release.setVersion(version.getVersion().getValue());
            release.setDateRelease(version.getReleaseDateTime().toLocalDate().toString());

            List<Action> actions = new ArrayList<>();
            version.getEntries().forEach(changelogEntry -> {
                Action action = new Action();
                action.setDev(authorsToString(changelogEntry.getAuthors()));
                String type = ChangesXMLEntryType.getXmlTypeFromMarkdownEntryType(changelogEntry.getType()).getType();
                action.setType(type);
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
            ChangelogEntryAuthor author = iterator.next();
            String authorName = "";

            // prefer Nick, then Name, leave empty if both are missing
            if (author.getNick() != null && !author.getNick().isEmpty()) {
                authorName = author.getNick();
            }
            else if (author.getName() != null && !author.getName().isEmpty()) {
                authorName = author.getName();
            }


            builder.append(authorName);
            if (iterator.hasNext()) {
                builder.append(",");
            }
        }
        return builder.toString();
    }
}
