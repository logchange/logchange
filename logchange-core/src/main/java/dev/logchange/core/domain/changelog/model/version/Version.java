package dev.logchange.core.domain.changelog.model.version;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.artifact.versioning.ComparableVersion;

import java.util.Comparator;

@Log
@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Version implements Comparable<Version> {
    public static final String UNRELEASED = "unreleased";

    private final String value;

    public static Version of(String version) {
        if (StringUtils.isBlank(version)) {
            log.severe("Version cannot be blank!");
            throw new IllegalArgumentException("Version cannot be blank!");
        }
        return new Version(version);
    }

    @Override
    public int compareTo(Version other) {
        if (UNRELEASED.equals(value)) {
            return 1;
        }

        if (UNRELEASED.equals(other.value)) {
            return -1;
        }

        if (value.equals(other.value)) {
            return 0;
        }

        return Comparator.comparing(Version::getComparableVersion)
                .compare(this, other);
    }

    private ComparableVersion getComparableVersion() {
        return new ComparableVersion(value);
    }

    public String getDirName() {
        if (UNRELEASED.equals(value)) {
            return value;
        } else {
            return "v" + value;
        }
    }

    public boolean isUnreleased() {
        return UNRELEASED.equals(value);
    }
}
