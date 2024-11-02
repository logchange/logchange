package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryImportantNote;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;
import dev.logchange.core.format.md.changelog.entry.MDChangelogEntryPrefix;
import dev.logchange.md.MarkdownBasics;
import dev.logchange.md.list.MarkdownLists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

class MDChangelogVersionImportantNotes extends Configurable implements MD {

    private final List<ChangelogEntryImportantNote> importantNotes;

    public MDChangelogVersionImportantNotes(List<ChangelogEntryImportantNote> importantNotes, Config config) {
        super(config);
        this.importantNotes = importantNotes;
    }

    @Override
    public String toString() {
        return getImportantNotes();
    }

    private String getImportantNotes() {
        if (importantNotes == null || importantNotes.isEmpty()) {
            return StringUtils.EMPTY;
        }

        List<String> notes = importantNotes.stream()
                .map(note -> MDChangelogEntryPrefix.of(note.getPrefix()) + note.getValue())
                .collect(Collectors.toList());

        return MarkdownBasics.heading(getConfig().getLabels().getImportantNotes(), 3)
                + "\n\n"
                + MarkdownLists.unorderedList(notes)
                + "\n\n";

    }
}
