package dev.logchange.md.list;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class MarkdownLists {

    /**
     * Formats the elements of items param as Markdown Unordered List - example:
     * <blockquote><pre>
     * - first
     * - second
     * - third
     * </pre></blockquote>
     * @param items {@code List<T>} - list of items to be formatted.
     * @return the string representation of Markdown Unordered List.
     */
    public static <T> String unorderedList(List<T> items) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        items.stream()
                .map(MarkdownLists::unorderedListItem)
                .forEach(sj::add);
        return sj.toString();
    }

    /**
     * Formats the string representation of value param as Markdown Unordered List Item - example:
     * <blockquote><pre>
     * - list item
     * </pre></blockquote>
     * @param value {@code Object} - object to be formatted.
     * @return the string representation of single Markdown Unordered List Item.
     */
    public static String unorderedListItem(Object value) {
        return "- " + value;
    }

    /**
     * Formats recursively the elements of items param as nested Markdown Unordered List.
     * If element of the list is another list it will be formatted as nested list with proper indentation - example:
     * <blockquote><pre>
     * - 1
     *   - 1.1
     *   - 1.2
     *   - 1.2
     * - 2
     *   - 2.1
     *     - 2.1.1
     * - 3
     * </pre></blockquote>
     * @param items {@code List<T>} - list of items to be formatted.
     * @return the string representation of nested Markdown Unordered List.
     */
    public static <T> String unorderedNestedList(List<T> items) {
        return unorderedNestedList(items, 0);
    }

    private static <T> String unorderedNestedList(List<T> items, int indentationLevel) {
        String indentation = fillUpLeftAligned("", ' ', indentationLevel * 2);
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

    private static String fillUpLeftAligned(String value, char fill, int length) {
        return value.length() >= length ? value : String.format("%-" + length + "s", value).replace(' ', fill);
    }
}
