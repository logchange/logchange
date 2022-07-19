package dev.logchange.core.domain.changelog.model.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntryMergeRequest {
    private final Long value;

    public static ChangelogEntryMergeRequest of(Long mergeRequest) {
        if (mergeRequest == null) {
            throw new IllegalArgumentException("Merge request cannot be null!");
        }

        return new ChangelogEntryMergeRequest(mergeRequest);
    }
}
