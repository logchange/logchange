package dev.logchange.commands.add;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChangelogEntryProviderFactory {

    private final boolean empty;

    private final boolean batchMode;

    private final AddChangelogEntryBatchModeParams batchModeParams;
    private final AddEntryPrompter prompter;

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

        return new UserInputChangelogEntryProvider(prompter, batchModeParams);
    }
}
