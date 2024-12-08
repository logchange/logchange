package dev.logchange.md;

class MarkdownHeading {
    private static final int MINIMUM_LEVEL = 1;
    private static final int MAXIMUM_LEVEL = 6;
    private static final char UNDERLINE_CHAR_1 = '=';
    private static final char UNDERLINE_CHAR_2 = '-';

    static String of(Object value, int level) {
        int trimmedLevel = trimLevel(level);
        return predecessor(trimmedLevel) + value + successor(trimmedLevel, value);
    }

    private static int trimLevel(int level) {
        return Math.min(MAXIMUM_LEVEL, Math.max(MINIMUM_LEVEL, level));
    }

    private static String predecessor(int level) {
        if (level < 3) {
            return "";
        }
        return fillUpRightAligned(level) + " ";
    }

    private static String fillUpRightAligned(int length) {
        return 0 >= length ? "" : String.format("%" + length + "s", "").replace(' ', '#');
    }

    private static String successor(int level, Object value) {
        if (level < 3) {
            char underlineChar = (level == 1) ? UNDERLINE_CHAR_1 : UNDERLINE_CHAR_2;
            return System.lineSeparator() + fillUpLeftAligned(underlineChar, String.valueOf(value).length());
        }
        return "";
    }

    private static String fillUpLeftAligned(char fill, int length) {
        return 0 >= length ? "" : String.format("%-" + length + "s", "").replace(' ', fill);
    }
}

