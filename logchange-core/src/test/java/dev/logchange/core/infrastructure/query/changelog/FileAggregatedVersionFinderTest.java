package dev.logchange.core.infrastructure.query.changelog;

import dev.logchange.core.application.changelog.repository.AggregatedVersionQuery;
import dev.logchange.core.application.file.query.FileQuery;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.changelog.model.version.Version;
import dev.logchange.core.infrastructure.query.file.FileReader;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FileAggregatedVersionFinderTest {

    private static final String PATH = "src/test/resources/infrastructure/query/changelog/FileAggregatedVersionFinderTest/changelog";

    @Test
    void shouldFindAggregatedVersion() {
        // given:
        OffsetDateTime expectedDateTime = OffsetDateTime.of(2021, 1, 1, 12, 0, 0, 0, OffsetDateTime.now().getOffset());
        Version version = Version.of("1.0.0");
        FileQuery fileQuery = new FileReader();
        AggregatedVersionQuery avq = new FileAggregatedVersionFinder(version, fileQuery);

        // when:
        Optional<ChangelogVersion> result = avq.find(Paths.get(PATH), "PROJECT_NAME");

        // then:
        assertTrue(result.isPresent());
        ChangelogVersion changelogVersion = result.get();
        assertEquals(version, changelogVersion.getVersion());
        assertEquals(expectedDateTime, changelogVersion.getReleaseDateTime());
        assertEquals(2, changelogVersion.getEntries().size());
    }

    @Test
    void shouldNotFindAggregatedVersion_whenDirectoryNotExists() {
        // given:
        Version version = Version.of("10.0.0");
        FileQuery fileQuery = new FileReader();
        AggregatedVersionQuery avq = new FileAggregatedVersionFinder(version, fileQuery);

        // when:
        Optional<ChangelogVersion> result = avq.find(Paths.get(PATH), "PROJECT_NAME");

        // then:
        assertFalse(result.isPresent());
    }

    @Test
    void shouldFindAggregatedVersionWithoutEntries_whenDirectoryIsEmpty() {
        // given:
        OffsetDateTime expectedDateTime = OffsetDateTime.of(2021, 1, 2, 12, 0, 0, 0, OffsetDateTime.now().getOffset());
        Version version = Version.of("2.0.0");
        FileQuery fileQuery = new FileReader();
        AggregatedVersionQuery avq = new FileAggregatedVersionFinder(version, fileQuery);

        // when:
        Optional<ChangelogVersion> result = avq.find(Paths.get(PATH), "PROJECT_NAME");

        // then:
        assertTrue(result.isPresent());
        ChangelogVersion changelogVersion = result.get();
        assertEquals(version, changelogVersion.getVersion());
        assertEquals(expectedDateTime, changelogVersion.getReleaseDateTime());
        assertEquals(0, changelogVersion.getEntries().size());
    }
}
