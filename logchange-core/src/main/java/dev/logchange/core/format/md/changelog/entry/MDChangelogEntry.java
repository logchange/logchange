package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.format.md.MD;
import dev.logchange.md.list.MarkdownLists;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

public class MDChangelogEntry implements MD {

    private static final String entryFormat = "${prefix}${title} ${merge_requests} ${issues} ${links} ${authors}";

    private final ChangelogEntry entry;

    private final MDChangelogEntryPrefix prefix;
    private final MDChangelogEntryMergeRequests mdMergeRequests;
    private final MDChangelogEntryIssues mdIssues;
    private final MDChangelogEntryLinks mdLinks;
    private final MDChangelogEntryAuthors mdAuthors;

    public MDChangelogEntry(ChangelogEntry entry) {
        this.entry = entry;
        this.prefix = new MDChangelogEntryPrefix(entry.getPrefix());
        this.mdMergeRequests = new MDChangelogEntryMergeRequests(entry.getMergeRequests());
        this.mdIssues = new MDChangelogEntryIssues(entry.getIssues());
        this.mdLinks = new MDChangelogEntryLinks(entry.getLinks());
        this.mdAuthors = new MDChangelogEntryAuthors(entry.getAuthors());
    }

    @Override
    public String toString() {
        return MarkdownLists.unorderedListItem(getEntry());
    }

    protected String getEntry() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("prefix", prefix.toMD());
        valuesMap.put("title", entry.getTitle().getValue());
        valuesMap.put("merge_requests", mdMergeRequests.toMD());
        valuesMap.put("issues", mdIssues.toMD());
        valuesMap.put("links", mdLinks.toMD());
        valuesMap.put("authors", mdAuthors.toMD());

        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(entryFormat).replaceAll("\\s{2,}", " ");
    }
}
