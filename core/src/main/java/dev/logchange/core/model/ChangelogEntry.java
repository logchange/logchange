package dev.logchange.core.model;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntry {

    private final ChangelogTitle title;
    private final ChangelogMergeRequest mergeRequest;
    @Singular
    private final List<String> issues;
    @Singular
    private final List<ChangelogEntryLink> links;
    @Singular
    private final List<ChangelogEntryAuthor> authors;
    private final ChangelogEntryType type;
    @Singular
    private final List<String> importantNotes;
    @Singular
    private final List<Configuration> configurations;

    public static ChangelogEntry of(String title, String mergeRequest, String issue, ChangelogEntryLink link, ChangelogEntryAuthor author, ChangelogEntryType type) {
        return ChangelogEntry.builder()
                .title(ChangelogTitle.of(title))
                .mergeRequest(ChangelogMergeRequest.of(mergeRequest))
                .issue(issue)
                .link(link)
                .author(author)
                .type(type)
                .importantNotes(Collections.emptyList())
                .configurations(Collections.emptyList())
                .build();
    }
}
