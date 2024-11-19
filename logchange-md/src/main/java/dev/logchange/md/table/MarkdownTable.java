package dev.logchange.md.table;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class MarkdownTable {

    private static final String SEPARATOR = "|";
    private static final char WHITESPACE = ' ';
    private static final int DEFAULT_MINIMUM_COLUMN_WIDTH = 3;

    private final MarkdownTableRow header;
    private final List<MarkdownTableRow> rows;
    private final Map<Integer, Integer> tableColumnWidths;

    public static MarkdownTable of(MarkdownTableRow header) {
        checkHeader(header);
        return new MarkdownTable(header);
    }

    private MarkdownTable(MarkdownTableRow header) {
        this.header = header;
        this.tableColumnWidths = new HashMap<>();
        this.rows = new ArrayList<>();
        calculateTableColumnWidths(header);
    }

    private static void checkHeader(MarkdownTableRow header) {
        if (header == null) {
            throw new MarkdownTableValidationException("Table header cannot be null!");
        }
        if (header.getNumberOfCells() == 0) {
            throw new MarkdownTableValidationException("Table header cannot be empty!");
        }
    }

    boolean addRow(MarkdownTableRow tableRow) {
        if (tableRow != null && getNumberOfColumns() == tableRow.getNumberOfCells()) {
            calculateTableColumnWidths(tableRow);
            return this.rows.add(tableRow);
        }
        return false;
    }

    int getNumberOfColumns() {
        return this.header.getNumberOfCells();
    }

    private void calculateTableColumnWidths(MarkdownTableRow row) {
        Map<Integer, Integer> rowCellWidths = row.getCellWidths();
        for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
            this.tableColumnWidths.compute(columnIndex, (key, value) ->
                    Math.max((value != null ? value : DEFAULT_MINIMUM_COLUMN_WIDTH),
                            rowCellWidths.getOrDefault(key, DEFAULT_MINIMUM_COLUMN_WIDTH)));
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

    private String fillUpLeftAligned(String value, char fill, int length) {
        return value.length() >= length ? value : String.format("%-" + length + "s", value).replace(' ', fill);
    }

    private String escapeSeparatorSign(Object cell) {
        return String.valueOf(cell).replace(SEPARATOR, "\\" + SEPARATOR);
    }

    private String surroundWithWhitespace(String value) {
        return WHITESPACE + value + WHITESPACE;
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
