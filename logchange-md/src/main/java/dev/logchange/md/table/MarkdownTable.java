package dev.logchange.md.table;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class MarkdownTable {

    private static final String SEPARATOR = "|";
    private static final char WHITESPACE = ' ';

    private final MarkdownTableRow header;
    private final List<MarkdownTableRow> rows;
    private final Map<Integer, Integer> tableColumnWidths;

    MarkdownTable(MarkdownTableRow header) {
        this.header = header;
        this.tableColumnWidths = new HashMap<>();
        this.rows = new ArrayList<>();
        calculateTableColumnWidths(header);
    }

    private static String fillUpLeftAligned(String value, char fill, int length) {
        return value.length() >= length ? value : String.format("%-" + length + "s", value).replace(' ', fill);
    }

    private static String surroundWithWhitespace(String value) {
        return WHITESPACE + value + WHITESPACE;
    }

    void addRow(MarkdownTableRow tableRow) {
        calculateTableColumnWidths(tableRow);
        this.rows.add(tableRow);
    }

    int getNumberOfColumns() {
        return this.header.getNumberOfCells();
    }

    private void calculateTableColumnWidths(MarkdownTableRow row) {
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
        for (MarkdownTableRow row : rows) {
            sb.append(System.lineSeparator());
            sb.append(generateCells(row));
        }
        return sb.toString();
    }

    private String generateCells(MarkdownTableRow row) {
        StringBuilder sb = new StringBuilder();

        for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
            sb.append(SEPARATOR);
            sb.append(fillUpLeftAligned(
                    surroundWithWhitespace(escapeSeparatorSign(row.getCell(columnIndex))),
                    WHITESPACE,
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
            sb.append(surroundWithWhitespace(fillUpLeftAligned("", '-', tableColumnWidths.get(columnIndex))));
        }
        return sb.append(SEPARATOR).toString();
    }
}
