package dev.logchange.core.domain.changelog.model.entry;

public class ChangelogEntryImportantNote {

    private final String value;

    private ChangelogEntryImportantNote(String value) {
        this.value = value;
    }

    public static ChangelogEntryImportantNote of(String value) {
        return new ChangelogEntryImportantNote(value);
    }

    public String getValue() {
        return value;
    }
}
