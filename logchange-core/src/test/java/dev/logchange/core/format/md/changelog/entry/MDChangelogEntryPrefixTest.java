package dev.logchange.core.format.md.changelog.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MDChangelogEntryPrefixTest {

    @Test
    void givenSomePrefix_whenToString_thenResultMatchesFormat() {
        //given:
        String prefix = "Some prefix";

        //when:
        String result = MDChangelogEntryPrefix.of(prefix).toString();

        //then:
        assertEquals("**Some prefix** - ", result);
    }

    @Test
    void givenNullPrefix_whenToString_thenResultMatchesFormat() {
        //given:
        String prefix = null;

        //when:
        String result = MDChangelogEntryPrefix.of(prefix).toString();

        //then:
        assertEquals("", result);
    }

    @Test
    void givenEmptyPrefix_whenToString_thenResultMatchesFormat() {
        //given:
        String prefix = "";

        //when:
        String result = MDChangelogEntryPrefix.of(prefix).toString();

        //then:
        assertEquals("", result);
    }

    @Test
    void givenBlankPrefix_whenToString_thenResultMatchesFormat() {
        //given:
        String prefix = "   ";

        //when:
        String result = MDChangelogEntryPrefix.of(prefix).toString();

        //then:
        assertEquals("", result);
    }

    @Test
    void givenSomePrefix_whenToString_thenResultMatchToMD() {
        //given:
        String prefix = "Some prefix";

        //when:
        String result1 = MDChangelogEntryPrefix.of(prefix).toString();
        String result2 = MDChangelogEntryPrefix.of(prefix).toMD();

        //then:
        assertEquals(result1, result2);
    }
}