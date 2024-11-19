package dev.logchange.md.table;


import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


class MarkdownTableRow {

    private static final int DEFAULT_MINIMUM_CELL_WIDTH = 3;

    private final List<Object> cells;

    @Getter
    private final Map<Integer, Integer> cellWidths;


    public static MarkdownTableRow of(List<Object> cells) {
        checkCells(cells);
        return new MarkdownTableRow(cells);
    }

    private MarkdownTableRow(List<Object> cells) {
        this.cells = cells;
        this.cellWidths = calculateCellsWidths(cells);
    }

    private static void checkCells(List<Object> cells) {
        if (cells == null) {
            throw new MarkdownTableValidationException("Row cells cannot be null!");
        }
        if (cells.isEmpty()) {
            throw new MarkdownTableValidationException("Row cells cannot be empty!");
        }
    }

    private static Map<Integer, Integer> calculateCellsWidths(List<Object> cells) {
        Map<Integer, Integer> widths = new HashMap<>();
        for (int i = 0; i < cells.size(); i++) {
            widths.put(i, calculateCellWidth(cells.get(i)));
        }
        return widths;
    }

    private static int calculateCellWidth(Object value) {
        return Math.max(String.valueOf(value).length(), DEFAULT_MINIMUM_CELL_WIDTH);
    }

    int getNumberOfCells() {
        return cells.size();
    }

    Object getCell(int index) {
        return cells.get(index);
    }
}
