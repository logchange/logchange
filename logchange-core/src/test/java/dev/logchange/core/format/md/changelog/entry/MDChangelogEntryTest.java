package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.*;
import dev.logchange.core.domain.config.model.Config;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MDChangelogEntryTest {

    @Test
    void givenBasicEntry_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntry entry = getSomeEntry(null);

        //when:

        String result = new MDChangelogEntry(entry, Config.EMPTY, MDChangelogEntryPrefix.EMPTY).toString();

        //then:
        assertEquals("- Some Title !567 #890 [Some link](https//google.com) ([Some Name](https://nick.name) @Nick)", result);
    }

    @Test
    void givenSomeEntry_whenToString_thenResultMatchesToMD() {
        //given:
        ChangelogEntry entry = getSomeEntry(null);

        //when:
        String result1 = new MDChangelogEntry(entry, Config.EMPTY, MDChangelogEntryPrefix.EMPTY).toString();
        String result2 = new MDChangelogEntry(entry, Config.EMPTY, MDChangelogEntryPrefix.EMPTY).toMD();

        //then:
        assertEquals(result1, result2);
    }

    @Test
    void givenBasicEntryWithPrefix_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntry entry = getSomeEntry("Some prefix");
        MDChangelogEntryPrefix prefix = MDChangelogEntryPrefix.of("Some prefix");

        //when:
        String result = new MDChangelogEntry(entry, Config.EMPTY, prefix).toString();

        //then:
        assertEquals("- **Some prefix** - Some Title !567 #890 [Some link](https//google.com) ([Some Name](https://nick.name) @Nick)", result);
    }

    @Test
    void givenSomeEntryWithPrefix_whenToString_thenResultMatchesToMD() {
        //given:
        ChangelogEntry entry = getSomeEntry("Some prefix");
        MDChangelogEntryPrefix prefix = MDChangelogEntryPrefix.of("Some prefix");

        //when:
        String result1 = new MDChangelogEntry(entry, Config.EMPTY, prefix).toString();
        String result2 = new MDChangelogEntry(entry, Config.EMPTY, prefix).toMD();

        //then:
        assertEquals(result1, result2);
    }

    private ChangelogEntry getSomeEntry(String prefix) {
        return ChangelogEntry.builder()
                .prefix(prefix)
                .title(ChangelogEntryTitle.of("Some Title"))
                .type(ChangelogEntryType.fromNameIgnoreCase("added"))
                .mergeRequest(ChangelogEntryMergeRequest.of(567L))
                .issue(890L)
                .link(new ChangelogEntryLink("Some link", "https//google.com"))
                .author(ChangelogEntryAuthor.of("Some Name", "Nick", "https://nick.name"))
                .importantNotes(Collections.emptyList())
                .configurations(Collections.emptyList())
                .build();
    }

}
