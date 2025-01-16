package dev.logchange.commands.add;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class AddChangelogEntryBatchModeParams {
    String title;
    String author;
    List<String> authors;
    String type;
    String linkName;
    String linkUrl;
}