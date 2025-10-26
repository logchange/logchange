package dev.logchange.core.format.yml.changelog.entry;

import java.util.Collection;

public class YMLChangelogEntryParseException extends RuntimeException {
    private final String ymlEntryPath;
    private final Collection<YmlInvalidProperty> invalidProperties;

    public YMLChangelogEntryParseException(String ymlEntryPath, Collection<YmlInvalidProperty> errors) {
        super(toString(ymlEntryPath, errors));

        this.ymlEntryPath = ymlEntryPath;
        this.invalidProperties = errors;
    }

    public static String toString(String path, Collection<YmlInvalidProperty> invalidProperties) {
        StringBuilder sb = new StringBuilder();
        sb.append("Errors in ").append(path).append(":\n");

        for (YmlInvalidProperty error : invalidProperties) {
            sb.append("\t")
                    .append(error.getMessage())
                    .append("\n");
        }

        return sb.toString();
    }

    public String getYmlEntryPath() {
        return ymlEntryPath;
    }

    public Collection<YmlInvalidProperty> getInvalidProperties() {
        return invalidProperties;
    }
}


