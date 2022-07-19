package dev.logchange.maven_plugin.mojo.add.entry;

import lombok.RequiredArgsConstructor;
import org.codehaus.plexus.components.interactivity.Prompter;

@RequiredArgsConstructor
public class ChangelogEntryProviderFactory {

    private final boolean empty;
    private final Prompter prompter;

    public ChangelogEntryProvider create() {
        if (empty) {
            return new EmptyChangelogEntryProvider();
        } else {
            return new UserInputChangelogEntryProvider(prompter);
        }
    }
}
