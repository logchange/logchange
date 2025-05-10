package dev.logchange.core.domain.changelog.model.entry;

import dev.logchange.core.domain.changelog.model.DetachedImportantNote;
import dev.logchange.core.domain.changelog.model.HasModules;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntry implements HasModules {

    // used to keep original order of entries
    @Setter
    private int id;

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
    @Singular
    private final List<ChangelogModule> modules;

    public List<DetachedImportantNote> getDetachedImportantNotes() {
        System.out.println(importantNotes);
        return importantNotes.stream().map(importantNote ->
            new DetachedImportantNote(importantNote.getValue(), modules)
        ).collect(Collectors.toList());
    }

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

        return this.toBuilder()
                .importantNotes(prefixedNotes)
                .configurations(prefixedConfigurations)
                .build();
    }

    @Override
    public String toString() {
        return type.getKey() + " - " + title.getValue();
    }

}
