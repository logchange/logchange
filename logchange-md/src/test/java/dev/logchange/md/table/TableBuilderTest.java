package dev.logchange.md.table;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TableBuilderTest {

    @Test
    void xd() {
        TableBuilder builder = new TableBuilder();
        builder.addRow("TYP", "RODZAJ", "DASDADADADA");
        builder.addRow("database", "baza danych");
        builder.addRow("database", "baza danych", "trzy", "cztery");

        TableRow row = new TableRow(Arrays.asList("ROW", "ROW", "ROW"));

        System.out.println(builder.toMarkdownTable());
        System.out.println(row);
    }

}