package dev.logchange.core.domain.changelog.model.entry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;

/**
 * Enum for types supported by Changes.xml.
 */
@Log
@Getter
@AllArgsConstructor
public enum ChangesXMLEntryType {

    ADD("add", 1),
    FIX("fix", 2),
    UPDATE("update", 3),
    REMOVE("remove", 4);

    private final String type;
    private final Integer order;

    public static ChangesXMLEntryType getXmlTypeFromMarkdownEntryType(ChangelogEntryType markdownEntryType) {
        log.info("Changelog entry key: " + markdownEntryType.getKey());
        if ("added".equals(markdownEntryType.getKey())) {
            return ADD;
        } else if ("fixed".equals(markdownEntryType.getKey()) || "changed".equals(markdownEntryType.getKey())) {
            return FIX;
        } else if ("removed".equals(markdownEntryType.getKey())) {
            return REMOVE;
        } else {
            return ChangesXMLEntryType.UPDATE;
        }
    }
}
