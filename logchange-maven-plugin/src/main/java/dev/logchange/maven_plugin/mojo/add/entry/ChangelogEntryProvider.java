package dev.logchange.maven_plugin.mojo.add.entry;

import dev.logchange.core.domain.changelog.model.entry.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.components.interactivity.Prompter;
import org.codehaus.plexus.components.interactivity.PrompterException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ChangelogEntryProvider {

    private final boolean empty;
    private final Prompter prompter;

    public ChangelogEntry get() throws PrompterException {
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

    private ChangelogEntryTitle getTitle() throws PrompterException {
        while (true) {
            try {
                return ChangelogEntryTitle.of(prompter.prompt("What is changelog's entry title(e.g. Adding new awesome product to order list)"));
            } catch (IllegalArgumentException e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }

    private ChangelogEntryType getType() throws PrompterException {
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

    private List<ChangelogEntryMergeRequest> getMergeRequests() throws PrompterException {
        String prompt = "What is the MR's number? (numbers, seperated with comma) [press ENTER to skip] ";

        while (true) {
            try {
                String response = prompter.prompt(prompt);
                return Arrays.stream(response.replaceAll("\\s+", "").split(","))
                        .filter(StringUtils::isNotBlank)
                        .map(ChangelogEntryMergeRequest::of)
                        .collect(Collectors.toList());
            } catch (Exception e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }

    private List<String> getIssues() throws PrompterException {
        String prompt = "What is the issue's number?(numbers, seperated with comma) [press ENTER to skip] ";

        while (true) {
            try {
                String response = prompter.prompt(prompt);
                return Arrays.stream(response.replaceAll("\\s+", "").split(",")).
                        filter(StringUtils::isNotBlank)
                        .collect(Collectors.toList());
            } catch (Exception e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }

    private List<ChangelogEntryLink> getLinks() throws PrompterException {
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

    private List<ChangelogEntryLink> getLinksRecur(List<ChangelogEntryLink> links) throws PrompterException {
        String name = prompter.prompt("Give a link caption or press ENTER to skip");
        String link = prompter.prompt("Give a link");
        links.add(ChangelogEntryLink.of(name, link));

        if (prompter.prompt("Is there any other links you want to include? [Y/y - YES] [N/n - NO]").trim().equalsIgnoreCase("Y")) {
            return getLinksRecur(links);
        } else {
            return links;
        }
    }

    private List<ChangelogEntryAuthor> getAuthors() throws PrompterException {
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

    private List<ChangelogEntryAuthor> getAuthorsRecur(List<ChangelogEntryAuthor> authors) throws PrompterException {
        try {
            String name = prompter.prompt("Give a name of author or press ENTER to skip");
            String nick = prompter.prompt("Give a nickname of author or press ENTER to skip");
            String url = prompter.prompt("Give a url of author profile or press ENTER to skip");
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

    private List<String> getNotes() throws PrompterException {
        String prompt = "Is there any important information about this change (f.e. it affects other system) [Y/y - YES] [N/n - NO] [press ENTER to skip] ";
        List<String> notes = new ArrayList<>();

        while (true) {
            try {
                String response = prompter.prompt(prompt);
                if (response.trim().trim().equalsIgnoreCase("Y")) {
                    return getNotesRecur(notes);
                } else {
                    return Collections.emptyList();
                }
            } catch (Exception e) {
                prompter.showMessage(e.getMessage());
            }
        }
    }

    private List<String> getNotesRecur(List<String> notes) throws PrompterException {
        String note = prompter.prompt("Give a note");
        notes.add(note.trim());

        if (prompter.prompt("Is there any other note you want to include? [Y/y - YES] [N/n - NO]").trim().equalsIgnoreCase("Y")) {
            return getNotesRecur(notes);
        } else {
            return notes;
        }
    }

    private List<ChangelogEntryConfiguration> getConfigurations() {
        return Collections.emptyList();
    }
}
