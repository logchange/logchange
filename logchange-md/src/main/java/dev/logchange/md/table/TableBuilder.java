package dev.logchange.md.table;

import java.util.Arrays;

public class TableBuilder {
    private Table table;

    public TableBuilder() {
        table = new Table();
    }

    public TableBuilder addRow(Object... objects) {
        TableRow tableRow = new TableRow(Arrays.asList(objects));
        table.addRow(tableRow);
        return this;
    }

    public Table build() {
        return table;
    }
}
