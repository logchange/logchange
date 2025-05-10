package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.DetachedImportantNote;
import dev.logchange.core.domain.changelog.model.entry.ChangelogModule;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;
import dev.logchange.core.format.md.changelog.entry.MDChangelogEntryPrefix;
import dev.logchange.md.MarkdownBasics;
import dev.logchange.md.list.MarkdownLists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MDChangelogVersionImportantNotes extends Configurable implements MD {

    private final List<DetachedImportantNote> detachedImportantNotes;

    public MDChangelogVersionImportantNotes(List<DetachedImportantNote> detachedImportantNotes, Config config) {
        super(config);
        this.detachedImportantNotes = detachedImportantNotes;
    }

    @Override
    public String toString() {
        return getDetachedImportantNotes();
    }

    private String getDetachedImportantNotes() {
        if (detachedImportantNotes == null || detachedImportantNotes.isEmpty()) {
            return StringUtils.EMPTY;
        }

        MDModuleStructure<DetachedImportantNote> structure = MDModuleStructure.build(detachedImportantNotes);

        Stream<String> nonModule = renderListOfNotes(structure.getNoModules());
        Stream<String> withModule = structure.getGroups().entrySet().stream().flatMap(entry ->
                renderListOfNotes(entry.getValue())
        );
        List<String> notes = Stream.concat(nonModule, withModule).collect(Collectors.toList());

        return MarkdownBasics.heading(getConfig().getLabels().getImportantNotes(), 3)
                + "\n\n"
                + MarkdownLists.unorderedList(notes)
                + "\n\n";

    }

    private Stream<String> renderListOfNotes(List<DetachedImportantNote> detachedImportantNotes) {
        return detachedImportantNotes.stream()
                .map(note -> {
                    String content = note.getModules()
                            .stream()
                            .map(ChangelogModule::getName)
                            .collect(Collectors.joining(" "));
                    return MDChangelogEntryPrefix.of(content) + note.getValue();
                });
    }
}
