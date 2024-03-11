package dev.logchange.md.table;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownTableTest {

    @Test
    void shouldThrowExceptionWhenNullHeaderPassed() {
        // when-then
        MarkdownTableValidationException exception = assertThrows(MarkdownTableValidationException.class, () -> MarkdownTable.of(null));
        assertEquals("Table header cannot be null!", exception.getMessage());
    }

    @Test
    void shouldGenerateTableWithHeaderOnly() {
        // given
        String expectedTable = "| null |     | HEADER 1 |" + System.lineSeparator() + "| ---- | --- | -------- |";
        MarkdownTableRow header = MarkdownTableRow.of(Arrays.asList(null, "", "HEADER 1"));

        // when
        MarkdownTable table = MarkdownTable.of(header);

        // then
        assertEquals(header.getNumberOfCells(), table.getNumberOfColumns());
        assertEquals(expectedTable, table.toString());
    }

    @Test
    void shouldGenerateTableWithOneColumnAndOneRow() {
        // given
        String lineSeparator = System.lineSeparator();
        String expectedTable = "| Type: database                                                                                             |" + lineSeparator +
                        "| ---------------------------------------------------------------------------------------------------------- |" + lineSeparator +
                        "| <ul><li>Updated `parameter` with default value: `true`</li><li>Description: Parameter true/false</li></ul> |";

        MarkdownTableRow header = MarkdownTableRow.of(Collections.singletonList("Type: database"));
        MarkdownTableRow row = MarkdownTableRow.of(Collections.singletonList("<ul><li>Updated `parameter` with default value: `true`</li><li>Description: Parameter true/false</li></ul>"));

        // when
        MarkdownTable table = MarkdownTable.of(header);
        table.addRow(row);

        // then
        assertEquals(header.getNumberOfCells(), table.getNumberOfColumns());
        assertEquals(expectedTable, table.toString());
    }

    @Test
    void shouldIgnoreRowsWithIncorrectAmountOfCellsAndNull() {
        // given
        String expectedTable = "| null   |        | HEADER 1 |" + System.lineSeparator()
                + "| ------ | ------ | -------- |" + System.lineSeparator()
                + "| Cell 1 | Cell 2 | Cell 3   |";

        MarkdownTableRow header = MarkdownTableRow.of(Arrays.asList(null, "", "HEADER 1"));
        MarkdownTableRow tooShorRow = MarkdownTableRow.of(Arrays.asList("Cell 1", "Cell 2"));
        MarkdownTableRow tooLongRow = MarkdownTableRow.of(Arrays.asList("Cell 1", "Cell 2", "Cell 3", "Cell 4"));
        MarkdownTableRow correctRow = MarkdownTableRow.of(Arrays.asList("Cell 1", "Cell 2", "Cell 3"));

        // when
        MarkdownTable table = MarkdownTable.of(header);
        boolean resultForTooShort = table.addRow(tooShorRow);
        boolean resultForTooLong = table.addRow(tooLongRow);
        boolean resultNull = table.addRow(null);
        boolean resultForCorrect = table.addRow(correctRow);

        // then
        assertEquals(header.getNumberOfCells(), table.getNumberOfColumns());
        assertFalse(resultForTooShort);
        assertFalse(resultForTooLong);
        assertFalse(resultNull);
        assertTrue(resultForCorrect);
        assertEquals(expectedTable, table.toString());
    }

    @Test
    void shouldEscapeSeparatorSigns() {
        // given
        String expectedTable = "|  \\|  | string\\|string | \\| string \\| string \\| |" + System.lineSeparator()
                + "| --- | ------------- | ------------------- |" + System.lineSeparator()
                + "| \\|  | \\|\\|          | \\|\\|\\|              |";

        MarkdownTableRow header = MarkdownTableRow.of(Arrays.asList(" | ", "string|string", "| string | string |"));
        MarkdownTableRow correctRow = MarkdownTableRow.of(Arrays.asList("|", "||", "|||"));

        // when
        MarkdownTable table = MarkdownTable.of(header);
        boolean resultForCorrect = table.addRow(correctRow);

        // then
        assertEquals(header.getNumberOfCells(), table.getNumberOfColumns());
        assertTrue(resultForCorrect);
        assertEquals(expectedTable, table.toString());
    }
}
