package dev.logchange.commands.add;

import dev.logchange.core.domain.changelog.model.entry.*;

import java.util.Collections;
import java.util.List;

class EmptyChangelogEntryProvider implements ChangelogEntryProvider {

    @Override
    public ChangelogEntry get() {
        return ChangelogEntry.builder()
                .title(getTitle())
                .type(getType())
                .mergeRequests(getMergeRequests())
                .issues(getIssues())
                .links(getLinks())
                .authors(getAuthors())
                .importantNotes(getNotes())
                .configurations(getConfigurations())
                .build();
    }

    private ChangelogEntryTitle getTitle() {
        return ChangelogEntryTitle.of("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
    }

    private ChangelogEntryType getType() {
        return ChangelogEntryType.values().get(0);
    }

    private List<ChangelogEntryMergeRequest> getMergeRequests() {
        return Collections.singletonList(ChangelogEntryMergeRequest.of(1L));
    }

    private List<Long> getIssues() {
        return Collections.singletonList(2L);
    }

    private List<ChangelogEntryLink> getLinks() {
        return Collections.singletonList(ChangelogEntryLink.of("Google", "https://google.com"));
    }

    private List<ChangelogEntryAuthor> getAuthors() {
        return Collections.singletonList(ChangelogEntryAuthor.of("Gal Anonim", "GaLaNo", "https://github.com/anon"));
    }

    private List<ChangelogEntryImportantNote> getNotes() {
        return Collections.singletonList(ChangelogEntryImportantNote.of("Phasellus euismod placerat ullamcorper. Nam a vehicula sapien, at accumsan purus."));
    }

    private List<ChangelogEntryConfiguration> getConfigurations() {
        return Collections.singletonList(ChangelogEntryConfiguration.of(
                "system environment",
                ChangelogEntryConfigurationAction.ADD,
                "ABC_DFG", "true", "Phasellus euismod placerat ullamcorper.", "Nam a vehicula sapien, at accumsan purus."));
    }
}
