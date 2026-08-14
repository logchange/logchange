package dev.logchange.core.format.yml.config;

import dev.logchange.core.domain.config.model.Config;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class YMLConfigTest {

    @Test
    void entryBannerIsEnabledWhenThereIsNoEntrySection() {
        // given:
        InputStream input = yml("changelog:\n  heading: Some heading\n");

        // when:
        Config config = YMLConfig.of(input).to();

        // then:
        assertTrue(config.isEntryBanner());
    }

    @Test
    void entryBannerIsEnabledWhenEntrySectionDoesNotDefineIt() {
        // given:
        InputStream input = yml("entry:\n  banner:\n");

        // when:
        Config config = YMLConfig.of(input).to();

        // then:
        assertTrue(config.isEntryBanner());
    }

    @Test
    void entryBannerIsEnabledWhenSetToTrue() {
        // given:
        InputStream input = yml("entry:\n  banner: true\n");

        // when:
        Config config = YMLConfig.of(input).to();

        // then:
        assertTrue(config.isEntryBanner());
    }

    @Test
    void entryBannerIsDisabledWhenSetToFalse() {
        // given:
        InputStream input = yml("entry:\n  banner: false\n");

        // when:
        Config config = YMLConfig.of(input).to();

        // then:
        assertFalse(config.isEntryBanner());
    }

    @Test
    void unknownPropertyInEntrySectionDoesNotBreakConfig() {
        // given:
        InputStream input = yml("entry:\n  bannner: false\n");

        // when:
        Config config = YMLConfig.of(input).to();

        // then:
        assertTrue(config.isEntryBanner());
    }

    private InputStream yml(String content) {
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }
}
