package dev.logchange.core.format.yml;

import de.beosign.snakeyamlanno.representer.AnnotationAwareRepresenter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class YamlProvider {

    public static Yaml get(){
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setPrettyFlow(true);
        dumperOptions.setIndent(4);
        dumperOptions.setIndicatorIndent(2);

        return new Yaml(new AnnotationAwareRepresenter(), dumperOptions);
    }
}
