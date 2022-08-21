package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import net.steppschuh.markdowngenerator.text.heading.Heading;
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
            return new Heading(getConfig().getLabels().getImportantNotes(), 3)
                    + "\n\n"
                    + new UnorderedList<>(importantNotes)
                    + "\n\n";
        } else {
            return StringUtils.EMPTY;
        }
    }
}
