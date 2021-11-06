package dev.logchange.core.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogTitle {
    private final String value;

    public static ChangelogTitle of(String title) {
        if (StringUtils.isBlank(title)) {
            throw new IllegalArgumentException("Title cannot be blank!");
        }

        return new ChangelogTitle(title);
    }
}
