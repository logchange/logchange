package dev.logchange.core.format.yml.changelog.entry;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.logchange.core.domain.changelog.model.entry.*;
import dev.logchange.core.format.yml.ObjectMapperProvider;
import lombok.*;
import lombok.extern.java.Log;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

@Log
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLChangelogEntry {

    private static final String YML_HEADING = "# This file is used by logchange tool to generate CHANGELOG.md \uD83C\uDF33 \uD83E\uDE93 => \uD83E\uDEB5 \n" +
            "# Visit https://github.com/logchange/logchange and leave a star \uD83C\uDF1F \n" +
            "# More info about configuration you can find https://github.com/logchange/logchange#yaml-format ⬅️⬅ ️\n";


    @JsonProperty(index = 0)
    public String title;

    @Singular
    @JsonProperty(index = 1)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<YMLChangelogEntryAuthor> authors;

    @Singular
    @JsonProperty(value = "merge_requests", index = 2)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<Long> mergeRequests;

    @Singular
    @JsonProperty(index = 3)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<Long> issues;

    @Singular
    @JsonProperty(index = 4)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<YMLChangelogEntryLink> links;

    @JsonProperty(index = 5)
    public YMLChangelogEntryType type;

    @Singular
    @JsonProperty(value = "important_notes", index = 6)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<String> importantNotes;

    @Singular
    @JsonProperty(index = 7)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<YMLChangelogEntryConfiguration> configurations;

    @JsonIgnore
    private Set<Pair<String, String>> invalidProperties = new HashSet<>();

    @SneakyThrows
    public static YMLChangelogEntry of(InputStream input, String path) {
//        Yaml yaml = new Yaml(new AnnotationAwareConstructor(YMLChangelogEntry.class));
//        return yaml.load(input);

        ObjectMapper mapper = ObjectMapperProvider.get();
        YMLChangelogEntry res = mapper.readValue(input, YMLChangelogEntry.class);

        if (!res.invalidProperties.isEmpty()) {
            throw new YMLChangelogEntryConfigException(path, res.invalidProperties);
        }
        
        return res;
    }

    @SneakyThrows
    public String toYMLString() {
        return YML_HEADING + ObjectMapperProvider.get()
                .writeValueAsString(this);
    }

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        invalidProperties.add(Pair.of(key, value.toString()));
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

    private List<Long> issues() {
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
