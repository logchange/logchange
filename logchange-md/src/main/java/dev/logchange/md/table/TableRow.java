package dev.logchange.md.table;


import dev.logchange.md.MarkdownElement;
import dev.logchange.md.MarkdownSerializationException;
import dev.logchange.md.util.StringUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static dev.logchange.md.table.Table.WHITESPACE;

@Getter
@RequiredArgsConstructor
public class TableRow<T> extends MarkdownElement {

    private final List<T> columns;

    @Override
    public String serialize() throws MarkdownSerializationException {
        StringBuilder sb = new StringBuilder();
        for (Object column : columns) {
            checkColumn(column);
            sb.append(Table.SEPARATOR);
            sb.append(StringUtil.surroundValueWith(column.toString(), WHITESPACE));
        }
        return sb.append(Table.SEPARATOR).toString();
    }

    private static void checkColumn(Object column) throws MarkdownSerializationException {
        if (column == null) {
            throw new MarkdownSerializationException("Column is null");
        }
        if (column.toString().contains(Table.SEPARATOR)) {
            throw new MarkdownSerializationException("Column contains seperator char \"" + Table.SEPARATOR + "\"");
        }
    }

}
