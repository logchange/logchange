package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.format.md.MD;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

class MDChangelogEntryIssues implements MD {

    private final List<Long> issues;

    MDChangelogEntryIssues(List<Long> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return getIssues();
    }

    private String getIssues() {
        if (issues == null || issues.isEmpty()) {
            return StringUtils.EMPTY;
        }

        StringBuilder mdIssues = new StringBuilder(StringUtils.EMPTY);
        for (Long issue : issues) {
            mdIssues.append(new MDChangelogEntryIssue(issue)).append(" ");
        }

        return mdIssues.toString().trim();
    }
}
