package dev.logchange.commands.add;

import lombok.Value;

@Value(staticConstructor = "of")
public class AddChangelogEntryBatchModeParams {
    String title;
    String author;
    String type;
    String linkName;
    String linkUrl;
}