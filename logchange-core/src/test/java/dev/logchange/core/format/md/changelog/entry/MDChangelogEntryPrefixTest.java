package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryPrefix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MDChangelogEntryPrefixTest {
    @Test
    void givenSomePrefix_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryPrefix prefix = ChangelogEntryPrefix.of("Some prefix");

        //when:
        String result = new MDChangelogEntryPrefix(prefix).toString();

        //then:
        assertEquals("**Some prefix** - ", result);
    }

    @Test
    void givenNullPrefix_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryPrefix prefix = null;

        //when:
        String result = new MDChangelogEntryPrefix(prefix).toString();

        //then:
        assertEquals("", result);
    }

    @Test
    void givenSomePrefix_whenToString_thenResultMatchToMD() {
        //given:
        ChangelogEntryPrefix prefix = ChangelogEntryPrefix.of("Some prefix");

        //when:
        String result1 = new MDChangelogEntryPrefix(prefix).toString();
        String result2 = new MDChangelogEntryPrefix(prefix).toMD();

        //then:
        assertEquals(result1, result2);
    }
}