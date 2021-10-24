package dev.logchange.core.format.md.changelog.entry;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MDChangelogEntryIssuesTest {

    @Test
    void givenOneIssue_whenToString_thenResultMatchesFormat() {
        //given:
        List<String> issues = Collections.singletonList("567");

        //when:
        String result = new MDChangelogEntryIssues(issues).toString();

        //then:
        assertEquals("#567", result);
    }

    @Test
    void givenTwoIssue_whenToString_thenResultMatchesFormat() {
        //given:
        List<String> issues = Arrays.asList("567", "789");

        //when:
        String result = new MDChangelogEntryIssues(issues).toString();

        //then:
        assertEquals("#567 #789", result);
    }

    @Test
    void givenNoIssue_whenToString_thenResultEmpty() {
        //given:
        List<String> issues = Collections.emptyList();

        //when:
        String result = new MDChangelogEntryIssues(issues).toString();

        //then:
        assertEquals("", result);
    }

    @Test
    void givenTwoIssue_whenToString_thenResultMatchToMD() {
        //given:
        List<String> issues = Arrays.asList("567", "789");

        //when:
        String result1 = new MDChangelogEntryIssues(issues).toString();
        String result2 = new MDChangelogEntryIssues(issues).toMD();

        //then:
        assertEquals(result1, result2);
    }

}