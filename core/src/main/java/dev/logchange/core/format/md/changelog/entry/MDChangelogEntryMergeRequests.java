package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryMergeRequest;
import dev.logchange.core.format.md.MD;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

class MDChangelogEntryMergeRequests implements MD {

    private final List<ChangelogEntryMergeRequest> mergeRequests;

    MDChangelogEntryMergeRequests(List<ChangelogEntryMergeRequest> mergeRequests) {
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
        for (ChangelogEntryMergeRequest mergeRequest : mergeRequests) {
            md.append(new MDChangelogEntryMergeRequest(mergeRequest)).append(" ");
        }

        return md.toString().trim();
    }
}
