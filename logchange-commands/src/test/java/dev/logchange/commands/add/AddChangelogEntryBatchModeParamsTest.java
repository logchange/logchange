package dev.logchange.commands.add;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class AddChangelogEntryBatchModeParamsTest {

    @Test
    void shouldCreateChangelogEntryBatchModeParamsWithAuthor() {
        // when:
        AddChangelogEntryBatchModeParams params = AddChangelogEntryBatchModeParams.of("title", "Peter", null, "added", null, null);

        // then:
        assertEquals("Peter", params.getAuthor());
        assertNull(params.getAuthors());
    }


    @Test
    void shouldCreateChangelogEntryBatchModeParamsWithAuthors() {
        // when:
        AddChangelogEntryBatchModeParams params = AddChangelogEntryBatchModeParams.of("title", null, Arrays.asList("Peter", "Mat"), "added", null, null);

        // then:
        assertEquals(2, params.getAuthors().size());
        assertEquals("Peter", params.getAuthors().get(0));
        assertEquals("Mat", params.getAuthors().get(1));
        assertNull(params.getAuthor());
    }


    @Test
    void shouldThrowExceptionWhenBothAuthorAndAuthorsAreSet() {
        // when:
        Exception ex = assertThrows(IllegalArgumentException.class, () -> AddChangelogEntryBatchModeParams.of("title", "Peter", Arrays.asList("Peter", "Mat"), "added", null, null));

        // then:
        assertEquals("It is not allowed to simultaneously use of 'author' and 'authors' options!", ex.getMessage());
    }

    @Test
    void shouldCreateParamsWithEmptyAuthorsAndNotNullAuthor() {
        // when:
        AddChangelogEntryBatchModeParams params = AddChangelogEntryBatchModeParams.of("title", "Peter", Collections.emptyList(), "added", null, null);

        // then:
        assertEquals("Peter", params.getAuthor());
        assertTrue(params.getAuthors().isEmpty());
    }
}