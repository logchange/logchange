package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.format.md.MD;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

class MDChangelogEntryIssue implements MD {

    private static final String issueFormat = "#${issue}";

    private final Long issue;

    MDChangelogEntryIssue(Long issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return getIssue();
    }

    private String getIssue() {
        if (issue == null) {
            return StringUtils.EMPTY;
        }

        Map<String, Long> valuesMap = new HashMap<>();
        valuesMap.put("issue", issue);
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(issueFormat);
    }
}
