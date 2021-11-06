package dev.logchange.core.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChangelogTitleTest {

    @Test
    void givenGoodTitle_whenOf_thenObjectCreatedAndValueIsCorrect() {
        //given:
        String title = "some title";

        //when:
        ChangelogTitle changelogTitle = ChangelogTitle.of(title);

        //then:
        assertEquals(title, changelogTitle.getValue());
    }

    @Test
    void givenEmptyTitle_whenOf_thenExceptionIsThrown() {
        //given:
        String title = "";

        //when-then:
        assertThrows(IllegalArgumentException.class, () -> ChangelogTitle.of(title));
    }

    @Test
    void givenBlankTitle_whenOf_thenExceptionIsThrown() {
        //given:
        String title = " ";

        //when-then:
        assertThrows(IllegalArgumentException.class, () -> ChangelogTitle.of(title));
    }

}