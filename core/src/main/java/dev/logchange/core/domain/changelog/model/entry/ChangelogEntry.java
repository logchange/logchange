package dev.logchange.core.domain.changelog.model.entry;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
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

    public static ChangelogEntry of(
            String title,
            ChangelogEntryType type,
            String mergeRequest,
            String issue,
            ChangelogEntryLink link,
            ChangelogEntryAuthor author) {


        return ChangelogEntry.builder()
                .title(ChangelogEntryTitle.of(title))
                .type(type)
                .mergeRequest(ChangelogEntryMergeRequest.of(mergeRequest))
                .issue(issue)
                .link(link)
                .author(author)
                .importantNotes(Collections.emptyList())
                .configurations(Collections.emptyList())
                .build();
    }

    public static ChangelogEntry of(
            String title,
            ChangelogEntryType type,
            String mergeRequest,
            List<String> issues,
            List<ChangelogEntryLink> links,
            List<ChangelogEntryAuthor> authors,
            List<String> importantNotes,
            List<ChangelogEntryConfiguration> configurations
    ) {
        return ChangelogEntry.builder()
                .title(ChangelogEntryTitle.of(title))
                .type(type)
                .mergeRequest(ChangelogEntryMergeRequest.of(mergeRequest))
                .issues(issues)
                .links(links)
                .authors(authors)
                .importantNotes(importantNotes)
                .configurations(configurations)
                .build();
    }

}
