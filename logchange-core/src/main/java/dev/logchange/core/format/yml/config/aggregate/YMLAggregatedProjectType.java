package dev.logchange.core.format.yml.config.aggregate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import dev.logchange.core.domain.config.model.aggregate.AggregatedProjectType;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.stream.Collectors;

@Log
@AllArgsConstructor
public enum YMLAggregatedProjectType {

    TAR_GZ("tar.gz"),
    ;

    private final String type;


    @JsonCreator
    public static YMLAggregatedProjectType of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.getType().equals(name))
                .findFirst()
                .orElseThrow(() -> {
                    String availableType = Arrays.stream(YMLAggregatedProjectType.values()).map(YMLAggregatedProjectType::getType).collect(Collectors.joining(", "));
                    String message = "Cannot match YMLAggregatedProjectType for string: " + name + " - Available types: [" + availableType + "].";
                    log.severe(message);
                    return new IllegalArgumentException(message);
                });

    }

    @JsonValue
    public String getType() {
        return type;
    }

    public AggregatedProjectType to() {
        switch (this) {
            case TAR_GZ:
                return AggregatedProjectType.TAR_GZ;
            default:
                throw new IllegalArgumentException();
        }
    }
}
