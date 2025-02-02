package dev.logchange.core.infrastructure.persistance.changelog;

import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.domain.config.model.templates.VersionSummaryTemplate;
import dev.logchange.core.format.jinja.changelog.version.JinjaChangelogVersion;
import dev.logchange.core.format.md.MDMeta;
import dev.logchange.core.format.md.changelog.version.MDChangelogVersion;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.io.File;
import java.util.List;

@Log
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
            String rendered = new JinjaChangelogVersion(inputDirectory, template, version).render();
            saveToFile(version, rendered, "version-summary.html"); //TODO name from template
        }
    }

    private void saveToFile(ChangelogVersion version, String content, String fileName) {
        String outputFilePath = inputDirectory.getPath() + "/" + version.getVersion().getDirName() + "/" + fileName;
        File outputFile = new File(outputFilePath);

        FileRepository fileRepository = FileRepository.of(outputFile);
        fileRepository.write(content);
    }
}
