package dev.logchange.core.format.md;

public class MDMeta implements MD {

    private static final String FORMATTER_OFF = "<!-- @formatter:off -->";
    private static final String NOINSPECTION = "<!-- noinspection -->";
    private static final String COMMENT = "<!-- Prevents auto format, for JetBrains IDE File > Settings > Editor > Code Style (Formatter Tab) > Turn formatter on/off with markers in code comments  -->";

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
