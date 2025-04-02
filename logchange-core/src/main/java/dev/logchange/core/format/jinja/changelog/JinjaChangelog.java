package dev.logchange.core.format.jinja.changelog;

import com.google.common.collect.Maps;
import com.hubspot.jinjava.Jinjava;
import dev.logchange.core.application.config.TemplateFile;
import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.format.jinja.JinJavaProvider;
import lombok.SneakyThrows;

import java.util.Map;

public class JinjaChangelog {

    private final TemplateFile templateFile;
    private final Changelog changelog;

    public JinjaChangelog(TemplateFile templateFile, Changelog changelog) {
        this.templateFile = templateFile;
        this.changelog = changelog;
    }

    @SneakyThrows
    public String render() {
        Jinjava jinjava = JinJavaProvider.get();

        Map<String, Object> context = Maps.newHashMap();
        context.put("changelog", changelog);

        return jinjava.render(templateFile.getContent(), context);
    }
}
