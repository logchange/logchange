package dev.logchange.core.domain.changelog.model.entry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChangelogEntryAuthorTest {

    @Test
    void givenNameNickUrl_whenOf_thenObjectCreatedAndValueIsCorrect() {
        //given:
        String name = "Some Name";
        String nick = "marwin1991";
        String url = "https://logchange.dev/";

        //when:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of(name, nick, url);

        //then:
        assertEquals(name, author.getName());
        assertEquals(nick, author.getNick());
        assertEquals(url, author.getUrl());
    }

    @Test
    void givenAllBlank_whenOf_thenObjectCreatedAndValueIsCorrect() {
        //given:
        String name = "";
        String nick = "         ";
        String url = " ";

        //when-then:
        assertThrows(IllegalArgumentException.class, () -> ChangelogEntryAuthor.of(name, nick, url));
    }

    @Test
    void givenNameBlank_whenOf_thenObjectCreatedAndValueIsCorrectAndNameIsEmpty() {
        //given:
        String name = " ";
        String nick = "marwin1991";
        String url = "";

        //when:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of(name, nick, url);

        //then:
        assertEquals("", author.getName());
        assertEquals(nick, author.getNick());
        assertEquals(url, author.getUrl());
    }

    @Test
    void givenNickBlank_whenOf_thenObjectCreatedAndValueIsCorrectAndNickIsEmpty() {
        //given:
        String name = "";
        String nick = " ";
        String url = "https://logchange.dev/";

        //when:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of(name, nick, url);

        //then:
        assertEquals(name, author.getName());
        assertEquals("", author.getNick());
        assertEquals(url, author.getUrl());
    }

    @Test
    void givenUrlBlank_whenOf_thenObjectCreatedAndValueIsCorrectAndUrlIsEmpty() {
        //given:
        String name = "";
        String nick = "marwin1991";
        String url = " ";

        //when:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of(name, nick, url);

        //then:
        assertEquals(name, author.getName());
        assertEquals(nick, author.getNick());
        assertEquals("", author.getUrl());
    }

    @Test
    void givenOnlyName_shouldCreateAuthorWithCorrectNameAndBlankNickAndUrl() {
        // given:
        String input = " John doe ";
        String expected = "John doe";

        // when
        ChangelogEntryAuthor changelogEntryAuthor = ChangelogEntryAuthor.of(input);

        // then:
        assertEquals(expected, changelogEntryAuthor.getName());
        assertEquals("", changelogEntryAuthor.getNick());
        assertEquals("", changelogEntryAuthor.getUrl());
    }

    @Test
    void givenOnlyNick_shouldCreateAuthorWithCorrectNickAndBlankNameAndUrl() {
        // given:
        String input = "; jdoe ";
        String expected = "jdoe";

        // when
        ChangelogEntryAuthor changelogEntryAuthor = ChangelogEntryAuthor.of(input);

        // then:
        assertEquals("", changelogEntryAuthor.getName());
        assertEquals(expected, changelogEntryAuthor.getNick());
        assertEquals("", changelogEntryAuthor.getUrl());
    }

    @Test
    void givenOnlyUrl_shouldCreateAuthorWithCorrectUrlAndBlankNameAndNick() {
        // given:
        String input = "; ; https://github.com/logchange ";
        String expected = "https://github.com/logchange";

        // when
        ChangelogEntryAuthor changelogEntryAuthor = ChangelogEntryAuthor.of(input);

        // then:
        assertEquals("", changelogEntryAuthor.getName());
        assertEquals("", changelogEntryAuthor.getNick());
        assertEquals(expected, changelogEntryAuthor.getUrl());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenBlankString_shouldThrowException(String input) {
        // when
        Exception ex = assertThrows(IllegalArgumentException.class, () ->ChangelogEntryAuthor.of(input));

        // then:
        assertEquals("Cannot create blank author", ex.getMessage());
    }

    @Test
    void givenFullAuthorInformation_shouldFillAllObjectProperties() {
        // given:
        String input = "John Doe; jdoe; https://github.com/logchange ";
        String expectedName = "John Doe";
        String expectedNick = "jdoe";
        String expectedURL = "https://github.com/logchange";

        // when
        ChangelogEntryAuthor changelogEntryAuthor = ChangelogEntryAuthor.of(input);

        // then:
        assertEquals(expectedName, changelogEntryAuthor.getName());
        assertEquals(expectedNick, changelogEntryAuthor.getNick());
        assertEquals(expectedURL, changelogEntryAuthor.getUrl());
    }
}