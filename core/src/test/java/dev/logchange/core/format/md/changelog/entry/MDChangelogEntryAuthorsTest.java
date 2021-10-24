package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.model.ChangelogEntryAuthor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MDChangelogEntryAuthorsTest {


    @Test
    void givenOneAuthor_whenToString_tenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = new ChangelogEntryAuthor("FirstName LastName", "NickName", "https://google.com");
        List<ChangelogEntryAuthor> authors = Collections.singletonList(author);

        //when:
        String result = new MDChangelogEntryAuthors(authors).toString();

        //then:
        assertEquals("([FirstName LastName](https://google.com) @NickName)", result);
    }

    @Test
    void givenTwoAuthor_whenToString_tenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = new ChangelogEntryAuthor("FirstName LastName", "NickName", "https://google.com");
        List<ChangelogEntryAuthor> authors = Arrays.asList(author, author);

        //when:
        String result = new MDChangelogEntryAuthors(authors).toString();

        //then:
        assertEquals("([FirstName LastName](https://google.com) @NickName) ([FirstName LastName](https://google.com) @NickName)", result);
    }

    @Test
    void givenSomeAuthors_whenToString_thenResultMatchToMD() {
        //given:
        ChangelogEntryAuthor author = new ChangelogEntryAuthor("FirstName LastName", "NickName", "https://google.com");
        List<ChangelogEntryAuthor> authors = Arrays.asList(author, author);

        //when:
        String result1 = new MDChangelogEntryAuthors(authors).toString();
        String result2 = new MDChangelogEntryAuthors(authors).toMD();

        //then:
        assertEquals(result2, result1);
    }

}