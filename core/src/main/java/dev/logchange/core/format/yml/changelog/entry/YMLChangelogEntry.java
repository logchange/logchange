package dev.logchange.core.format.yml.changelog.entry;

import de.beosign.snakeyamlanno.constructor.AnnotationAwareConstructor;
import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;


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
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public ChangelogEntry to() {
        return null;
    }
}
