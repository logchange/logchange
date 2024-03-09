package dev.logchange.md;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkdownBasicsTest {

    @Test
    public void shouldConvertObjectToText() {
        assertEquals("null", MarkdownBasics.text(null));
        assertEquals("true", MarkdownBasics.text(true));
        assertEquals("9223372036854775807", MarkdownBasics.text(9223372036854775807L));
        assertEquals("[orange, banana, apple]", MarkdownBasics.text(Arrays.asList("orange", "banana", "apple")));
    }

    @Test
    public void shouldConvertObjectToCode() {
        assertEquals("`null`", MarkdownBasics.code(null));
        assertEquals("`true`", MarkdownBasics.code(true));
        assertEquals("`9223372036854775807`", MarkdownBasics.code(9223372036854775807L));
        assertEquals("`[orange, banana, apple]`", MarkdownBasics.code(Arrays.asList("orange", "banana", "apple")));
    }

    @Test
    public void shouldConvertObjectToLink() {
        assertEquals("[null](null)", MarkdownBasics.link(null, null));
        assertEquals("[null](string)", MarkdownBasics.link(null, "string"));
        assertEquals("[true](string)", MarkdownBasics.link(true, "string"));
        assertEquals("[9223372036854775807](string)", MarkdownBasics.link(9223372036854775807L, "string"));
        assertEquals("[[orange, banana, apple]](string)", MarkdownBasics.link(Arrays.asList("orange", "banana", "apple"), "string"));
    }
}