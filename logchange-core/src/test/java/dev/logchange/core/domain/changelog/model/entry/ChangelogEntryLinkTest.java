package dev.logchange.core.domain.changelog.model.entry;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChangelogEntryLinkTest {

    @Test
    void shouldCreateLinkWithGivenNameAndUrl() {
        // given:
        String name = "Homepage";
        String url = "https://example.com";

        // when:
        ChangelogEntryLink link = ChangelogEntryLink.of(name, url);

        // then:
        assertThat(link.getName()).isEqualTo(name);
        assertThat(link.getUrl()).isEqualTo(url);
    }

    @Test
    void shouldUseEmptyStringWhenNameIsNull() {
        // given:
        String url = "https://example.com/docs";

        // when:
        ChangelogEntryLink link = ChangelogEntryLink.of(null, url);

        // then:
        assertThat(link.getName()).isEqualTo("");
        assertThat(link.getUrl()).isEqualTo(url);
    }

    @Test
    void shouldThrowWhenUrlIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> ChangelogEntryLink.of("n", null));
        assertThrows(IllegalArgumentException.class, () -> ChangelogEntryLink.of("n", ""));
        assertThrows(IllegalArgumentException.class, () -> ChangelogEntryLink.of("n", "   "));
    }
}
