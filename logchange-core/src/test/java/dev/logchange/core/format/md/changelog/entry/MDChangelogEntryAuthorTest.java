package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryAuthor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MDChangelogEntryAuthorTest {

    @Test
    void givenAuthorWithName_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("FirstName LastName", "", "");

        //when:
        String result = new MDChangelogEntryAuthor(author).toString();

        //then:
        assertEquals("(FirstName LastName)", result);
    }

    @Test
    void givenAuthorWithNameAndNickName_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("FirstName LastName", "NickName", "");

        //when:
        String result = new MDChangelogEntryAuthor(author).toString();

        //then:
        assertEquals("(FirstName LastName @NickName)", result);
    }

    @Test
    void givenAuthorWithNameAndNickNameAndUrl_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("FirstName LastName", "NickName", "https://google.com");

        //when:
        String result = new MDChangelogEntryAuthor(author).toString();

        //then:
        assertEquals("([FirstName LastName](https://google.com) @NickName)", result);
    }

    @Test
    void givenAuthorWithNickNameAndUrl_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("", "NickName", "https://google.com");

        //when:
        String result = new MDChangelogEntryAuthor(author).toString();

        //then:
        assertEquals("(@NickName [LINK](https://google.com))", result);
    }

    @Test
    void givenAuthorWithNameAndUrl_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("FirstName LastName", "", "https://google.com");

        //when:
        String result = new MDChangelogEntryAuthor(author).toString();

        //then:
        assertEquals("([FirstName LastName](https://google.com))", result);
    }

    @Test
    void givenAuthorWithdUrl_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("", "", "https://google.com");

        //when:
        String result = new MDChangelogEntryAuthor(author).toString();

        //then:
        assertEquals("([LINK](https://google.com))", result);
    }

    @Test
    void givenSomeAuthor_whenToString_thenResultMatchToMD() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("FirstName LastName", "NickName", "https://google.com");

        //when:
        String result1 = new MDChangelogEntryAuthor(author).toString();
        String result2 = new MDChangelogEntryAuthor(author).toMD();

        //then:
        assertEquals(result2, result1);
    }


}