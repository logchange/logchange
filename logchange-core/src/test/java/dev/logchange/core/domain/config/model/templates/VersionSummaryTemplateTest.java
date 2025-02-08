package dev.logchange.core.domain.config.model.templates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VersionSummaryTemplateTest {

    @Test
    void testGetOutputFileName(){
        // given:
        String outputFileName = "somedir/myfile.txt";

        // when:
        String result = VersionSummaryTemplate.of(outputFileName).getOutputFileName();

        // then:
        assertEquals("myfile.txt", result);
    }

}