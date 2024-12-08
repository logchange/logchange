package dev.logchange.md.table;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkdownTableBuilderTest {

    @Test
    void shouldGenerateEmptyStringWhenNoRowsPassed() {
        // when
        MarkdownTableBuilder emptyConstructorBuilder = new MarkdownTableBuilder();

        // then
        assertEquals("", emptyConstructorBuilder.build());
    }

    @Test
    void shouldGenerateEmptyStringWhenEmptyRowsPassed() {
        // when
        MarkdownTableBuilder emptyConstructorBuilder = new MarkdownTableBuilder();
        emptyConstructorBuilder.addRow();

        // then
        assertEquals("", emptyConstructorBuilder.build());
    }

    @Test
    void shouldGenerateTableWithHeaderOnly() {
        // given
        String expectedTable = "| null |     | HEADER 1 |" + System.lineSeparator() + "| ---- | --- | -------- |";

        // when
        MarkdownTableBuilder builder = new MarkdownTableBuilder(null, "", "HEADER 1");

        MarkdownTableBuilder emptyConstructorBuilder = new MarkdownTableBuilder();
        emptyConstructorBuilder.addRow(null, "", "HEADER 1");

        // then
        String markdownTable = builder.build();
        String emptyConstructorMarkdownTable = emptyConstructorBuilder.build();

        assertEquals(expectedTable, markdownTable);
        assertEquals(expectedTable, emptyConstructorMarkdownTable);
        assertEquals(markdownTable, emptyConstructorMarkdownTable);
    }

    @Test
    void shouldGenerateTableWithAdjustedAmountOfRows() {
        // given
        String lineSeparator = System.lineSeparator();
        String expectedTable = "| HEADER 1 | HEADER 2 | HEADER 3 |" + lineSeparator +
                "| -------- | -------- | -------- |" + lineSeparator +
                "|          |          |          |" + lineSeparator +
                "| Cell 1   |          |          |" + lineSeparator +
                "| Cell 1   | Cell 2   |          |" + lineSeparator +
                "| Cell 1   | Cell 2   | Cell 3   |" + lineSeparator +
                "| Cell 1   | Cell 2   | Cell 3   |";

        // when
        MarkdownTableBuilder builder = new MarkdownTableBuilder("HEADER 1", "HEADER 2", "HEADER 3");
        builder.addRow()
                .addRow("Cell 1")
                .addRow("Cell 1", "Cell 2")
                .addRow("Cell 1", "Cell 2", "Cell 3")
                .addRow("Cell 1", "Cell 2", "Cell 3", "Cell 4");

        String markdownTable = builder.build();

        // then
        assertEquals(expectedTable, markdownTable);
    }
}
