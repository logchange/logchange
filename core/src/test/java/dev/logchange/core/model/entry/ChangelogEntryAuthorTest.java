package dev.logchange.core.model.entry;

import org.junit.jupiter.api.Test;

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

}