package dev.logchange.core.format.yml.config;

import de.beosign.snakeyamlanno.constructor.AnnotationAwareConstructor;
import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.domain.config.model.labels.Labels;
import dev.logchange.core.format.yml.YamlProvider;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YMLConfig {

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
        return YamlProvider.get().dumpAsMap(this);
    }

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        System.out.println("Unknown property: " + key + " with value " + value);
        //TODO Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    public Config to() {
        return Config.builder()
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
}
