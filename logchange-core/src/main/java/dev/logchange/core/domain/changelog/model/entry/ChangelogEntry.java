package dev.logchange.core.domain.changelog.model.entry;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntry {

    private final String prefix;
    private final ChangelogEntryTitle title;
    private final ChangelogEntryType type;
    @Singular
    private final List<ChangelogEntryMergeRequest> mergeRequests;
    @Singular
    private final List<Long> issues;
    @Singular
    private final List<ChangelogEntryLink> links;
    @Singular
    private final List<ChangelogEntryAuthor> authors;
    @Singular
    private final List<ChangelogEntryImportantNote> importantNotes;
    @Singular
    private final List<ChangelogEntryConfiguration> configurations;

    public ChangelogEntry withPrefix(String prefix) {
        if (prefix == null) {
            return this;
        }
        List<ChangelogEntryImportantNote> prefixedNotes = importantNotes.stream()
                .map(note -> note.withPrefix(prefix))
                .collect(Collectors.toList());

        List<ChangelogEntryConfiguration> prefixedConfigurations = configurations.stream()
                .map(config -> config.withPrefix(prefix))
                .collect(Collectors.toList());

        return ChangelogEntry.builder()
                .prefix(prefix)
                .title(title)
                .type(type)
                .mergeRequests(mergeRequests)
                .issues(issues)
                .links(links)
                .authors(authors)
                .importantNotes(prefixedNotes)
                .configurations(prefixedConfigurations)
                .build();
    }
}
