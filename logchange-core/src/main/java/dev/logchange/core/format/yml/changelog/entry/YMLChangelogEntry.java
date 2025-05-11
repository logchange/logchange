package dev.logchange.core.format.yml.changelog.entry;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.logchange.core.domain.changelog.model.entry.*;
import dev.logchange.core.format.yml.ObjectMapperProvider;
import lombok.*;
import org.apache.commons.lang3.tuple.Pair;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @JsonProperty(index = 2)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<YMLChangelogModule> modules;

    @Singular
    @JsonProperty(value = "merge_requests", index = 3)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<Long> mergeRequests;

    @Singular
    @JsonProperty(index = 4)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<Long> issues;

    @Singular
    @JsonProperty(index = 5)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<YMLChangelogEntryLink> links;

    @JsonProperty(index = 6)
    public YMLChangelogEntryType type;

    @Singular
    @JsonProperty(value = "important_notes", index = 7)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<String> importantNotes;

    @Singular
    @JsonProperty(index = 8)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<YMLChangelogEntryConfiguration> configurations;

    @JsonIgnore
    private Set<Pair<String, String>> invalidProperties = new HashSet<>();

    @JsonIgnore
    private String path;

    @SneakyThrows
    public static YMLChangelogEntry of(InputStream input, String path) {
        ObjectMapper mapper = ObjectMapperProvider.get();
        YMLChangelogEntry res;
        try {
            res = mapper.readValue(input, YMLChangelogEntry.class);
            res.setPath(path);
        } catch (Exception e) {
            String msg = (e.getCause() != null) ? e.getCause().getMessage() : e.getMessage();
            throw new YMLChangelogInvalidConfigValuesException(path, Collections.singleton(msg));
        }

        if (!res.invalidProperties.isEmpty()) {
            throw new YMLChangelogEntryConfigException(path, res.invalidProperties);
        }

        return res;
    }

    public static YMLChangelogEntry of(ChangelogEntry entry) {
        return YMLChangelogEntry.builder()
                .title(entry.getTitle().getValue())
                .authors(entry.getAuthors().stream().map(YMLChangelogEntryAuthor::of).collect(Collectors.toList()))
                .mergeRequests(entry.getMergeRequests().stream().map(ChangelogEntryMergeRequest::getValue).collect(Collectors.toList()))
                .issues(entry.getIssues())
                .links(entry.getLinks().stream().map(YMLChangelogEntryLink::of).collect(Collectors.toList()))
                .type(YMLChangelogEntryType.of(entry.getType()))
                .importantNotes(entry.getImportantNotes().stream().map(ChangelogEntryImportantNote::getValue).collect(Collectors.toList()))
                .configurations(entry.getConfigurations().stream().map(YMLChangelogEntryConfiguration::of).collect(Collectors.toList()))
                .modules(entry.getModules().stream().map(YMLChangelogModule::of).collect(Collectors.toList()))
                .build();
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
        ChangelogEntryTitle changelogEntryTitle = title();
        ChangelogEntryType changelogEntryType = type();
        if (!invalidProperties.isEmpty()) {
            List<String> errors = invalidProperties.stream().map(Pair::getRight).collect(Collectors.toList());
            throw new YMLChangelogInvalidConfigValuesException(path, errors);
        }
        return ChangelogEntry.builder()
                .title(changelogEntryTitle)
                .type(changelogEntryType)
                .mergeRequests(mergeRequests())
                .issues(issues())
                .links(links())
                .authors(authors())
                .importantNotes(importantNotes())
                .configurations(changelogEntryConfiguration())
                .modules(modules())
                .build();
    }

    private ChangelogEntryTitle title() {
        try {
            return ChangelogEntryTitle.of(title);
        } catch (IllegalArgumentException e) {
            invalidProperties.add(Pair.of("title", e.getMessage()));
            return null;
        }
    }

    private ChangelogEntryType type() {
        if (type == null) {
            invalidProperties.add(Pair.of("type", "Missing type property!"));
            return null;
        }
        try {
            return type.to();
        } catch (IllegalArgumentException e) {
            invalidProperties.add(Pair.of("type", e.getMessage()));
            return null;
        }
    }

    private List<ChangelogModule>  modules() {
        if(modules == null) {
            return Collections.emptyList();
        }
        return modules.stream().map(YMLChangelogModule::to).collect(Collectors.toList());
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

    private List<ChangelogEntryImportantNote> importantNotes() {
        if (importantNotes == null) {
            return Collections.emptyList();
        } else {
            return importantNotes.stream()
                    .map(ChangelogEntryImportantNote::of)
                    .collect(Collectors.toList());
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
