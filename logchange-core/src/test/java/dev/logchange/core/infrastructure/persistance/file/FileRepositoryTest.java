package dev.logchange.core.infrastructure.persistance.file;

import dev.logchange.core.application.file.repository.FileWriter;
import dev.logchange.core.application.file.repository.XmlFileWriter;
import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryTitle;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryType;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.changelog.model.version.Version;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.changelog.MDChangelog;
import org.apache.maven.plugins.changes.model.ChangesDocument;
import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.Test;
import org.xmlunit.assertj3.XmlAssert;
import org.xmlunit.builder.Input;

import java.io.File;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static dev.logchange.core.application.changelog.service.generate.GenerateChangelogXMLService.mapChangelogToChangesDocument;
import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;

class FileRepositoryTest {

    private static final String PATH = "src/test/resources/infrastructure/persistance/file/";

    @Test
    void shouldWriteStringContentToMarkdownFile() throws IOException {
        // given:
        File outputFile = new File(PATH + "CHANGELOG.md");
        File expectedChangelogOutputFile = new File(PATH + "EXPECTED_CHANGELOG.md");

        FileWriter fileRepository = FileRepository.of(outputFile);
        String md = new MDChangelog(Config.EMPTY, prepareChangelog()).toMD();

        // when:
        fileRepository.write(md);

        // then:
        String expectedContent = FileUtils.fileRead(expectedChangelogOutputFile, "UTF-8");
        String actualContent = FileUtils.fileRead(outputFile, "UTF-8");
        assertThat(actualContent).isEqualToIgnoringNewLines(expectedContent);

        // cleanup:
        outputFile.delete();
    }

    @Test
    void shouldWriteXmlFile() {
        // given:
        File changesXmlOutputFile = new File(PATH + "CHANGELOG.md");
        File expectedChangesXmlOutputFile = new File(PATH + "EXPECTED_CHANGES.xml");

        ChangesDocument changesDocument = mapChangelogToChangesDocument(prepareChangelog());
        XmlFileWriter xmlFileWriter = FileRepository.of(changesXmlOutputFile);

        // when:
        xmlFileWriter.writeXml(changesDocument);

        // then:
        XmlAssert.assertThat(Input.fromFile(changesXmlOutputFile))
                .and(Input.fromFile(expectedChangesXmlOutputFile))
                .ignoreWhitespace()
                .areIdentical();

        // cleanup:
        changesXmlOutputFile.delete();
    }

    private Changelog prepareChangelog() {
        ChangelogEntry entry = ChangelogEntry.builder()
                .title(ChangelogEntryTitle.of("Some Title"))
                .type(ChangelogEntryType.fromNameIgnoreCase("added"))
                .build();

        List<ChangelogEntry> entries = new ArrayList<>();
        entries.add(entry);

        ChangelogVersion version = ChangelogVersion.builder()
                .version(Version.of("1.0.0"))
                .releaseDateTime(OffsetDateTime.of(2024, 10, 30, 0, 0, 0, 0, UTC))
                .entries(entries)
                .build();

        List<ChangelogVersion> versions = new ArrayList<>();
        versions.add(version);

        return Changelog.of(versions, Collections.emptyList());
    }
}
