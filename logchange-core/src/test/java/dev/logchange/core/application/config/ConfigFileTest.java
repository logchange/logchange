package dev.logchange.core.application.config;

import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.config.FileConfigRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ConfigFileTest {

    private static final String PATH = "src/test/resources/ConfigFileTest/";
    private static final String CONFIG_FILE_NAME = "logchange-config.yml";


    @AfterEach
    void cleanUp() {
        new File(PATH + CONFIG_FILE_NAME).delete();
    }

    @Test
    void shouldCreateConfigFile() {
        // given:
        assertFalse(new File(PATH + CONFIG_FILE_NAME).exists());

        // when:
        ConfigFile.of(PATH).create(CONFIG_FILE_NAME);

        // then:
        assertTrue(new File(PATH + CONFIG_FILE_NAME).exists());
    }

    @Test
    void shouldTryCreateConfigFileAndThrowException() throws IOException {
        // given:
        assertFalse(new File(PATH + CONFIG_FILE_NAME).exists());
        new File(PATH + CONFIG_FILE_NAME).createNewFile();

        // when:
        Exception exception = assertThrows(RuntimeException.class, () -> ConfigFile.of(PATH).create(CONFIG_FILE_NAME));

        // then:
        assertEquals("logchange-config.yml already exists.", exception.getMessage());

    }

    @Test
    void shouldFindConfigFile() {
        // given:
        assertFalse(new File(PATH + CONFIG_FILE_NAME).exists());
        File file = ConfigFile.of(PATH).create(CONFIG_FILE_NAME);
        ConfigRepository configRepository = new FileConfigRepository(file);
        configRepository.save(Config.EMPTY);


        // when:
        Optional<Config> config = ConfigFile.find(PATH + CONFIG_FILE_NAME);

        // then:
        assertTrue(config.isPresent());

    }

    @Test
    void shouldNotFindConfigFile() {
        // given:
        assertFalse(new File(PATH + CONFIG_FILE_NAME).exists());

        // when:
        Optional<Config> config = ConfigFile.find(PATH + CONFIG_FILE_NAME);

        // then:
        assertFalse(config.isPresent());
    }

    @Test
    void shouldFindConfigFileAsDirectoryAndThrowException() {
        // given:
        assertFalse(new File(PATH + CONFIG_FILE_NAME).exists());

        // when:
        Exception exception = assertThrows(RuntimeException.class, () -> ConfigFile.find(PATH));

        // then:
        assertEquals("File " + PATH + " is a directory !!!", exception.getMessage());
    }
}