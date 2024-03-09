package dev.logchange.md;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class MarkdownLists {

    public static <T> String unorderedList(List<T> items) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        items.stream()
                .map(MarkdownLists::unorderedListItem)
                .forEach(sj::add);
        return sj.toString();
    }

    public static String unorderedListItem(Object value) {
        return "- " + value;
    }

    public static <T> String unorderedNestedList(List<T> items) {
        return unorderedNestedList(items, 0);
    }

    private static <T> String unorderedNestedList(List<T> items, int indentationLevel) {
        String indentation = StringUtil.fillUpLeftAligned("", ' ', indentationLevel * 2);
        return items.stream()
                .map(item -> {
                    if (item instanceof List) {
                        return unorderedNestedList((List<?>) item, indentationLevel + 1);
                    } else {
                        return indentation + unorderedListItem(item);
                    }
                })
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
