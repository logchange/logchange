package dev.logchange.md.table;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.logchange.md.StringUtil.fillUpLeftAligned;
import static dev.logchange.md.StringUtil.surroundValueWith;


class Table {

    private static final String SEPARATOR = "|";
    private static final String WHITESPACE = " ";

    private final TableRow header;
    private final List<TableRow> rows;
    private final Map<Integer, Integer> tableColumnWidths;

    Table(TableRow header) {
        this.header = header;
        this.tableColumnWidths = new HashMap<>();
        this.rows = new ArrayList<>();
        calculateTableColumnWidths(header);
    }

    void addRow(TableRow tableRow) {
        calculateTableColumnWidths(tableRow);
        this.rows.add(tableRow);
    }

    int getNumberOfColumns() {
        return this.header.getNumberOfCells();
    }

    private void calculateTableColumnWidths(TableRow row) {
        Map<Integer, Integer> rowCellWidths = row.getCellWidths();
        for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
            this.tableColumnWidths.compute(columnIndex, (key, value) ->
                    Math.max((value != null ? value : 0), rowCellWidths.getOrDefault(key, 0)));
        }
    }

    @Override
    public String toString() {
        return serialize();
    }

    private String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(generateCells(this.header));
        sb.append(generateHeaderSeparator());
        for (TableRow row : rows) {
            sb.append(System.lineSeparator());
            sb.append(generateCells(row));
        }
        return sb.toString();
    }

    private String generateCells(TableRow row) {
        StringBuilder sb = new StringBuilder();

        for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
            sb.append(SEPARATOR);
            sb.append(fillUpLeftAligned(
                    surroundValueWith(escapeSeparatorSign(row.getCell(columnIndex)), WHITESPACE),
                    ' ',
                    tableColumnWidths.get(columnIndex) + 2));
        }
        return sb.append(SEPARATOR).toString();
    }

    private String escapeSeparatorSign(Object cell) {
        return String.valueOf(cell).replace(SEPARATOR, "\\" + SEPARATOR);
    }

    private String generateHeaderSeparator() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
            sb.append(SEPARATOR);
            sb.append(surroundValueWith(fillUpLeftAligned("", '-', tableColumnWidths.get(columnIndex)), WHITESPACE));
        }
        return sb.append(SEPARATOR).toString();
    }
}
