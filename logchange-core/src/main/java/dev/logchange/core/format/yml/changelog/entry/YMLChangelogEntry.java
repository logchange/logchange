package dev.logchange.core.format.yml.changelog.entry;

import de.beosign.snakeyamlanno.constructor.AnnotationAwareConstructor;
import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import de.beosign.snakeyamlanno.representer.AnnotationAwareRepresenter;
import dev.logchange.core.domain.changelog.model.entry.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLChangelogEntry {

    private String title;
    private List<YMLChangelogEntryAuthor> authors;
    private List<String> mergeRequests;
    private List<String> issues;
    private List<YMLChangelogEntryLink> links;
    private YMLChangelogEntryType type;
    private List<String> importantNotes;
    private List<YMLChangelogEntryConfiguration> configurations;

    public static YMLChangelogEntry of(InputStream input) {
        Yaml yaml = new Yaml(new AnnotationAwareConstructor(YMLChangelogEntry.class));
        return yaml.load(input);
    }

    public String toYMLString(){
        Yaml yaml = new Yaml(new AnnotationAwareRepresenter());
        return yaml.dump(this);
    }

    @YamlProperty(key = "merge_requests")
    public void setMergeRequests(List<String> mergeRequests) {
        this.mergeRequests = mergeRequests;
    }

    @YamlProperty(key = "type", converter = YMLChangelogEntryTypeConverter.class)
    public void setType(YMLChangelogEntryType type) {
        this.type = type;
    }

    @YamlProperty(key = "important_notes")
    public void setImportantNotes(List<String> importantNotes) {
        this.importantNotes = importantNotes;
    }

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        System.out.println("Unknown property: " + key + " with value " + value);
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public ChangelogEntry to() {
        return ChangelogEntry.builder()
                .title(ChangelogEntryTitle.of(title))
                .type(type.to())
                .mergeRequests(mergeRequests())
                .issues(issues())
                .links(links())
                .authors(authors())
                .importantNotes(importantNotes())
                .configurations(changelogEntryConfiguration())
                .build();
    }

    private List<ChangelogEntryMergeRequest> mergeRequests() {
        if (mergeRequests == null) {
            return Collections.emptyList();
        } else {
            return mergeRequests.stream()
                    .map(ChangelogEntryMergeRequest::of)
                    .collect(Collectors.toList());
        }
    }

    private List<String> issues() {
        if (issues == null) {
            return Collections.emptyList();
        } else {
            return issues;
        }
    }

    private List<ChangelogEntryLink> links() {
        if (links == null) {
            return Collections.emptyList();
        } else {
            return links.stream()
                    .map(YMLChangelogEntryLink::to)
                    .collect(Collectors.toList());
        }
    }

    private List<ChangelogEntryAuthor> authors() {
        if (authors == null) {
            return Collections.emptyList();
        } else {
            return authors.stream()
                    .map(YMLChangelogEntryAuthor::to)
                    .collect(Collectors.toList());
        }
    }

    private List<String> importantNotes() {
        if (importantNotes == null) {
            return Collections.emptyList();
        } else {
            return importantNotes;
        }
    }

    private List<ChangelogEntryConfiguration> changelogEntryConfiguration() {
        if (configurations == null) {
            return Collections.emptyList();
        } else {
            return configurations.stream()
                    .map(YMLChangelogEntryConfiguration::to)
                    .collect(Collectors.toList());
        }
    }

    public static YMLChangelogEntry of(ChangelogEntry entry) {
        return YMLChangelogEntry.builder()
                .title(entry.getTitle().getValue())
                .authors(entry.getAuthors().stream().map(YMLChangelogEntryAuthor::of).collect(Collectors.toList()))
                .mergeRequests(entry.getMergeRequests().stream().map(ChangelogEntryMergeRequest::getValue).collect(Collectors.toList()))
                .issues(entry.getIssues())
                .links(entry.getLinks().stream().map(YMLChangelogEntryLink::of).collect(Collectors.toList()))
                .type(YMLChangelogEntryType.of(entry.getType()))
                .importantNotes(entry.getImportantNotes())
                .configurations(entry.getConfigurations().stream().map(YMLChangelogEntryConfiguration::of).collect(Collectors.toList()))
                .build();
    }
}
