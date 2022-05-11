package dev.logchange.core.domain.changelog.model.entry;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntry {

    private final ChangelogEntryTitle title;
    private final ChangelogEntryType type;
    @Singular
    private final List<ChangelogEntryMergeRequest> mergeRequests;
    @Singular
    private final List<String> issues;
    @Singular
    private final List<ChangelogEntryLink> links;
    @Singular
    private final List<ChangelogEntryAuthor> authors;
    @Singular
    private final List<String> importantNotes;
    @Singular
    private final List<ChangelogEntryConfiguration> configurations;

}
