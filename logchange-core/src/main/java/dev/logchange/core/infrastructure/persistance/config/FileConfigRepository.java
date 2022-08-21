package dev.logchange.core.infrastructure.persistance.config;

import dev.logchange.core.application.config.ConfigRepository;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.yml.config.YMLConfig;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@AllArgsConstructor
public class FileConfigRepository implements ConfigRepository {

    private final File inputFile;

    @Override
    public Config find() {
        return YMLConfig.of(getConfigInputStream(inputFile)).to();
    }

    private InputStream getConfigInputStream(File entryFile) {
        try {
            return new FileInputStream(entryFile);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Cannot find entry file: " + entryFile.getName());
        }
    }
}
