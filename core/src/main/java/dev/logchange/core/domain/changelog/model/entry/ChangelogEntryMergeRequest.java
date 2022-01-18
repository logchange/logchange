package dev.logchange.core.domain.changelog.model.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntryMergeRequest {
    private final String value;

    public static ChangelogEntryMergeRequest of(String mergeRequest) {
        if (StringUtils.isBlank(mergeRequest)) {
            new ChangelogEntryMergeRequest(StringUtils.EMPTY);
        }

        return new ChangelogEntryMergeRequest(mergeRequest);
    }
}
