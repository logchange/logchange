package dev.logchange.core.domain.changelog.model.entry;

import dev.logchange.core.domain.changelog.model.DetachedConfiguration;
import dev.logchange.core.domain.changelog.model.DetachedImportantNote;
import dev.logchange.core.domain.changelog.model.HasModules;
import lombok.*;

import java.util.List;
import java.util.stream.Stream;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntry implements HasModules {

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
    // used to keep the original order of entries
    @Setter
    private int id;

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

    @Override
    public String toString() {
        return type.getKey() + " - " + title.getValue();
    }

    @Override
    public List<ChangelogModule> getModules() {
        return modules;
    }

    /**
     * Method used during aggregation of projects as one CHANGELOG
     * Single project becomes one module
     */
    public ChangelogEntry addProjectAsModule(ChangelogModule module) {
        if (!modules.isEmpty()) {
            throw new RuntimeException("Aggregation does not support projects using modules! Please consider using a single module for the project or create and issue at https://github.com/logchange/logchange");
        }

        return toBuilder().module(module).build();
    }
}
