package dev.logchange.commands.add;

import lombok.Value;

import java.util.List;

@Value
public class AddChangelogEntryBatchModeParams {
    String title;
    String author;
    List<String> authors;
    String type;
    String linkName;
    String linkUrl;

    public static AddChangelogEntryBatchModeParams of(String title, String author, List<String> authors, String type, String linkName, String linkUrl) {
        validateAuthors(author, authors);
        return new AddChangelogEntryBatchModeParams(title, author, authors, type, linkName, linkUrl);
    }

    private static void validateAuthors(String author, List<String> authors) {
        if (author != null && authors != null && !authors.isEmpty()) {
            throw new IllegalArgumentException("It is not allowed to simultaneously use of 'author' and 'authors' options!");
        }
    }
}