package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryMergeRequest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MDChangelogEntryMergeRequestsTest {
    @Test
    void givenOneMergeRequest_whenToString_thenResultMatchesFormat() {
        //given:
        List<ChangelogEntryMergeRequest> mergeRequests = Collections.singletonList(ChangelogEntryMergeRequest.of(567L));

        //when:
        String result = new MDChangelogEntryMergeRequests(mergeRequests).toString();

        //then:
        assertEquals("!567", result);
    }

    @Test
    void givenTwoMergeRequests_whenToString_thenResultMatchesFormat() {
        //given:
        List<ChangelogEntryMergeRequest> mergeRequests = Arrays.asList(
                ChangelogEntryMergeRequest.of(567L),
                ChangelogEntryMergeRequest.of(234L));

        //when:
        String result = new MDChangelogEntryMergeRequests(mergeRequests).toString();

        //then:
        assertEquals("!567 !234", result);
    }

    @Test
    void givenNoMergeRequests_whenToString_thenResultMatchesFormat() {
        //given:
        List<ChangelogEntryMergeRequest> mergeRequests = Collections.emptyList();

        //when:
        String result = new MDChangelogEntryMergeRequests(mergeRequests).toString();

        //then:
        assertEquals("", result);
    }

    @Test
    void givenTwoMergeRequests_whenToString_thenResultMatchToMD() {
        //given:
        List<ChangelogEntryMergeRequest> mergeRequests = Arrays.asList(
                ChangelogEntryMergeRequest.of(567L),
                ChangelogEntryMergeRequest.of(234L));

        //when:
        String result1 = new MDChangelogEntryMergeRequests(mergeRequests).toString();
        String result2 = new MDChangelogEntryMergeRequests(mergeRequests).toMD();

        //then:
        assertEquals(result1, result2);
    }
}