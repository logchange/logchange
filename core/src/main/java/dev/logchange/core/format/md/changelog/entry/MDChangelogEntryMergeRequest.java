package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.format.md.MD;
import dev.logchange.core.model.ChangelogMergeRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

class MDChangelogEntryMergeRequest implements MD {

    private static final String mergeRequestFormat = "!${merge_request}";

    private final ChangelogMergeRequest mergeRequest;

    MDChangelogEntryMergeRequest(ChangelogMergeRequest mergeRequest) {
        this.mergeRequest = mergeRequest;
    }

    @Override
    public String toString() {
        return getMergeRequest();
    }

    private String getMergeRequest() {
        if (mergeRequest == null || StringUtils.isBlank(mergeRequest.getValue())) {
            return StringUtils.EMPTY;
        }

        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("merge_request", mergeRequest.getValue());
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(mergeRequestFormat);
    }
}
