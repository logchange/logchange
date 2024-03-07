package dev.logchange.md;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeadingTest {

    @Test
    public void shouldParseObjectAsHeading() {
        String lineSeparator = System.lineSeparator();
        assertEquals("null" + lineSeparator + "====", Heading.of(null, -1));
        assertEquals("null" + lineSeparator + "====", Heading.of(null, 0));
        assertEquals("null" + lineSeparator + "====", Heading.of(null, 1));
        assertEquals("null" + lineSeparator + "----", Heading.of(null, 2));
        assertEquals("### null", Heading.of(null, 3));
        assertEquals("#### null", Heading.of(null, 4));
        assertEquals("##### null", Heading.of(null, 5));
        assertEquals("###### null", Heading.of(null, 6));
        assertEquals("###### null", Heading.of(null, 7));
    }

}