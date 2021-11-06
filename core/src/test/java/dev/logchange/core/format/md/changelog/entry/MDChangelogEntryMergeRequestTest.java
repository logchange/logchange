package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.model.ChangelogMergeRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MDChangelogEntryMergeRequestTest {

    @Test
    void givenSomeMergeRequestNumber_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogMergeRequest mr = ChangelogMergeRequest.of("567");

        //when:
        String result = new MDChangelogEntryMergeRequest(mr).toString();

        //then:
        assertEquals("!567", result);
    }

    @Test
    void givenNullMergeRequestNumber_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogMergeRequest mr = null;

        //when:
        String result = new MDChangelogEntryMergeRequest(mr).toString();

        //then:
        assertEquals("", result);
    }

    @Test
    void givenSomeMergeRequestNumber_whenToString_thenResultMatchToMD() {
        //given:
        ChangelogMergeRequest mr = ChangelogMergeRequest.of("567");

        //when:
        String result1 = new MDChangelogEntryMergeRequest(mr).toString();
        String result2 = new MDChangelogEntryMergeRequest(mr).toMD();

        //then:
        assertEquals(result1, result2);
    }


}