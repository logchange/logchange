package dev.logchange.core.domain.changelog.model.version;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VersionTest {

    @Test
    void testCompareToWithTwoReleasedVersions() {
        // given:
        Version v1 = Version.of("1.0.0");
        Version v2 = Version.of("2.0.0");
        Version v3 = Version.of("1.1.0");
        Version v4 = Version.of("1.0.1");
        Version v5 = Version.of("1.0.0");

        // when-then:
        assertTrue(v1.compareTo(v2) < 0, "1.0.0 should be less than 2.0.0");
        assertTrue(v2.compareTo(v1) > 0, "2.0.0 should be greater than 1.0.0");
        assertTrue(v1.compareTo(v3) < 0, "1.0.0 should be less than 1.1.0");
        assertTrue(v1.compareTo(v4) < 0, "1.0.0 should be less than 1.0.1");
        assertEquals(0, v1.compareTo(v5), "1.0.0 should be equal to 1.0.0");
    }

    @Test
    void testCompareToWithUnreleasedAndReleasedVersions() {
        // given:
        Version released = Version.of("1.0.0");
        Version unreleased = Version.of("unreleased-2.0.0");

        // when-then:
        assertTrue(unreleased.compareTo(released) > 0, "Unreleased version should be greater than released version");
        assertTrue(released.compareTo(unreleased) < 0, "Released version should be less than unreleased version");
    }

    @Test
    void testCompareToWithTwoUnreleasedVersions() {
        // given:
        Version unreleased1 = Version.of("unreleased-1.0.0");
        Version unreleased2 = Version.of("unreleased-2.0.0");
        Version justUnreleased = Version.of("unreleased");

        // when-then:
        assertTrue(unreleased1.compareTo(unreleased2) < 0, "unreleased-1.0.0 should be less than unreleased-2.0.0");
        assertTrue(unreleased2.compareTo(unreleased1) > 0, "unreleased-2.0.0 should be greater than unreleased-1.0.0");
        assertTrue(justUnreleased.compareTo(unreleased1) > 0, "unreleased should be greater than unreleased-1.0.0");
        assertTrue(unreleased1.compareTo(justUnreleased) < 0, "unreleased-1.0.0 should be less than unreleased");
    }

    @Test
    void testCompareToWithComplexVersions() {
        // given:
        Version v1 = Version.of("1.9.0");
        Version v2 = Version.of("1.10.0");
        Version v3 = Version.of("1.9.1");
        Version v4 = Version.of("1.9.0-SNAPSHOT");

        // when-then:
        assertTrue(v1.compareTo(v2) < 0, "1.9.0 should be less than 1.10.0");
        assertTrue(v1.compareTo(v3) < 0, "1.9.0 should be less than 1.9.1");
        assertTrue(v1.compareTo(v4) > 0, "1.9.0 should be greater than 1.9.0-SNAPSHOT");
    }
}
