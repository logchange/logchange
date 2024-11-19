package dev.logchange.core.format.yml.config.aggregate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YMLAggregatedProjectTypeTest {

    @Test
    void shouldMatchEnumValue() {
        // when-then:
        assertEquals(YMLAggregatedProjectType.TAR_GZ, YMLAggregatedProjectType.of("tar.gz"));
    }

    @Test
    void shouldNotMatchEnumValue() {
        // when-then:
        assertEquals(YMLAggregatedProjectType.TAR_GZ, YMLAggregatedProjectType.of("tar.gz"));
        String message = assertThrows(IllegalArgumentException.class, () -> YMLAggregatedProjectType.of("some_type")).getMessage();
        assertEquals("Cannot match YMLAggregatedProjectType for string: some_type - Available types: [tar.gz].", message);
    }
}
