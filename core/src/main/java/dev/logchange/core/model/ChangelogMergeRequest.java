package dev.logchange.core.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogMergeRequest {
    private final String value;

    public static ChangelogMergeRequest of(String mergeRequest) {
        if (StringUtils.isBlank(mergeRequest)) {
            throw new IllegalArgumentException("Merge request cannot be blank!");
        }

        return new ChangelogMergeRequest(mergeRequest);
    }
}
