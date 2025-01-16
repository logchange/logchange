package dev.logchange.commands.add;

import dev.logchange.core.domain.changelog.model.entry.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BatchModeChangelogEntryProvider implements ChangelogEntryProvider {

    private final AddChangelogEntryBatchModeParams params;

    @Override
    public ChangelogEntry get() {
        return ChangelogEntry.builder()
                .title(getTitle())
                .type(getType())
                .links(getLinks())
                .authors(getAuthors())
                .build();
    }

    private ChangelogEntryTitle getTitle() {
        return ChangelogEntryTitle.of(params.getTitle());
    }

    private ChangelogEntryType getType() {
        return ChangelogEntryType.fromNameIgnoreCase(params.getType());
    }

    private List<ChangelogEntryLink> getLinks() {
        if (StringUtils.isNotBlank(params.getLinkUrl())) {
            return Collections.singletonList(ChangelogEntryLink.of(params.getLinkName(), params.getLinkUrl()));
        }

        return Collections.emptyList();
    }

    private List<ChangelogEntryAuthor> getAuthors() {
        List<String> authors = params.getAuthors();
        if (authors != null && !authors.isEmpty()) {
            return authors.stream()
                    .map(a -> ChangelogEntryAuthor.of("", a, ""))
                    .collect(Collectors.toList());
        }

        String author = params.getAuthor();
        if (StringUtils.isNotBlank(author)) {
            return Collections.singletonList(ChangelogEntryAuthor.of("", author, ""));
        }

        return Collections.emptyList();
    }
}
