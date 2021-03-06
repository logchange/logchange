package dev.logchange.core.domain.changelog.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogHeading {
    private final String value;

    public static ChangelogHeading EMPTY = ChangelogHeading.of(StringUtils.EMPTY);

    public boolean isBlank() {
        return StringUtils.isBlank(value);
    }

    public static ChangelogHeading of(String heading) {
        if (StringUtils.isBlank(heading)) {
            heading = StringUtils.EMPTY;
        }

        return new ChangelogHeading(heading);
    }
}
