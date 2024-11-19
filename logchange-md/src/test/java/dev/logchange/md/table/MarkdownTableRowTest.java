package dev.logchange.md.table;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownTableRowTest {

    @Test
    void shouldThrowExceptionWhenNullHeaderPassed() {
        // when-then
        MarkdownTableValidationException exception = assertThrows(MarkdownTableValidationException.class, () -> MarkdownTableRow.of(null));
        assertEquals("Row cells cannot be null!", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmptyListPassed() {
        // when-then
        MarkdownTableValidationException exception = assertThrows(MarkdownTableValidationException.class, () -> MarkdownTableRow.of(Collections.emptyList()));
        assertEquals("Row cells cannot be empty!", exception.getMessage());
    }

    @Test
    void shouldCalculateCellWidthsAfterTableRowCreation() {
        // given
        List<Object> list = Arrays.asList(null, "", 1, 12, 123, "     long  cell     ");

        // when
        MarkdownTableRow markdownTableRow = MarkdownTableRow.of(list);
        Map<Integer, Integer> cellWidth = markdownTableRow.getCellWidths();

        // then
        final int listSize = list.size();
        assertEquals(listSize, markdownTableRow.getNumberOfCells());
        assertEquals(listSize, cellWidth.size());

        assertNull(markdownTableRow.getCell(0));
        assertEquals(4, cellWidth.get(0));

        assertEquals("", markdownTableRow.getCell(1));
        assertEquals(3, cellWidth.get(1));

        assertEquals(1, markdownTableRow.getCell(2));
        assertEquals(3, cellWidth.get(2));

        assertEquals(12, markdownTableRow.getCell(3));
        assertEquals(3, cellWidth.get(3));

        assertEquals(123, markdownTableRow.getCell(4));
        assertEquals(3, cellWidth.get(4));

        assertEquals("     long  cell     ", markdownTableRow.getCell(5));
        assertEquals("     long  cell     ".length(), cellWidth.get(5));
    }
}
