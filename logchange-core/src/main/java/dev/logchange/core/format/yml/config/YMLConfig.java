package dev.logchange.core.format.yml.config;

import de.beosign.snakeyamlanno.constructor.AnnotationAwareConstructor;
import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.domain.config.model.Heading;
import dev.logchange.core.domain.config.model.labels.Labels;
import dev.logchange.core.format.yml.YamlProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLConfig {

    private static final String YML_HEADING = "# This file configures logchange tool \uD83C\uDF33 \uD83E\uDE93 => \uD83E\uDEB5 \n" +
            "# Visit https://github.com/logchange/logchange and leave a star \uD83C\uDF1F \n" +
            "# More info about configuration you can find https://github.com/logchange/logchange#configuration \n";

    @YamlProperty(key = "changelog", order = 0)
    public YMLChangelog changelog;

    public static YMLConfig of(InputStream input) {
        Yaml yaml = new Yaml(new AnnotationAwareConstructor(YMLConfig.class));
        return yaml.load(input);
    }

    public static YMLConfig of(Config config) {
        return YMLConfig.builder()
                .changelog(YMLChangelog.of(config))
                .build();
    }

    public String toYMLString() {
        return YML_HEADING + YamlProvider.get().dumpAsMap(this);
    }

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        System.out.println("Unknown property: " + key + " with value " + value);
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public Config to() {
        return Config.builder()
                .heading(getHeading())
                .labels(getLabels())
                .build();
    }

    private Labels getLabels() {
        if (changelog == null) {
            return Labels.EMPTY;
        } else {
            return changelog.getLabels();
        }
    }


    private Heading getHeading() {
        if (changelog == null) {
            return Heading.EMPTY;
        } else {
            return changelog.getHeading();
        }
    }
}
