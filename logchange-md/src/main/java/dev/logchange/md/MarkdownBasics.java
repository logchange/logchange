package dev.logchange.md;

public class MarkdownBasics {

    /**
     * Returns the string representation of the Object argument.
     *
     * @param value {@code Object}
     * @return if the argument is null, then a string equal to "null"; otherwise, the value of value.toString() is returned.
     */
    public static String text(Object value) {
        return String.valueOf(value);
    }

    /**
     * Formats the string representation of the Object argument with a double asterisk sign prefix and suffix: **
     *
     * @param value {@code Object}
     * @return the value of value.toString() with backtick sing affixes, for example: <code>**text**</code>.
     */
    public static String bold(Object value) {
        return "**" + value + "**";
    }

    /**
     * Formats the string representation of the Object argument with a backtick sign prefix and suffix: `
     *
     * @param value {@code Object}
     * @return the value of value.toString() with backtick sing affixes, for example: <code>`text`</code>.
     */
    public static String code(Object value) {
        return "`" + value + "`";
    }

    /**
     * Formats the string representations of text and url params to  the Markdown link
     *
     * @param text {@code Object} text content to be displayed instead of the link
     * @param url  {@code String} address the link points to
     * @return the string representation of Markdown link, for example: <code>[text](url)</code>.
     */
    public static String link(Object text, String url) {
        return "[" + text + "]" + "(" + url + ")";
    }

    /**
     * Formats the {@code String} representation of value param to the Markdown heading based on level parameter:
     * <blockquote><pre>
     *      Level 1 - equivalent to Markdown <b>Title / H1</b>
     *      Level 2 - equivalent to Markdown <b>Subtitle / H2</b>
     *      Level 3 - equivalent to Markdown <b>Heading 1 / H3</b>
     *      Level 4 - equivalent to Markdown <b>Heading 2 / H4</b>
     *      Level 5 - equivalent to Markdown <b>Heading 3 / H5</b>
     *      Level 6 - equivalent to Markdown <b>Heading 4 / H6</b>
     * </pre></blockquote>
     * @param value {@code Object} text content of formatted header.
     * @param level {@code int} determines the level of the header. Possible values 1 - 6.
     * @return the {@code String} representation of Markdown heading.
     */
    public static String heading(Object value, int level) {
        return MarkdownHeading.of(value, level);
    }
}
