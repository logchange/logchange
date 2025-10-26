package dev.logchange.core.format.yml.changelog.entry;

public class YmlInvalidProperty {
    private final String property;
    private final InvalidType invalidType;
    private final String value;

    private YmlInvalidProperty(String property, InvalidType invalidType, String value) {
        this.property = property;
        this.invalidType = invalidType;
        this.value = value;
    }

    public static YmlInvalidProperty missing(String property) {
        return new YmlInvalidProperty(property, InvalidType.MISSING, "");
    }

    public static YmlInvalidProperty invalidProperty(String property, String value) {
        return new YmlInvalidProperty(property, InvalidType.INVALID_PROPERTY_NAME, value);
    }

    public static YmlInvalidProperty unknownError(String property, String value) {
        return new YmlInvalidProperty(property, InvalidType.UNKNOWN_ERROR, value);
    }

    public String getMessage() {
        switch (invalidType) {
            case MISSING:
                return "Missing " + property + " property!";
            case INVALID_PROPERTY_NAME:
                return "Unknown property [" + property + "] with value " + value;
            case UNKNOWN_ERROR:
                return "Error for property [" + property + "] - " + value;
        }

        throw new IllegalArgumentException("Unknown invalid type " + invalidType);
    }

    private enum InvalidType {
        MISSING,
        INVALID_PROPERTY_NAME,
        UNKNOWN_ERROR
    }
}