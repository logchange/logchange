package dev.logchange.core.domain.changelog.model.version;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReleaseDateTime implements Comparable<ReleaseDateTime> {
    private final OffsetDateTime value;

    public static ReleaseDateTime of(OffsetDateTime value) {
        if (value == null) {
            throw new IllegalArgumentException("Release date value cannot be null!");
        }
        return new ReleaseDateTime(value);
    }

    @Override
    public String toString() {
        return value.toLocalDate().toString();
    }

    @Override
    public int compareTo(ReleaseDateTime o) {
        if (o == null) {
            return 1;
        }
        return value.compareTo(o.value);
    }
}
