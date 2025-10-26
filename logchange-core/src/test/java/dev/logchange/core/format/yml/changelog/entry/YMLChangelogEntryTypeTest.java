package dev.logchange.core.format.yml.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YMLChangelogEntryTypeTest {

    @Test
    void whenNotFoundThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> YMLChangelogEntryType.of("some_type"));
        assertEquals("Cannot match YMLChangelogEntryType for string: some_type - Available types: [added, changed, deprecated, removed, fixed, security, dependency_update, other].", exception.getMessage());
    }

    @Test
    void toReturnsProperDomainType() {
        // given:
        YMLChangelogEntryType ymlType = YMLChangelogEntryType.of("added");

        // when:
        ChangelogEntryType domainType = ymlType.to();

        // then:
        assertEquals("added", domainType.getKey());
    }

    @Test
    void ofFromDomainTypeThenToReturnsSameInstanceByKey() {
        // given:
        ChangelogEntryType original = ChangelogEntryType.fromNameIgnoreCase("changed");
        YMLChangelogEntryType ymlType = YMLChangelogEntryType.of(original);

        // when:
        ChangelogEntryType converted = ymlType.to();

        // then:
        assertEquals(original.getKey(), converted.getKey());
    }
}