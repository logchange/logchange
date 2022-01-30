package dev.logchange.core.domain.changelog.model.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogEntryTitle {
    private final String value;

    public static ChangelogEntryTitle of(String title) {
        if (StringUtils.isBlank(title)) {
            throw new IllegalArgumentException("Title cannot be blank!");
        }

        return new ChangelogEntryTitle(title);
    }
}
