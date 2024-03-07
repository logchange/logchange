package dev.logchange.md;



/**
 * Created by steppschuh on 15/12/2016.
 */

public abstract class StringUtil {

    public static String fillUpLeftAligned(String value, String fill, int length) {
        if (value.length() >= length) {
            return value;
        }
        StringBuilder valueBuilder = new StringBuilder(value);
        while (valueBuilder.length() < length) {
            valueBuilder.append(fill);
        }
        return valueBuilder.toString();
    }

    public static String fillUpRightAligned(String value, String fill, int length) {
        if (value.length() >= length) {
            return value;
        }
        StringBuilder valueBuilder = new StringBuilder(value);
        while (valueBuilder.length() < length) {
            valueBuilder.insert(0, fill);
        }
        return valueBuilder.toString();
    }

    public static String surroundValueWith(String value, String surrounding) {
        return surrounding + value + surrounding;
    }

}
