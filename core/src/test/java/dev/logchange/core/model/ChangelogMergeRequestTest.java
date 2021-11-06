package dev.logchange.core.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChangelogMergeRequestTest {
    @Test
    void givenGoodMergeRequest_whenOf_thenObjectCreatedAndValueIsCorrect() {
        //given:
        String mr = "369";

        //when:
        ChangelogMergeRequest changelogMergeRequest = ChangelogMergeRequest.of(mr);

        //then:
        assertEquals(mr, changelogMergeRequest.getValue());
    }

    @Test
    void givenEmptyMergeRequest_whenOf_thenExceptionIsThrown() {
        //given:
        String mr = "";

        //when-then:
        assertThrows(IllegalArgumentException.class, () -> ChangelogMergeRequest.of(mr));
    }

    @Test
    void givenBlankTitle_whenOf_thenExceptionIsThrown() {
        //given:
        String mr = " ";

        //when-then:
        assertThrows(IllegalArgumentException.class, () -> ChangelogMergeRequest.of(mr));
    }
}