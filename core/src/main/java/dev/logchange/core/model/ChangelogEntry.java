package dev.logchange.core.model;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntry {

    private final ChangelogTitle title;
    private final ChangelogEntryType type;
    @Singular
    private final List<ChangelogMergeRequest> mergeRequests;
    @Singular
    private final List<String> issues;
    @Singular
    private final List<ChangelogEntryLink> links;
    @Singular
    private final List<ChangelogEntryAuthor> authors;
    @Singular
    private final List<String> importantNotes;
    @Singular
    private final List<Configuration> configurations;

    public static ChangelogEntry of(String title, ChangelogEntryType type, String mergeRequest, String issue, ChangelogEntryLink link, ChangelogEntryAuthor author) {
        return ChangelogEntry.builder()
                .title(ChangelogTitle.of(title))
                .type(type)
                .mergeRequest(ChangelogMergeRequest.of(mergeRequest))
                .issue(issue)
                .link(link)
                .author(author)
                .importantNotes(Collections.emptyList())
                .configurations(Collections.emptyList())
                .build();
    }
}
