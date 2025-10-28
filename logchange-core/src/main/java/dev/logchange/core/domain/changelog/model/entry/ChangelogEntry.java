package dev.logchange.core.domain.changelog.model.entry;

import dev.logchange.core.domain.changelog.model.DetachedConfiguration;
import dev.logchange.core.domain.changelog.model.DetachedImportantNote;
import dev.logchange.core.domain.changelog.model.HasModules;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntry implements HasModules {

    // used to keep the original order of entries
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

    public Stream<DetachedImportantNote> getDetachedImportantNotes() {
        return importantNotes.stream().map(importantNote ->
            new DetachedImportantNote(importantNote.getValue(), modules)
        );
    }

    public Stream<DetachedConfiguration> getDetachedConfigurations() {
        return configurations.stream().map(configuration ->
            new DetachedConfiguration(configuration, modules)
        );
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

    public ChangelogEntry addProjectModule(ChangelogModule module) {
        if (!modules.isEmpty()){
            throw new RuntimeException("Aggregation does not support project modules");
        }
        return toBuilder().module(module).build();
    }
}
