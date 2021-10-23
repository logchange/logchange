package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.format.md.MD;
import dev.logchange.core.model.ChangelogEntry;
import net.steppschuh.markdowngenerator.list.UnorderedListItem;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

class MDChangelogEntry implements MD {

    private static final String entryFormat = "${title} ${merge_request_format} ${issues} ${links} ${authors}";

    private final ChangelogEntry entry;

    //private final MarkdownChangelogEntryMergeRequest markdownChangelogEntryMergeRequest;
    //private final MarkdownChangelogEntryIssues markdownChangelogEntryIssues;
    //private final MarkdownChangelogEntryLinks markdownChangelogEntryLinks;
    private final MDChangelogEntryAuthors authors;

    public MDChangelogEntry(ChangelogEntry entry) {
        this.entry = entry;
//        this.markdownChangelogEntryMergeRequest = new MarkdownChangelogEntryMergeRequest(entry);
//        this.markdownChangelogEntryIssues = new MarkdownChangelogEntryIssues(entry);
//        this.markdownChangelogEntryLinks = new MarkdownChangelogEntryLinks(entry);
        this.authors = new MDChangelogEntryAuthors(entry.getAuthors());
    }

    @Override
    public String toString() {
        return new UnorderedListItem(getEntry()).toString();
    }

    private String getEntry() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("title", entry.getTitle());
//        valuesMap.put("merge_request_format", markdownChangelogEntryMergeRequest.getMergeRequest());
//        valuesMap.put("issues", markdownChangelogEntryIssues.getIssues());
//        valuesMap.put("links", markdownChangelogEntryLinks.getLinks());
        valuesMap.put("authors", authors.toMD());

        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(entryFormat).replaceAll("\\s{2,}", " ");
    }
}
