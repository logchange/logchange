package dev.logchange.core.format.md;

public class MDMeta implements MD {

    private static final String FORMATTER_OFF = "<!-- @formatter:off -->";
    private static final String NOINSPECTION = "<!-- noinspection -->";
    private static final String COMMENT = "<!-- Prevents auto format, for JetBrains IDE File > Editor > Code Style > Enable formatter markers in comments  -->";

    @Override
    public String toString() {
        return getMeta();
    }

    public String getMeta() {
        return FORMATTER_OFF + "\n" +
                NOINSPECTION + "\n" +
                COMMENT + "\n\n";
    }
}
