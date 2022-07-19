package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryMergeRequest;
import dev.logchange.core.format.md.MD;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

class MDChangelogEntryMergeRequest implements MD {

    private static final String mergeRequestFormat = "!${merge_request}";

    private final ChangelogEntryMergeRequest mergeRequest;

    MDChangelogEntryMergeRequest(ChangelogEntryMergeRequest mergeRequest) {
        this.mergeRequest = mergeRequest;
    }

    @Override
    public String toString() {
        return getMergeRequest();
    }

    private String getMergeRequest() {
        if (mergeRequest == null) {
            return StringUtils.EMPTY;
        }

        Map<String, Long> valuesMap = new HashMap<>();
        valuesMap.put("merge_request", mergeRequest.getValue());
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(mergeRequestFormat);
    }
}
