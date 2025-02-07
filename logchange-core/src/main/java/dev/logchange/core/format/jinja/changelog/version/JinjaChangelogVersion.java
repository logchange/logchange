package dev.logchange.core.format.jinja.changelog.version;

import com.google.common.collect.Maps;
import com.hubspot.jinjava.Jinjava;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.config.model.templates.VersionSummaryTemplate;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static dev.logchange.core.Constants.TEMPLATES_DIR_NAME;

public class JinjaChangelogVersion {

    private final File inputDirectory;
    private final VersionSummaryTemplate versionSummaryTemplate;
    private final ChangelogVersion version;

    public JinjaChangelogVersion(File inputDirectory, VersionSummaryTemplate versionSummaryTemplate, ChangelogVersion version) {
        this.inputDirectory = inputDirectory;
        this.versionSummaryTemplate = versionSummaryTemplate;
        this.version = version;
    }

    @SneakyThrows
    public String render() {
        Jinjava jinjava = new Jinjava();
        Map<String, Object> context = Maps.newHashMap();
        context.put("version", version);

        String template = getTemplate();

        return jinjava.render(template, context);
    }

    @SneakyThrows
    private String getTemplate() { //TODO it should be moved to TemplateRepository
        return IOUtils.toString(new FileInputStream(getTemplatePath()), StandardCharsets.UTF_8);
    }

    private String getTemplatePath() {
        return inputDirectory.getPath() + "/" + TEMPLATES_DIR_NAME + "/" + versionSummaryTemplate.getPath();
    }
}
