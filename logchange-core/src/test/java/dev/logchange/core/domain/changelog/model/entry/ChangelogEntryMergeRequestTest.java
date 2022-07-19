package dev.logchange.core.domain.changelog.model.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangelogEntryMergeRequestTest {
    @Test
    void givenGoodMergeRequest_whenOf_thenObjectCreatedAndValueIsCorrect() {
        //given:
        Long mr = 369L;

        //when:
        ChangelogEntryMergeRequest changelogEntryMergeRequest = ChangelogEntryMergeRequest.of(mr);

        //then:
        assertEquals(mr, changelogEntryMergeRequest.getValue());
    }
}