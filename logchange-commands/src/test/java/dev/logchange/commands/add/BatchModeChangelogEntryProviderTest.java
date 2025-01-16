package dev.logchange.commands.add;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryAuthor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BatchModeChangelogEntryProviderTest {

    @Test
    void shouldGetChangelogEntryWithoutAuthorOrAuthors() {
        // given:
        AddChangelogEntryBatchModeParams params = AddChangelogEntryBatchModeParams.of("title", null, null, "added", null, null);

        // when:
        ChangelogEntry changelogEntry = new BatchModeChangelogEntryProvider(params).get();

        // then:
        assertEquals(0, changelogEntry.getAuthors().size());
    }

    @Test
    void shouldGetChangelogEntryWithAuthor() {
        // given:
        AddChangelogEntryBatchModeParams params = AddChangelogEntryBatchModeParams.of("title", "Peter", null, "added", null, null);

        // when:
        ChangelogEntry changelogEntry = new BatchModeChangelogEntryProvider(params).get();

        // then:
        assertEquals(1, changelogEntry.getAuthors().size());
        assertEquals("Peter", changelogEntry.getAuthors().get(0).getNick());
    }

    @Test
    void shouldGetChangelogEntryWithAuthors() {
        // given:
        AddChangelogEntryBatchModeParams params = AddChangelogEntryBatchModeParams.of("title", null, Arrays.asList("Peter", "Matthew"), "added", null, null);

        // when:
        List<ChangelogEntryAuthor> authors = new BatchModeChangelogEntryProvider(params).get().getAuthors();

        // then:
        assertEquals(2, authors.size());
        assertEquals("Peter", authors.get(0).getNick());
        assertEquals("Matthew", authors.get(1).getNick());
    }

    @Test
    void shouldThrowExceptionWhenBothAuthorAndAuthorsAreSet() {
        // given:
        AddChangelogEntryBatchModeParams params = AddChangelogEntryBatchModeParams.of("title", "Peter", Arrays.asList("Peter", "Matthew"), "added", null, null);

        // when:
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new BatchModeChangelogEntryProvider(params).get());

        // then:
        assertEquals("Batch mode does not allow simultaneous use of 'author' and 'authors' options.", exception.getMessage());
    }

}