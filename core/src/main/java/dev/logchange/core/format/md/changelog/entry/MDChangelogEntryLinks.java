package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.format.md.MD;
import dev.logchange.core.model.entry.ChangelogEntryLink;
import net.steppschuh.markdowngenerator.link.Link;
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
            mdLinks.append(new Link(link.getName(), link.getUrl())).append(" ");
        }
        return mdLinks.toString().trim();
    }
}

