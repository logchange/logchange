package dev.logchange.md.table;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class MarkdownTableBuilder {
    private static final Logger log = Logger.getLogger(MarkdownTableBuilder.class.getName());
    private MarkdownTable table;

    public MarkdownTableBuilder() {
    }

    public MarkdownTableBuilder(Object... objects) {
        initializeTable(objects);
    }

    private void initializeTable(Object... objects) {
        try {
            this.table = new MarkdownTable(new MarkdownTableRow(Arrays.asList(objects)));
        } catch (MarkdownTableValidationException exception) {
            log.warning("Creation of MarkdownTable caused exception with following message: " + exception.getMessage());
        }
    }

    public MarkdownTableBuilder addRow(Object... objects) {
        if (table == null) {
            initializeTable(objects);
        } else {
            table.addRow(adjustNumberOfColumns(objects));
        }
        return this;
    }

    private MarkdownTableRow adjustNumberOfColumns(Object... objects) {
        List<Object> columns = new LinkedList<>(Arrays.asList(objects));
        final int numberOfColumns = table.getNumberOfColumns();
        if (columns.size() > numberOfColumns) {
            return trimColumns(columns, numberOfColumns);
        }
        return appendEmptyColumns(columns, numberOfColumns);
    }

    private MarkdownTableRow appendEmptyColumns(List<Object> columns, int numberOfColumns) {
        int difference = numberOfColumns - columns.size();
        columns.addAll(Collections.nCopies(difference, ""));
        return new MarkdownTableRow(columns);
    }

    private MarkdownTableRow trimColumns(List<Object> columns, int numberOfColumns) {
        return new MarkdownTableRow(columns.subList(0, numberOfColumns));
    }

    public String build() {
        return table != null ? table.toString() : "";
    }
}
