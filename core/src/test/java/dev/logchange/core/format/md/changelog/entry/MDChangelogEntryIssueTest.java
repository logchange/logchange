package dev.logchange.core.format.md.changelog.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MDChangelogEntryIssueTest {

    @Test
    void givenSomeIssueNumber_whenToString_thenResultMatchesFormat() {
        //given:
        String issue = "567";

        //when:
        String result = new MDChangelogEntryIssue(issue).toString();

        // then
        assertEquals("#567", result);
    }

    @Test
    void givenEmptyIssueNumber_whenToString_thenResultMatchesFormat() {
        //given:
        String issue = "";

        //when:
        String result = new MDChangelogEntryIssue(issue).toString();

        // then
        assertEquals("", result);
    }

    @Test
    void givenNullIssueNumber_whenToString_thenResultMatchesFormat() {
        //given:
        String issue = null;

        //when:
        String result = new MDChangelogEntryIssue(issue).toString();

        // then
        assertEquals("", result);
    }

    @Test
    void givenSomeIssueNumber_whenToString_thenResultMatchToMD() {
        //given:
        String issue = "567";

        //when:
        String result1 = new MDChangelogEntryIssue(issue).toString();
        String result2 = new MDChangelogEntryIssue(issue).toMD();

        // then
        assertEquals(result1, result2);
    }
}