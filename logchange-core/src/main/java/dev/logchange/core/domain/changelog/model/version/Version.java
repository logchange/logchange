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

    /**
     * Separates <unreleasedVersionDir> from a version, for example,
     * if unreleasedVersionDir is "unreleased" and the version to release is 1.0.0,
     * we have to check if dir unreleased-1.0.0 exists
     */
    public static final String UNRELEASED_DIR_SEPARATOR = "-";
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
        if (isUnreleased() && other.isUnreleased()) {
            if (UNRELEASED.equals(value)) {
              return 1;
            }

            if (UNRELEASED.equals(other.value)) {
                return -1;
            }

            return Comparator
                    .comparing(Version::getComparableVersion)
                    .compare(this, other);
        }

        if (isUnreleased()) {
            return 1;
        }

        if (other.isUnreleased()) {
            return -1;
        }

        if (value.equals(other.value)) {
            return 0;
        }

        return Comparator.comparing(Version::getComparableVersion)
                .compare(this, other);
    }

    private ComparableVersion getComparableVersion() {
        if (isUnreleased()) {
            return new ComparableVersion(value.replace(UNRELEASED + UNRELEASED_DIR_SEPARATOR, ""));
        }
        return new ComparableVersion(value);
    }

    public String getDirName() {
        if (isUnreleased()) {
            return value;
        } else {
            return "v" + value;
        }
    }

    public boolean isUnreleased() {
        return value.startsWith(UNRELEASED);
    }

    @Override
    public String toString() {
        return value;
    }
}
