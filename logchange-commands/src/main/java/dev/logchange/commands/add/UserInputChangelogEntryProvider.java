package dev.logchange.commands.add;

import dev.logchange.core.domain.changelog.model.entry.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class UserInputChangelogEntryProvider implements ChangelogEntryProvider {

    private final AddEntryPrompter prompter;
    private final AddChangelogEntryBatchModeParams batchModeParams;

    public ChangelogEntry get() {
        try {
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
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private ChangelogEntryTitle getTitle() {
        String title = batchModeParams.getTitle();
        if (StringUtils.isNotBlank(title)) {
            return ChangelogEntryTitle.of(title);
        }

        while (true) {
            try {
                return ChangelogEntryTitle.of(prompter.prompt("What is changelog's entry title(e.g. Adding new awesome product to order list)"));
            } catch (IllegalArgumentException e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }

    private ChangelogEntryType getType() {
        String type = batchModeParams.getType();
        if (StringUtils.isNotBlank(type)) {
            return ChangelogEntryType.fromNameIgnoreCase(type);
        }

        String prompt = Arrays.stream(ChangelogEntryType.values())
                .map(ChangelogEntryType::toString)
                .collect(Collectors.joining(System.lineSeparator())) +
                System.lineSeparator() +
                "What is changelog's entry type (choose number from above) ?";

        while (true) {
            try {
                return ChangelogEntryType.from(prompter.prompt(prompt));
            } catch (IllegalArgumentException e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }

    private List<ChangelogEntryMergeRequest> getMergeRequests() {
        String prompt = "What is the MR's number? (numbers, seperated with comma) [press ENTER to skip] ";

        while (true) {
            try {
                String response = prompter.prompt(prompt);
                return Arrays.stream(response.replaceAll("\\s+", "").split(","))
                        .filter(StringUtils::isNotBlank)
                        .map(Long::valueOf)
                        .map(ChangelogEntryMergeRequest::of)
                        .collect(Collectors.toList());
            } catch (Exception e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }

    private List<Long> getIssues() {
        String prompt = "What is the issue's number?(numbers, seperated with comma) [press ENTER to skip] ";

        while (true) {
            try {
                String response = prompter.prompt(prompt);
                return Arrays.stream(response.replaceAll("\\s+", "").split(","))
                        .filter(StringUtils::isNotBlank)
                        .map(Long::valueOf)
                        .collect(Collectors.toList());
            } catch (Exception e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }

    private List<ChangelogEntryLink> getLinks() {
        String linkUrl = batchModeParams.getLinkUrl();
        if (StringUtils.isNotBlank(linkUrl)) {
            return Collections.singletonList(ChangelogEntryLink.of(batchModeParams.getLinkName(), linkUrl));
        }

        String prompt = "Is there any links you want to include? [Y/y - YES] [N/n - NO] [press ENTER to skip] ";
        List<ChangelogEntryLink> links = new ArrayList<>();

        while (true) {
            try {
                String response = prompter.prompt(prompt);
                if (response.trim().equalsIgnoreCase("Y")) {
                    return getLinksRecur(links);
                } else {
                    return Collections.emptyList();
                }
            } catch (Exception e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }

    private List<ChangelogEntryLink> getLinksRecur(List<ChangelogEntryLink> links) {
        String name = prompter.prompt("Give a link caption [press ENTER to skip] ");
        String link = prompter.prompt("Give a link ");
        links.add(ChangelogEntryLink.of(name, link));

        if (prompter.prompt("Is there any other links you want to include? [Y/y - YES] [N/n - NO]").trim().equalsIgnoreCase("Y")) {
            return getLinksRecur(links);
        } else {
            return links;
        }
    }

    private List<ChangelogEntryAuthor> getAuthors() {
        String author = batchModeParams.getAuthor();
        List<String> authorsList = batchModeParams.getAuthors();
        if (StringUtils.isNotBlank(author) || (authorsList != null && !authorsList.isEmpty())) {
            if (StringUtils.isNotBlank(author)) {
                return Collections.singletonList(ChangelogEntryAuthor.of("", author.trim(), ""));
            } else {
                return authorsList.stream()
                        .map(a -> ChangelogEntryAuthor.of("", a.trim(), ""))
                        .collect(Collectors.toList());
            }
        }

        String prompt = "Is there any authors of this change, that you want to include? [Y/y - YES] [N/n - NO] [press ENTER to skip] ";
        List<ChangelogEntryAuthor> authors = new ArrayList<>();

        while (true) {
            try {
                String response = prompter.prompt(prompt);
                if (response.trim().equalsIgnoreCase("Y")) {
                    return getAuthorsRecur(authors);
                } else {
                    return Collections.emptyList();
                }
            } catch (Exception e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }

    private List<ChangelogEntryAuthor> getAuthorsRecur(List<ChangelogEntryAuthor> authors) {
        try {
            String name = prompter.prompt("Give a name of author [press ENTER to skip] ");
            String nick = prompter.prompt("Give a nickname of author [press ENTER to skip] ");
            String url = prompter.prompt("Give a url of author profile [press ENTER to skip] ");
            authors.add(ChangelogEntryAuthor.of(name, nick, url));
        } catch (IllegalArgumentException e) {
            prompter.showMessage(e.getMessage());
            return getAuthorsRecur(authors);
        }

        if (prompter.prompt("Is there any other links you want to include? [Y/y - YES] [N/n - NO]").trim().equalsIgnoreCase("Y")) {
            return getAuthorsRecur(authors);
        } else {
            return authors;
        }
    }

    private List<ChangelogEntryImportantNote> getNotes() {
        String prompt = "Is there any important information about this change (f.e. it affects other system) [Y/y - YES] [N/n - NO] [press ENTER to skip] ";
        List<String> notes = new ArrayList<>();

        while (true) {
            try {
                String response = prompter.prompt(prompt);
                if (response.trim().trim().equalsIgnoreCase("Y")) {
                    return getNotesRecur(notes).stream()
                            .map(ChangelogEntryImportantNote::of)
                            .collect(Collectors.toList());
                } else {
                    return Collections.emptyList();
                }
            } catch (Exception e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }

    private List<String> getNotesRecur(List<String> notes) {
        String note = prompter.prompt("Give a note ");
        notes.add(note.trim());

        if (prompter.prompt("Is there any other note you want to include? [Y/y - YES] [N/n - NO]").trim().equalsIgnoreCase("Y")) {
            return getNotesRecur(notes);
        } else {
            return notes;
        }
    }

    private List<ChangelogEntryConfiguration> getConfigurations() {
        String prompt = "Is there any configuration change regarding this change (f.e. new feature flag) [Y/y - YES] [N/n - NO] [press ENTER to skip] ";
        List<ChangelogEntryConfiguration> configurations = new ArrayList<>();

        while (true) {
            try {
                String response = prompter.prompt(prompt);
                if (response.trim().trim().equalsIgnoreCase("Y")) {
                    return getConfigurationsRecur(configurations);
                } else {
                    return Collections.emptyList();
                }
            } catch (Exception e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }

    private List<ChangelogEntryConfiguration> getConfigurationsRecur(List<ChangelogEntryConfiguration> configurations) {
        try {
            String type = prompter.prompt("Give a type of a configuration property (f.e. database, or system env, or application.properties) ");
            ChangelogEntryConfigurationAction action = getConfigurationAction();
            String key = prompter.prompt("Give a key of configuration property (f.e. server.port) ");
            String defaultValue = prompter.prompt("Give a default value of configuration property (f.e. 8443) [press ENTER to skip] ");
            String description = prompter.prompt("Give a description of configuration property (f.e. Port to handle incoming https traffic ) [press ENTER to skip] ");
            String moreInfo = prompter.prompt("Here you can specify more information about configuration property (f.e. Remember to disable port 8080 to disable standard http traffic ) [press ENTER to skip] ");
            configurations.add(ChangelogEntryConfiguration.of(type, action, key, defaultValue, description, moreInfo));
        } catch (IllegalArgumentException e) {
            prompter.showMessage(e.getMessage());
            return getConfigurationsRecur(configurations);
        }

        if (prompter.prompt("Is there any other configuration change you want to include? [Y/y - YES] [N/n - NO]").trim().equalsIgnoreCase("Y")) {
            return getConfigurationsRecur(configurations);
        } else {
            return configurations;
        }
    }

    private ChangelogEntryConfigurationAction getConfigurationAction() {
        String prompt = Arrays.stream(ChangelogEntryConfigurationAction.values())
                .map(ChangelogEntryConfigurationAction::toString)
                .collect(Collectors.joining(System.lineSeparator())) +
                System.lineSeparator() +
                "What is the configuration action (choose number from above) ?";

        while (true) {
            try {
                return ChangelogEntryConfigurationAction.from(prompter.prompt(prompt));
            } catch (IllegalArgumentException e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }
}
