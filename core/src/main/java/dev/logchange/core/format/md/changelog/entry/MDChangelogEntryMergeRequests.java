package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.format.md.MD;
import dev.logchange.core.model.ChangelogMergeRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

class MDChangelogEntryMergeRequests implements MD {

    private final List<ChangelogMergeRequest> mergeRequests;

    MDChangelogEntryMergeRequests(List<ChangelogMergeRequest> mergeRequests) {
        this.mergeRequests = mergeRequests;
    }

    @Override
    public String toString() {
        return getMergeRequest();
    }

    private String getMergeRequest() {
        if (mergeRequests == null || mergeRequests.isEmpty()) {
            return StringUtils.EMPTY;
        }

        StringBuilder md = new StringBuilder(StringUtils.EMPTY);
        for (ChangelogMergeRequest mergeRequest : mergeRequests) {
            md.append(new MDChangelogEntryMergeRequest(mergeRequest)).append(" ");
        }

        return md.toString().trim();
    }
}
