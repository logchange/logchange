package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.entry.*;
import dev.logchange.core.domain.config.model.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MDChangelogEntriesGroupTest {

    @Test
    void givenOneEntry_whenToString_thenResultMatches() {
        //given:
        ChangelogEntryType type = ChangelogEntryType.ADDED;
        List<ChangelogEntry> entries = Collections.singletonList(getSomeEntry(type));

        //when:
        String result = new MDChangelogEntriesGroup(type, entries, Config.EMPTY).toString();

        //then:
        Assertions.assertEquals("### Added (1 change)\n" +
                "\n" +
                "- Some Title !567 #890 [Some link](https//google.com) ([Some Name](https://nick.name) @Nick)\n" +
                "\n", result);
    }

    @Test
    void givenTwoEntries_whenToString_thenResultMatches() {
        //given:
        ChangelogEntryType type = ChangelogEntryType.ADDED;
        List<ChangelogEntry> entries = Arrays.asList(getSomeEntry(type), getSomeEntry(type));

        //when:
        String result = new MDChangelogEntriesGroup(type, entries, Config.EMPTY).toString();

        //then:
        Assertions.assertEquals("### Added (2 changes)\n" +
                "\n" +
                "- Some Title !567 #890 [Some link](https//google.com) ([Some Name](https://nick.name) @Nick)\n" +
                "- Some Title !567 #890 [Some link](https//google.com) ([Some Name](https://nick.name) @Nick)\n" +
                "\n", result);
    }

    @Test
    void givenTwoEntries_whenToString_thenResultMatchesToMD() {
        //given:
        ChangelogEntryType type = ChangelogEntryType.ADDED;
        List<ChangelogEntry> entries = Arrays.asList(getSomeEntry(type), getSomeEntry(type));

        //when:
        String result1 = new MDChangelogEntriesGroup(type, entries, Config.EMPTY).toString();
        String result2 = new MDChangelogEntriesGroup(type, entries, Config.EMPTY).toMD();

        //then:
        Assertions.assertEquals(result1, result2);
    }


    private ChangelogEntry getSomeEntry(ChangelogEntryType type) {
        return ChangelogEntry.builder()
                .title(ChangelogEntryTitle.of("Some Title"))
                .type(type)
                .mergeRequest(ChangelogEntryMergeRequest.of(567L))
                .issue(890L)
                .link(new ChangelogEntryLink("Some link", "https//google.com"))
                .author(ChangelogEntryAuthor.of("Some Name", "Nick", "https://nick.name"))
                .importantNotes(Collections.emptyList())
                .configurations(Collections.emptyList())
                .build();
    }

}