package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryAuthor;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryLink;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MDChangelogEntryTest {

    @Test
    void givenBasicEntry_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntry entry = ChangelogEntry.of(
                "Some Title",
                ChangelogEntryType.ADDED,
                "567",
                "890",
                new ChangelogEntryLink("Some link", "https//google.com"),
                ChangelogEntryAuthor.of("Some Name", "Nick", "https://nick.name"));

        //when:
        String result = new MDChangelogEntry(entry).toString();

        //then:
        assertEquals("- Some Title !567 #890 [Some link](https//google.com) ([Some Name](https://nick.name) @Nick)", result);
    }

    @Test
    void givenSomeEntry_whenToString_thenResultMatchesToMD() {
        //given:
        ChangelogEntry entry = ChangelogEntry.of(
                "Some Title",
                ChangelogEntryType.ADDED,
                "567",
                "890",
                new ChangelogEntryLink("Some link", "https//google.com"),
                ChangelogEntryAuthor.of("Some Name", "Nick", "https://nick.name"));

        //when:
        String result1 = new MDChangelogEntry(entry).toString();
        String result2 = new MDChangelogEntry(entry).toMD();

        //then:
        assertEquals(result1, result2);
    }

}