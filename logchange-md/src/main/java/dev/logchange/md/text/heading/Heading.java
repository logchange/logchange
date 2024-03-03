package dev.logchange.md.text.heading;

import dev.logchange.md.util.StringUtil;

public class Heading {

    public static final int MINIMUM_LEVEL = 1;
    public static final int MAXIMUM_LEVEL = 6;

    public static final char UNDERLINE_CHAR_1 = '=';
    public static final char UNDERLINE_CHAR_2 = '-';

    public static String stringOf(Object value, int level) {
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
        return StringUtil.fillUpRightAligned("", "#", level) + " ";
    }

    private static String successor(int level, Object value) {
        if (level < 3) {
            char underlineChar = (level == 1) ? UNDERLINE_CHAR_1 : UNDERLINE_CHAR_2;
            return System.lineSeparator() + StringUtil.fillUpLeftAligned("", "" + underlineChar, String.valueOf(value).length());
        }
        return "";
    }
}

