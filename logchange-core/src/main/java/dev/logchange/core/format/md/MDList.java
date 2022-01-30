package dev.logchange.core.format.md;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class MDList implements MD {
    private static final String BEGIN_TAG = "<ul>";
    private static final String END_TAG = "</ul>";

    private static final String BEGIN_ITEM_TAG = "<li>";
    private static final String END_ITEM_TAG = "</li>";

    private final List<String> items;

    public MDList() {
        this.items = new LinkedList<>();
    }

    public void add(String item) {
        if (StringUtils.isNotBlank(item)) {
            items.add(item);
        }
    }

    @Override
    public String toString() {
        if (items.size() == 0) {
            return StringUtils.EMPTY;
        }

        StringBuilder str = new StringBuilder(BEGIN_TAG);

        for (String item : items) {
            str.append(BEGIN_ITEM_TAG)
                    .append(item)
                    .append(END_ITEM_TAG);
        }

        str.append(END_TAG);

        return str.toString();
    }
}
