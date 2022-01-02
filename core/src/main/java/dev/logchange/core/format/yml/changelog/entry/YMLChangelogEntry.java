package dev.logchange.core.format.yml.changelog.entry;

import de.beosign.snakeyamlanno.constructor.AnnotationAwareConstructor;
import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryAuthor;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryConfiguration;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryLink;
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
    private String mergeRequest;
    private List<String> issues;
    private List<YMLChangelogEntryLink> links;
    private YMLChangelogEntryType type;
    private List<String> importantNotes;
    private List<YMLChangelogEntryConfiguration> configurations;

    public static YMLChangelogEntry of(InputStream input) {
        Yaml yaml = new Yaml(new AnnotationAwareConstructor(YMLChangelogEntry.class));
        return yaml.load(input);
    }

    @YamlProperty(key = "merge_request")
    public void setMergeRequest(String mergeRequest) {
        this.mergeRequest = mergeRequest;
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
        System.out.println("A");
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public ChangelogEntry to() {
        return ChangelogEntry.of(
                title,
                type.to(),
                mergeRequest,
                issues,
                links(),
                authors(),
                importantNotes(),
                changelogEntryConfiguration()
        );
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
}
