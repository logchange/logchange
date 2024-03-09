package dev.logchange.md;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkdownHeadingTest {

    @Test
    public void shouldParseObjectAsHeading() {
        // given
        String lineSeparator = System.lineSeparator();

        // when-then
        assertEquals("null" + lineSeparator + "====", MarkdownHeading.of(null, -1));
        assertEquals("null" + lineSeparator + "====", MarkdownHeading.of(null, 0));
        assertEquals("null" + lineSeparator + "====", MarkdownHeading.of(null, 1));
        assertEquals("null" + lineSeparator + "----", MarkdownHeading.of(null, 2));
        assertEquals("### null", MarkdownHeading.of(null, 3));
        assertEquals("#### null", MarkdownHeading.of(null, 4));
        assertEquals("##### null", MarkdownHeading.of(null, 5));
        assertEquals("###### null", MarkdownHeading.of(null, 6));
        assertEquals("###### null", MarkdownHeading.of(null, 7));
    }
}
