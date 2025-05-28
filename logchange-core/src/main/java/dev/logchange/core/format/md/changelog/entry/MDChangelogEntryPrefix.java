package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.format.md.MD;
import dev.logchange.md.MarkdownBasics;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor(staticName = "of")
public class MDChangelogEntryPrefix implements MD {

    public static final MDChangelogEntryPrefix EMPTY = new MDChangelogEntryPrefix("");
    private static final String prefixFormat = "${prefix} - ";
    private final String prefix;

    @Override
    public String toString() {
        return getPrefix();
    }

    private String getPrefix() {
        if (StringUtils.isBlank(this.prefix)) {
            return StringUtils.EMPTY;
        }

        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("prefix", MarkdownBasics.bold(this.prefix));
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(prefixFormat);
    }
}
