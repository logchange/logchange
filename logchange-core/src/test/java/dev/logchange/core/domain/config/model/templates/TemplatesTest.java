package dev.logchange.core.domain.config.model.templates;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static dev.logchange.core.domain.config.model.templates.Templates.DEFAULT_ENTRY_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TemplatesTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  "})
    void shouldReturnDefaultEntryFormat(String value) {
        // given:
        Templates templates = Templates.builder().entryFormat(value).build();

        // when:
        String entryFormat = templates.getEntryFormat();
        // then:
        assertEquals(DEFAULT_ENTRY_FORMAT, entryFormat);
    }

    @Test
    void shouldReturnProvidedEntryFormat() {
        // given:
        String expected_format = "${title} ${issues}";
        Templates templates = Templates.builder().entryFormat(expected_format).build();

        // when:
        String entryFormat = templates.getEntryFormat();

        // then:
        assertEquals(expected_format, entryFormat);
    }
}
