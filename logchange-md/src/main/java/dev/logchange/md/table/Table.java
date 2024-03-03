package dev.logchange.md.table;


import dev.logchange.md.MarkdownElement;
import dev.logchange.md.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.logchange.md.util.StringUtil.surroundValueWith;


public class Table extends MarkdownElement {

    public static final String SEPARATOR = "|";
    public static final String WHITESPACE = " ";
    public static final String DEFAULT_TRIMMING_INDICATOR = "~";
    public static final int DEFAULT_MINIMUM_COLUMN_WIDTH = 3;

    private final List<TableRow> rows;

    public Table() {
        this.rows = new ArrayList<>();
    }

    @Override
    public String serialize() {
        Map<Integer, Integer> columnWidths = getColumnWidths(rows);

        StringBuilder sb = new StringBuilder();

        String headerSeparator = generateHeaderSeparator(columnWidths);
        boolean headerSeperatorAdded = false;

        for (TableRow row : rows) {
            for (int columnIndex = 0; columnIndex < columnWidths.size(); columnIndex++) {
                sb.append(SEPARATOR);

                String value = "";
                if (row.getColumns().size() > columnIndex) {
                    Object valueObject = row.getColumns().get(columnIndex);
                    if (valueObject != null) {
                        value = valueObject.toString();
                    }
                }

                if (value.equals(DEFAULT_TRIMMING_INDICATOR)) {
                    value = StringUtil.fillUpLeftAligned(value, DEFAULT_TRIMMING_INDICATOR, columnWidths.get(columnIndex));
                    value = surroundValueWith(value, WHITESPACE);
                } else {
                    value = surroundValueWith(value, WHITESPACE);
                    value = StringUtil.fillUpLeftAligned(value, WHITESPACE, columnWidths.get(columnIndex) + 2);
                }

                sb.append(value);

                if (columnIndex == row.getColumns().size() - 1) {
                    sb.append(SEPARATOR);
                }
            }

            if (rows.indexOf(row) < rows.size() - 1 || rows.size() == 1) {
                sb.append(System.lineSeparator());
            }

            if (!headerSeperatorAdded) {
                sb.append(headerSeparator).append(System.lineSeparator());
                headerSeperatorAdded = true;
            }
        }
        return sb.toString();
    }

    public static String generateHeaderSeparator(Map<Integer, Integer> columnWidths) {
        StringBuilder sb = new StringBuilder();
        for (int columnIndex = 0; columnIndex < columnWidths.entrySet().size(); columnIndex++) {
            sb.append(SEPARATOR);

            String value = StringUtil.fillUpLeftAligned("", "-", columnWidths.get(columnIndex));

            value = surroundValueWith(value, WHITESPACE);

            sb.append(value);
            if (columnIndex == columnWidths.entrySet().size() - 1) {
                sb.append(SEPARATOR);
            }
        }
        return sb.toString();
    }

    public static Map<Integer, Integer> getColumnWidths(List<TableRow> rows) {
        Map<Integer, Integer> columnWidths = new HashMap<Integer, Integer>();
        if (rows.isEmpty()) {
            return columnWidths;
        }
        for (int columnIndex = 0; columnIndex < rows.get(0).getColumns().size(); columnIndex++) {
            columnWidths.put(columnIndex, getMaximumItemLength(rows, columnIndex, DEFAULT_MINIMUM_COLUMN_WIDTH));
        }
        return columnWidths;
    }

    public static int getMaximumItemLength(List<TableRow> rows, int columnIndex, int minimumColumnWidth) {
        int maximum = minimumColumnWidth;
        for (TableRow row : rows) {
            if (row.getColumns().size() < columnIndex + 1) {
                continue;
            }
            Object value = row.getColumns().get(columnIndex);
            if (value == null) {
                continue;
            }
            maximum = Math.max(value.toString().length(), maximum);
        }
        return maximum;
    }

    public void addRow(TableRow tableRow) {
        this.rows.add(tableRow);
    }

    public static class Builder {

        private Table table;

        public Builder() {
            table = new Table();
        }

        public Builder addRow(Object... objects) {
            TableRow tableRow = new TableRow(Arrays.asList(objects));
            table.addRow(tableRow);
            return this;
        }

        public Table build() {
            return table;
        }

    }
}
