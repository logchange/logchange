package dev.logchange.core.domain.changelog.model.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChangelogEntryTitleTest {

    @Test
    void givenGoodTitle_whenOf_thenObjectCreatedAndValueIsCorrect() {
        //given:
        String title = "some title";

        //when:
        ChangelogEntryTitle changelogEntryTitle = ChangelogEntryTitle.of(title);

        //then:
        assertEquals(title, changelogEntryTitle.getValue());
    }

    @Test
    void givenEmptyTitle_whenOf_thenExceptionIsThrown() {
        //given:
        String title = "";

        //when-then:
        assertThrows(IllegalArgumentException.class, () -> ChangelogEntryTitle.of(title));
    }

    @Test
    void givenBlankTitle_whenOf_thenExceptionIsThrown() {
        //given:
        String title = " ";

        //when-then:
        assertThrows(IllegalArgumentException.class, () -> ChangelogEntryTitle.of(title));
    }

}