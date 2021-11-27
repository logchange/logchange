package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.model.entry.ChangelogEntry;
import dev.logchange.core.model.entry.ChangelogEntryAuthor;
import dev.logchange.core.model.entry.ChangelogEntryLink;
import dev.logchange.core.model.entry.ChangelogEntryType;
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
        String result = new MDChangelogEntriesGroup(type, entries).toString();

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
        String result = new MDChangelogEntriesGroup(type, entries).toString();

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
        String result1 = new MDChangelogEntriesGroup(type, entries).toString();
        String result2 = new MDChangelogEntriesGroup(type, entries).toMD();

        //then:
        Assertions.assertEquals(result1, result2);
    }


    private ChangelogEntry getSomeEntry(ChangelogEntryType type) {
        return ChangelogEntry.of(
                "Some Title",
                type,
                "567",
                "890",
                new ChangelogEntryLink("Some link", "https//google.com"),
                ChangelogEntryAuthor.of("Some Name", "Nick", "https://nick.name"));
    }

}