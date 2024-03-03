package dev.logchange.md.list;

import dev.logchange.md.MarkdownElement;
import dev.logchange.md.util.StringUtil;
import lombok.Setter;

import java.util.List;

public class UnorderedList<T> extends MarkdownElement {

    protected final List<T> items;
    @Setter
    protected int indentationLevel = 0;

    public UnorderedList(List<T> items) {
        this.items = items;
    }

    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (int itemIndex = 0; itemIndex < items.size(); itemIndex++) {
            T item = items.get(itemIndex);

            if (itemIndex > 0) {
                sb.append(StringUtil.fillUpLeftAligned("", "  ", indentationLevel * 2));
            } else if (indentationLevel > 0) {
                sb.append("  ");
            }

            if (item instanceof UnorderedList) {
                UnorderedList unorderedList = (UnorderedList) item;
                unorderedList.setIndentationLevel(indentationLevel + 1);
                sb.append(unorderedList);
            } else {
                sb.append(UnorderedListItem.stringOf(item));
            }

            if (itemIndex < items.size() - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
