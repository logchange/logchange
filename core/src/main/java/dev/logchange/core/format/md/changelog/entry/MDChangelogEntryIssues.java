package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.format.md.MD;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

class MDChangelogEntryIssues implements MD {

    private final List<String> issues;

    MDChangelogEntryIssues(List<String> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return getIssues();
    }

    private String getIssues() {
        if (issues.isEmpty()) {
            return StringUtils.EMPTY;
        }

        StringBuilder mdIssues = new StringBuilder(StringUtils.EMPTY);
        for (String issue : issues) {
            mdIssues.append(new MDChangelogEntryIssue(issue)).append(" ");
        }

        return mdIssues.toString().trim();
    }
}
