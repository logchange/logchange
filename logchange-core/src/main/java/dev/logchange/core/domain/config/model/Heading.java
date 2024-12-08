package dev.logchange.core.domain.config.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Heading {
    public static Heading EMPTY = Heading.of(StringUtils.EMPTY);
    private final String value;

    public static Heading of(String heading) {
        if (StringUtils.isBlank(heading)) {
            heading = StringUtils.EMPTY;
        }

        return new Heading(heading);
    }

    public boolean isBlank() {
        return StringUtils.isBlank(value);
    }
}
