package dev.logchange.core.format.md.changelog.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MDChangelogEntryMergeRequestTest {

    @Test
    void givenSomeMergeRequestNumber_whenToString_thenResultMatchFormat() {
        //given:
        String mr = "567";

        //when:
        String result = new MDChangelogEntryMergeRequest(mr).toString();

        // then
        assertEquals("!567", result);
    }

    @Test
    void givenEmptyMergeRequestNumber_whenToString_thenResultMatchFormat() {
        //given:
        String mr = "";

        //when:
        String result = new MDChangelogEntryMergeRequest(mr).toString();

        // then
        assertEquals("", result);
    }

    @Test
    void givenNullMergeRequestNumber_whenToString_thenResultMatchFormat() {
        //given:
        String mr = null;

        //when:
        String result = new MDChangelogEntryMergeRequest(mr).toString();

        // then
        assertEquals("", result);
    }

    @Test
    void givenSomeMergeRequestNumber_whenToString_thenResultMatchToMD() {
        //given:
        String mr = "567";

        //when:
        String result1 = new MDChangelogEntryMergeRequest(mr).toString();
        String result2 = new MDChangelogEntryMergeRequest(mr).toString();

        // then
        assertEquals(result1, result2);
    }


}