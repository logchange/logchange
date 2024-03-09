package dev.logchange.md.table;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MarkdownTableBuilderTest {

    @Test
    void xd() {
        MarkdownTableBuilder builder = new MarkdownTableBuilder();
        builder.addRow("TYP", "RODZAJ", "DASDADADADA");
        builder.addRow("database", "baza danych");
        builder.addRow("database", "baza danych", "trzy", "cztery");

        MarkdownTableRow row = new MarkdownTableRow(Arrays.asList("ROW", "ROW", "ROW"));

        System.out.println(builder.build());
        System.out.println(row);
    }

}