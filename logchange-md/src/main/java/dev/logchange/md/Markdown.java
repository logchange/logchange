package dev.logchange.md;

public class Markdown {

    public static String text(Object value) {
        return String.valueOf(value);
    }

    public static String code(Object value) {
        return "`" + value + "`";
    }

    public static String link(Object text, String url) {
        return "[" + text + "]" + "(" + url + ")";
    }

    public static String heading(Object value, int level) {
        return Heading.of(value, level);
    }
}
