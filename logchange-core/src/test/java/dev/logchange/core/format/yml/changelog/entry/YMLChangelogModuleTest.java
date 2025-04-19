package dev.logchange.core.format.yml.changelog.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YMLChangelogModulesTest {

    @Test
    void yamlChangelogModulesCreation() {
        // No nulls
        assertThrows(IllegalArgumentException.class, () ->
                new YMLChangelogModule(null)
        );
        // No empty modules
        assertThrows(IllegalArgumentException.class, () ->
                new YMLChangelogModule("")
        );
    }

}
