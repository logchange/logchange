package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryLink;
import dev.logchange.core.format.md.MD;
import dev.logchange.md.MarkdownBasics;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

class MDChangelogEntryLinks implements MD {

    private final List<ChangelogEntryLink> links;

    MDChangelogEntryLinks(List<ChangelogEntryLink> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return getLinks();
    }

    private String getLinks() {
        if (links == null || links.isEmpty()) {
            return StringUtils.EMPTY;
        }

        StringBuilder mdLinks = new StringBuilder(StringUtils.EMPTY);
        for (ChangelogEntryLink link : links) {
            String name = link.getName();
            String url = link.getUrl();
            if (StringUtils.isBlank(name) || StringUtils.isBlank(url)) {
                // Skip invalid links defensively; lint should prevent these earlier
                continue;
            }
            mdLinks.append(MarkdownBasics.link(name, url)).append(" ");
        }
        return mdLinks.toString().trim();
    }
}

