package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.model.ChangelogEntryLink;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MDChangelogEntryLinksTest {

    @Test
    void givenOneLink_whenToString_thenResultMatchesFormat() {
        //given:
        List<ChangelogEntryLink> links = Collections.singletonList(new ChangelogEntryLink("some link", "https://google.com"));

        //when:
        String result = new MDChangelogEntryLinks(links).toString();

        //then:
        assertEquals("[some link](https://google.com)", result);
    }

    @Test
    void givenTwoLinks_whenToString_thenResultMatchesFormat() {
        //given:
        List<ChangelogEntryLink> links = Arrays.asList(
                new ChangelogEntryLink("some link1", "https://google.com"),
                new ChangelogEntryLink("some link2", "https://google.com"));

        //when:
        String result = new MDChangelogEntryLinks(links).toString();

        //then:
        assertEquals("[some link1](https://google.com) [some link2](https://google.com)", result);
    }

    @Test
    void givenNoLinks_whenToString_thenResultEmpty() {
        //given:
        List<ChangelogEntryLink> links = Collections.emptyList();

        //when:
        String result = new MDChangelogEntryLinks(links).toString();

        //then:
        assertEquals("", result);
    }

    @Test
    void givenTwoLinks_whenToString_thenResultMatchToMD() {
        //given:
        List<ChangelogEntryLink> links = Arrays.asList(
                new ChangelogEntryLink("some link1", "https://google.com"),
                new ChangelogEntryLink("some link2", "https://google.com"));

        //when:
        String result1 = new MDChangelogEntryLinks(links).toString();
        String result2 = new MDChangelogEntryLinks(links).toMD();

        //then:
        assertEquals(result1, result2);
    }

}