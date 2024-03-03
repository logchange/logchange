package dev.logchange.md.link;

public class Link {

    public static String stringOf(Object text, String url) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(text).append("]");
        sb.append("(").append(url).append(")");
        return sb.toString();
    }
}
