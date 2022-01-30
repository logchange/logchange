package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.format.md.MD;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

class MDChangelogVersionImportantNotes implements MD {

    private final List<String> importantNotes;

    public MDChangelogVersionImportantNotes(List<String> importantNotes) {
        this.importantNotes = importantNotes;
    }

    @Override
    public String toString() {
        return getImportantNotes();
    }

    private String getImportantNotes() {
        if (importantNotes.size() != 0) {
            return new Heading("Important notes", 3)
                    + "\n\n"
                    + new UnorderedList<>(importantNotes)
                    + "\n\n";
        } else {
            return StringUtils.EMPTY;
        }
    }
}
