package dev.logchange.md;



/**
 * Created by steppschuh on 15/12/2016.
 */

public abstract class StringUtil {

    public static String fillUpLeftAligned(String value, char fill, int length) {
        return value.length() >= length ? value : String.format("%-" + length + "s", value).replace(' ', fill);
    }

    static String fillUpRightAligned(String value, char fill, int length) {
        return value.length() >= length ? value : String.format("%" + length + "s", value).replace(' ', fill);
    }

    public static String surroundValueWith(String value, String surrounding) {
        return surrounding + value + surrounding;
    }
}
