package dev.logchange.core.format.jinja.changelog.version;

import com.google.common.collect.Maps;
import com.hubspot.jinjava.Jinjava;
import dev.logchange.core.application.config.TemplateFile;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import lombok.SneakyThrows;

import java.util.Map;

public class JinjaChangelogVersion {

    private final TemplateFile templateFile;
    private final ChangelogVersion version;

    public JinjaChangelogVersion(TemplateFile templateFile, ChangelogVersion version) {
        this.templateFile = templateFile;
        this.version = version;
    }

    @SneakyThrows
    public String render() {
        Jinjava jinjava = new Jinjava();
        Map<String, Object> context = Maps.newHashMap();
        context.put("version", version);

        return jinjava.render(templateFile.getContent(), context);
    }
}
