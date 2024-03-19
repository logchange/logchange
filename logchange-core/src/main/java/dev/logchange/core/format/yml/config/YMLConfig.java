package dev.logchange.core.format.yml.config;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.domain.config.model.Heading;
import dev.logchange.core.domain.config.model.labels.Labels;
import dev.logchange.core.format.yml.ObjectMapperProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.InputStream;
import java.io.StringWriter;

@Log
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLConfig {

    private static final String YML_HEADING = "# This file configures logchange tool \uD83C\uDF33 \uD83E\uDE93 => \uD83E\uDEB5 \n" +
            "# Visit https://github.com/logchange/logchange and leave a star \uD83C\uDF1F \n" +
            "# More info about configuration you can find https://github.com/logchange/logchange#configuration \n";

    @JsonProperty(index = 0)
    public YMLChangelog changelog;

    @SneakyThrows
    public static YMLConfig of(InputStream input) {
//        Yaml yaml = new Yaml(new AnnotationAwareConstructor(YMLConfig.class));
//        return yaml.load(input);

        ObjectMapper mapper = ObjectMapperProvider.get();
        return mapper.readValue(input, YMLConfig.class);
    }

    public static YMLConfig of(Config config) {
        return YMLConfig.builder()
                .changelog(YMLChangelog.of(config))
                .build();
    }

    @SneakyThrows
    public String toYMLString() {
        StringWriter stringWriter = new StringWriter();
        ObjectMapperProvider.get()
                .createGenerator(stringWriter)
                .writeObject(this);

        return YML_HEADING + stringWriter;
    }

    @JsonAnySetter
    public void anySetter(String key, Object value) {
        log.warning("Unknown property: " + key + " with value " + value);
    }

    public Config to() {
        return Config.builder()
                .heading(toHeading())
                .labels(toLabels())
                .build();
    }

    private Labels toLabels() {
        if (changelog == null) {
            return Labels.EMPTY;
        } else {
            return changelog.toLabels();
        }
    }


    private Heading toHeading() {
        if (changelog == null) {
            return Heading.EMPTY;
        } else {
            return changelog.toHeading();
        }
    }
}
