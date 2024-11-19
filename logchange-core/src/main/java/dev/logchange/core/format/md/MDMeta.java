package dev.logchange.core.format.md;

public class MDMeta implements MD {

    private static final String FORMATTER_OFF = "<!-- @formatter:off -->";
    private static final String NOINSPECTION = "<!-- noinspection -->";
    private static final String COMMENT = "<!-- Prevents auto format, for JetBrains IDE File > Settings > Editor > Code Style (Formatter Tab) > Turn formatter on/off with markers in code comments  -->";

    private static final String DO_NOT_MODIFY_1 = "<!-- This file is automatically generate by logchange tool \uD83C\uDF33 \uD83E\uDE93 => \uD83E\uDEB5 -->";
    private static final String DO_NOT_MODIFY_2 = "<!-- Visit https://github.com/logchange/logchange and leave a star \uD83C\uDF1F -->";
    private static final String DO_NOT_MODIFY_3 = "<!-- !!! ⚠️ DO NOT MODIFY THIS FILE, YOUR CHANGES WILL BE LOST ⚠️ !!! -->";


    @Override
    public String toString() {
        return getMeta();
    }

    public String getMeta() {
        return FORMATTER_OFF + "\n" +
                NOINSPECTION + "\n" +
                COMMENT + "\n" +
                "\n" +
                DO_NOT_MODIFY_1  + "\n" +
                DO_NOT_MODIFY_2  + "\n" +
                DO_NOT_MODIFY_3  + "\n" +
                "\n\n";
    }
}
