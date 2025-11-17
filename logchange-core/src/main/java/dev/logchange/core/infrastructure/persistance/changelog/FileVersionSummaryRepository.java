package dev.logchange.core.infrastructure.persistance.changelog;

import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.application.config.TemplateFile;
import dev.logchange.core.application.config.TemplateRepository;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.domain.config.model.templates.VersionSummaryTemplate;
import dev.logchange.core.format.jinja.changelog.version.JinjaChangelogVersion;
import dev.logchange.core.format.md.MDMeta;
import dev.logchange.core.format.md.changelog.version.MDChangelogVersion;
import dev.logchange.core.infrastructure.persistance.config.FileTemplateRepository;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import lombok.AllArgsConstructor;
import lombok.CustomLog;

import java.io.File;
import java.util.List;

@CustomLog
@AllArgsConstructor
public class FileVersionSummaryRepository implements VersionSummaryRepository {

    private final File inputDirectory;
    private final Config config;

    @Override
    public void save(ChangelogVersion version) {
        saveMD(version);
        saveJinja(version);
    }

    private void saveMD(ChangelogVersion version) {
        String meta = new MDMeta().toMD();
        String md = new MDChangelogVersion(config, version).toMD();
        saveToFile(version, meta + md, "version-summary.md");
    }

    private void saveJinja(ChangelogVersion version) {
        List<VersionSummaryTemplate> templates = config.getTemplates().getVersionSummaryTemplates();
        for (VersionSummaryTemplate template : templates) {
            log.info("Generating from version-summary template: " + template);
            File templatePath = TemplateFile.getTemplatePath(inputDirectory, template.getPath());
            TemplateRepository templateRepository = new FileTemplateRepository(templatePath);
            TemplateFile templateFile = templateRepository.find();
            String rendered = new JinjaChangelogVersion(templateFile, version).render();
            saveToFile(version, rendered, template.getOutputFileName());
        }
    }

    private void saveToFile(ChangelogVersion version, String content, String fileName) {
        String outputFilePath = inputDirectory.getPath() + "/" + version.getVersion().getDirName() + "/" + fileName;
        File outputFile = new File(outputFilePath);

        FileRepository fileRepository = FileRepository.of(outputFile);
        fileRepository.write(content);
        log.info("Saved version summary to file: " + outputFilePath);
    }
}
