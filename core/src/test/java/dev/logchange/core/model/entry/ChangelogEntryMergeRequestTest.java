package dev.logchange.core.model.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChangelogEntryMergeRequestTest {
    @Test
    void givenGoodMergeRequest_whenOf_thenObjectCreatedAndValueIsCorrect() {
        //given:
        String mr = "369";

        //when:
        ChangelogEntryMergeRequest changelogEntryMergeRequest = ChangelogEntryMergeRequest.of(mr);

        //then:
        assertEquals(mr, changelogEntryMergeRequest.getValue());
    }

    @Test
    void givenEmptyMergeRequest_whenOf_thenExceptionIsThrown() {
        //given:
        String mr = "";

        //when-then:
        assertThrows(IllegalArgumentException.class, () -> ChangelogEntryMergeRequest.of(mr));
    }

    @Test
    void givenBlankTitle_whenOf_thenExceptionIsThrown() {
        //given:
        String mr = " ";

        //when-then:
        assertThrows(IllegalArgumentException.class, () -> ChangelogEntryMergeRequest.of(mr));
    }
}