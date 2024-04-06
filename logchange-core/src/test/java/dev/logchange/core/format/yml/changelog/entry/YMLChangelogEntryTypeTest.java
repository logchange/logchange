package dev.logchange.core.format.yml.changelog.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YMLChangelogEntryTypeTest {

    @Test
    void whenNotFoundThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> YMLChangelogEntryType.of("some_type"));
        assertEquals("Cannot match YMLChangelogEntryType for string: some_type - Available types: [added, changed, deprecated, removed, fixed, security, dependency_update, other].", exception.getMessage());
    }

}