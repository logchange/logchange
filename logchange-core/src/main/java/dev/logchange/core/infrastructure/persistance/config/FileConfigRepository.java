package dev.logchange.core.infrastructure.persistance.config;

import dev.logchange.core.application.config.ConfigRepository;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.yml.config.YMLConfig;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.CustomLog;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@CustomLog
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FileConfigRepository implements ConfigRepository {

    private final File configFile;

    public static FileConfigRepository of(File configFile) {
        return new FileConfigRepository(configFile);
    }

    @Override
    public Config find() {
        return YMLConfig.of(getConfigInputStream(configFile)).to();
    }

    @Override
    public void save(Config config) {
        String content = YMLConfig.of(config).toYMLString();

        try (OutputStream os = Files.newOutputStream(configFile.toPath());
             PrintWriter out = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8))) {

            out.println(content);

        } catch (IOException e) {
            String message = "Could not save config to file: " + configFile + " because: " + e.getMessage();
            log.error(message);
            throw new IllegalArgumentException(message);
        }
    }

    private InputStream getConfigInputStream(File entryFile) {
        try {
            return new FileInputStream(entryFile);
        } catch (FileNotFoundException e) {
            String message = "Cannot find entry file: " + entryFile.getName();
            log.error(message);
            throw new IllegalArgumentException(message);
        }
    }
}
