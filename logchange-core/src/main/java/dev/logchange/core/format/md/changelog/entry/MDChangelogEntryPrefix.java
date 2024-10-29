package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryPrefix;
import dev.logchange.core.format.md.MD;
import dev.logchange.md.MarkdownBasics;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

public class MDChangelogEntryPrefix implements MD {
    private static final String prefixFormat = "${prefix} -";
    private final ChangelogEntryPrefix prefix;

    public MDChangelogEntryPrefix(ChangelogEntryPrefix prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return getPrefix();
    }

    private String getPrefix() {
        if (this.prefix == null) {
            return StringUtils.EMPTY;
        }

        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("prefix", MarkdownBasics.bold(this.prefix.getValue()));
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(prefixFormat);
    }
}
