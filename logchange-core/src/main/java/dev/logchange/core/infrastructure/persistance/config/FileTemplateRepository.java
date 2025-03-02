package dev.logchange.core.infrastructure.persistance.config;

import dev.logchange.core.application.config.TemplateFile;
import dev.logchange.core.application.config.TemplateRepository;
import lombok.CustomLog;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@CustomLog
public class FileTemplateRepository implements TemplateRepository {

    private final File file;

    public FileTemplateRepository(File file) {
        this.file = file;
    }

    @Override
    public TemplateFile find() {
        return TemplateFile.of(getContent());
    }


    private String getContent() {
        try {
            return IOUtils.toString(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            String msg = "Could not read file: " + file.getAbsolutePath() + " msg: " + e.getMessage();
            log.error(msg);
            throw new IllegalStateException(msg);
        }
    }
}
