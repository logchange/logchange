package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;
import dev.logchange.md.MarkdownBasics;
import dev.logchange.md.list.MarkdownLists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

class MDChangelogVersionImportantNotes extends Configurable implements MD {

    private final List<String> importantNotes;

    public MDChangelogVersionImportantNotes(List<String> importantNotes, Config config) {
        super(config);
        this.importantNotes = importantNotes;
    }

    @Override
    public String toString() {
        return getImportantNotes();
    }

    private String getImportantNotes() {
        if (importantNotes.size() != 0) {
            return MarkdownBasics.heading(getConfig().getLabels().getImportantNotes(), 3)
                    + "\n\n"
                    + MarkdownLists.unorderedList(importantNotes)
                    + "\n\n";
        } else {
            return StringUtils.EMPTY;
        }
    }
}
