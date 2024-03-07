package dev.logchange.md;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkdownTest {


    @Test
    public void shouldConvertObjectToString() {
        assertEquals("null", Markdown.text(null));
        assertEquals("true", Markdown.text(true));
        assertEquals("9223372036854775807", Markdown.text(9223372036854775807L));
        assertEquals("[orange, banana, apple]", Markdown.text(Arrays.asList("orange", "banana", "apple")));
    }

    @Test
    public void shouldConvertObjectToCode() {
        assertEquals("`null`", Markdown.code(null));
        assertEquals("`true`", Markdown.code(true));
        assertEquals("`9223372036854775807`", Markdown.code(9223372036854775807L));
        assertEquals("`[orange, banana, apple]`", Markdown.code(Arrays.asList("orange", "banana", "apple")));
    }

    @Test
    public void shouldConvertObjectToLink() {
        assertEquals("[null](null)", Markdown.link(null, null));
        assertEquals("[null](string)", Markdown.link(null, "string"));
        assertEquals("[true](string)", Markdown.link(true, "string"));
        assertEquals("[9223372036854775807](string)", Markdown.link(9223372036854775807L, "string"));
        assertEquals("[[orange, banana, apple]](string)", Markdown.link(Arrays.asList("orange", "banana", "apple"), "string"));
    }
}