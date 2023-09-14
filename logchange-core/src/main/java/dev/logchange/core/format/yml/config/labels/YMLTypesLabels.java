package dev.logchange.core.format.yml.config.labels;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import dev.logchange.core.domain.config.model.labels.TypesLabels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLTypesLabels {

    public static final YMLTypesLabels EMPTY = YMLTypesLabels.builder().build();

    @JsonProperty(index = 1)
    public String added;

    @JsonProperty(index = 2)
    public String changed;

    @JsonProperty(index = 3)
    public String deprecated;

    @JsonProperty(index = 4)
    public String removed;

    @JsonProperty(index = 5)
    public String fixed;

    @JsonProperty(index = 6)
    public String security;

    @JsonProperty(value = "dependency_update", index = 7)
    public String dependencyUpdate;

    @JsonProperty(index = 8)
    public String other;

    @JsonProperty(value = "number_of_changes", index = 9)
    public YMLNumberOfChangesLabels numberOfChanges;

    public static YMLTypesLabels of(TypesLabels types) {
        return YMLTypesLabels.builder()
                .added(types.getType(ChangelogEntryType.ADDED))
                .changed(types.getType(ChangelogEntryType.CHANGED))
                .deprecated(types.getType(ChangelogEntryType.DEPRECATED))
                .removed(types.getType(ChangelogEntryType.REMOVED))
                .fixed(types.getType(ChangelogEntryType.FIXED))
                .security(types.getType(ChangelogEntryType.SECURITY))
                .dependencyUpdate(types.getType(ChangelogEntryType.DEPENDENCY_UPDATE))
                .other(types.getType(ChangelogEntryType.SECURITY))
                .numberOfChanges(YMLNumberOfChangesLabels.of(types.getNumberOfChanges()))
                .build();
    }

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        System.out.println("Unknown property: " + key + " with value " + value);
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public TypesLabels to() {
        return TypesLabels.builder()
                .added(added)
                .changed(changed)
                .deprecated(deprecated)
                .removed(removed)
                .fixed(fixed)
                .security(security)
                .dependencyUpdate(dependencyUpdate)
                .other(other)
                .numberOfChanges(getNumberOfChanges().to())
                .build();
    }

    public YMLNumberOfChangesLabels getNumberOfChanges() {
        if (numberOfChanges != null) {
            return numberOfChanges;
        } else {
            return YMLNumberOfChangesLabels.EMPTY;
        }
    }
}
