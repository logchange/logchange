package dev.logchange.md.table;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TableBuilder {
    private Table table;

    public TableBuilder() {
    }

    public TableBuilder(Object... objects) {
        initializeTable(objects);
    }

    private void initializeTable(Object... objects) {
        this.table = new Table(new TableRow(Arrays.asList(objects)));
    }

    public TableBuilder addRow(Object... objects) {
        if (table == null) {
            initializeTable(objects);
        } else {
            table.addRow(adjustNumberOfColumns(objects));
        }
        return this;
    }

    private TableRow adjustNumberOfColumns(Object... objects) {
        List<Object> columns = new LinkedList<>(Arrays.asList(objects));
        final int numberOfColumns = table.getNumberOfColumns();
        if (columns.size() > numberOfColumns) {
            return trimColumns(columns, numberOfColumns);
        }
        return appendEmptyColumns(columns, numberOfColumns);
    }

    private TableRow appendEmptyColumns(List<Object> columns, int numberOfColumns) {
        int difference = numberOfColumns - columns.size();
        columns.addAll(Collections.nCopies(difference, ""));
        return new TableRow(columns);
    }

    private TableRow trimColumns(List<Object> columns, int numberOfColumns) {
        return new TableRow(columns.subList(0, numberOfColumns));
    }

    public String toMarkdownTable() {
        return table != null ? table.toString() : "";
    }
}
