package dev.logchange.cli;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static dev.logchange.commands.Constants.BASIC_FOOTER;
import static org.assertj.core.api.Assertions.assertThat;

class LogchangeVersionProviderTest {

    private static final String ERROR_MESSAGE = "Cloud not read version from version.txt!";

    @Test
    void given_versionFileOnClasspath_when_getVersionInfo_then_returnsTrimmedFileContent() {
        // when
        String versionInfo = LogchangeVersionProvider.getVersionInfo();

        // then
        assertThat(versionInfo)
                .startsWith("Logchange version: ")
                .contains("Build with Java: ")
                .contains("VirtualMachine: ")
                .doesNotEndWith("\n");
    }

    @Test
    void given_versionFileOnClasspath_when_getVersion_then_returnsVersionInfoWithFooter() {
        // when
        String[] version = new LogchangeVersionProvider().getVersion();

        // then
        assertThat(version).hasSize(1);
        assertThat(version[0]).isEqualTo(LogchangeVersionProvider.getVersionInfo() + BASIC_FOOTER);
    }

    @Test
    void given_multiLineContent_when_readVersion_then_returnsAllLines() {
        // given
        InputStream content = new ByteArrayInputStream("first\nsecond\n".getBytes(StandardCharsets.UTF_8));

        // when
        String versionInfo = LogchangeVersionProvider.readVersion(content);

        // then
        assertThat(versionInfo).isEqualTo("first\nsecond");
    }

    @Test
    void given_missingVersionFile_when_readVersion_then_returnsErrorMessage() {
        // when
        String versionInfo = LogchangeVersionProvider.readVersion(null);

        // then
        assertThat(versionInfo).isEqualTo(ERROR_MESSAGE);
    }

    @Test
    void given_unreadableVersionFile_when_readVersion_then_returnsErrorMessage() {
        // given
        InputStream failing = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("cannot read");
            }
        };

        // when
        String versionInfo = LogchangeVersionProvider.readVersion(failing);

        // then
        assertThat(versionInfo).isEqualTo(ERROR_MESSAGE);
    }
}
