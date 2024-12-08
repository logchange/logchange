package dev.logchange.maven_plugin.mojo.add.entry;

import dev.logchange.maven_plugin.mojo.add.AddChangelogEntryMojo.AddChangelogEntryBatchModeParams;
import lombok.RequiredArgsConstructor;
import org.codehaus.plexus.components.interactivity.Prompter;

@RequiredArgsConstructor
public class ChangelogEntryProviderFactory {

    private final boolean empty;

    private final boolean batchMode;

    private final AddChangelogEntryBatchModeParams batchModeParams;
    private final Prompter prompter;

    public ChangelogEntryProvider create() {
        if (batchMode && empty) {
            String msg = "Cannot use empty (-Dempty) and batchMode(-DbatchMode) at the same time!";
            throw new RuntimeException(msg);
        }

        if (empty) {
            return new EmptyChangelogEntryProvider();
        }

        if (batchMode) {
            return new BatchModeChangelogEntryProvider(batchModeParams);
        }

        return new UserInputChangelogEntryProvider(prompter);
    }
}
