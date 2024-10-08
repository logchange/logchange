package dev.logchange.core.format.yml.changelog.entry;

import java.util.Collection;

public class YMLChangelogInvalidConfigValuesException extends RuntimeException {
  public String path;
  public Collection<String> invalidProperties;

  public static String toString(String path, Collection<String> invalidProperties) {
    StringBuilder sb = new StringBuilder();
    sb.append("Errors in ").append(path).append(":\n");

    for (String error : invalidProperties) {
        sb.append(error)
          .append("\n");
    }

    return sb.toString();
  }

  public YMLChangelogInvalidConfigValuesException(String path, Collection<String> errors) {
    super(toString(path, errors));

    this.path = path;
    this.invalidProperties = errors;
  }
}
