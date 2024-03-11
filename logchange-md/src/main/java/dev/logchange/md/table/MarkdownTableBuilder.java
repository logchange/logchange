package dev.logchange.md.table;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class MarkdownTableBuilder {
    private static final Logger log = Logger.getLogger(MarkdownTableBuilder.class.getName());
    private MarkdownTable table;


    /**
     * Creates instance of {@code MarkdownTableBuilder} without initialization of underneath {@code MarkdownTable}.
     * First call of {@link #addRow(Object... objects) addRow} method will try to initialize {@code MarkdownTable} field.
     */
    public MarkdownTableBuilder() {
    }

    /**
     * <p>Creates instance of {@code MarkdownTableBuilder} and try to initialize underneath {@code MarkdownTable}.
     * Successful initialization will create {@code MarkdownTable} with header {@code MarkdownTableRow}
     * which specifies the number of columns in the table.</p>
     *
     * <p>Every other row will be adjusted, excess cells will be trimmed and the missing ones will be filled
     * with empty cells, to the number of cells in header.</p>
     *
     * <p>In case of {@code MarkdownTableValidationException} initialization is skipped and the next call of
     * {@link #addRow(Object... objects) addRow} method will also try to initialize {@code MarkdownTable} field.</p>
     * @param objects {@code Object...} arguments to be converted into header {@code MarkdownTableRow}
     */
    public MarkdownTableBuilder(Object... objects) {
        initializeTable(objects);
    }

    private void initializeTable(Object... objects) {
        try {
            this.table = MarkdownTable.of(MarkdownTableRow.of(Arrays.asList(objects)));
        } catch (MarkdownTableValidationException exception) {
            log.warning("Creation of MarkdownTable caused exception with following message: " + exception.getMessage());
        }
    }

    /**
     * <p>Tries to add new {@code MarkdownTableRow} to the {@code MarkdownTable}.
     * If the {@code MarkdownTable} filed has not been initialized yet this method will try to create {@code MarkdownTable}
     * with header {@code MarkdownTableRow} which specifies the number of columns in the table.</p>
     *
     * <p>Every other added row will be adjusted, excess cells will be trimmed and the missing ones will be filled
     * with empty cells, to the number of cells in header.</p>
     *
     * <p>In case of {@code MarkdownTableValidationException} initialization of {@code MarkdownTableRow} is skipped
     * and the next call of this method will also try to initialize this field.</p>
     *
     * @param objects {@code Object...} arguments to be converted into {@code MarkdownTableRow} and added to {@code MarkdownTable}
     * @return this {@code MarkdownTableBuilder} instance of builder.
     */
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
        return MarkdownTableRow.of(columns);
    }

    private MarkdownTableRow trimColumns(List<Object> columns, int numberOfColumns) {
        return MarkdownTableRow.of(columns.subList(0, numberOfColumns));
    }

    /**
     * <p> Invokes {@code table.toString()} method to serialize {@code MarkdownTable} object
     * into {@code String} representing Markdown Table.
     * In case {@code MarkdownTable} filed has not been initialized yet returns empty string instead.</p>
     *
     * <p> During serialization every cell content is adjusted with whitespace characters to the calculated column width.
     * Column widths are set based on the widest cell in a column. Furthermore, all separation characters <b>|</b>
     * within cells contents are escaped with backslash character <b>\</b></p>
     *
     * @return {@code String} representation Markdown Table.
     */
    public String build() {
        return table != null ? table.toString() : "";
    }
}
