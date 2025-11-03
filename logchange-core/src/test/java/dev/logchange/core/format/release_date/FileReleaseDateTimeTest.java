package dev.logchange.core.format.release_date;

import dev.logchange.core.domain.changelog.model.version.ReleaseDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.*;
import java.time.format.DateTimeFormatter;

import static dev.logchange.core.format.release_date.FileReleaseDateTime.RELEASE_DATE_FILENAME;
import static dev.logchange.core.format.release_date.FileReleaseDateTime.RELEASE_DATE_FORMAT;
import static org.junit.jupiter.api.Assertions.*;

class FileReleaseDateTimeTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(RELEASE_DATE_FORMAT);

    @Test
    void given_DirectoryWithoutFile_when_GetFromDir_then_ReturnsNull(@TempDir Path tempDir) {
        // when
        ReleaseDateTime result = FileReleaseDateTime.getFromDir(tempDir.toFile());

        // then
        assertNull(result);
    }

    @Test
    void given_NoneOption_when_AddToDir_then_DoesNotCreateFile(@TempDir Path tempDir) {
        // given
        ReleaseDateOption none = ReleaseDateOption.of("none");

        // when
        FileReleaseDateTime.addToDir(tempDir, none);

        // then
        assertFalse(Files.exists(tempDir.resolve(RELEASE_DATE_FILENAME)));
    }

    @Test
    void given_TodayOption_when_AddToDir_then_CreatesFileWithTodayAndGetFromDirParsesIt(@TempDir Path tempDir) throws IOException {
        // given
        ReleaseDateOption today = ReleaseDateOption.of("today");
        String expectedDateText = LocalDate.now().format(FORMATTER);

        // when
        FileReleaseDateTime.addToDir(tempDir, today);

        // then: file exists with today's date
        Path filePath = tempDir.resolve(RELEASE_DATE_FILENAME);
        assertTrue(Files.exists(filePath));
        String content = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
        assertEquals(expectedDateText, content);

        // and getFromDir returns date time at NOON with current system offset
        ReleaseDateTime rdt = FileReleaseDateTime.getFromDir(tempDir.toFile());
        assertNotNull(rdt);
        OffsetDateTime expected = OffsetDateTime.of(LocalDate.now(), LocalTime.NOON, ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
        assertEquals(expected, rdt.getValue());
    }

    @Test
    void given_FileWithSpecificDate_when_GetFromDir_then_ReturnsExpectedOffsetDateTime(@TempDir Path tempDir) throws IOException {
        // given
        String date = "2021-01-02";
        Files.write(tempDir.resolve(RELEASE_DATE_FILENAME), date.getBytes(StandardCharsets.UTF_8));

        // when
        ReleaseDateTime rdt = FileReleaseDateTime.getFromDir(tempDir.toFile());

        // then
        assertNotNull(rdt);
        OffsetDateTime expected = OffsetDateTime.of(LocalDate.parse(date, FORMATTER), LocalTime.NOON,
                ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
        assertEquals(expected, rdt.getValue());
    }

    @Test
    void given_EmptyFile_when_GetFromDir_then_ThrowsIllegalArgumentException(@TempDir Path tempDir) throws IOException {
        // given
        Path filePath = tempDir.resolve(RELEASE_DATE_FILENAME);
        // create truly empty file
        Files.write(filePath, new byte[0]);

        // when / then
        assertThrows(IllegalArgumentException.class, () -> FileReleaseDateTime.getFromDir(tempDir.toFile()));
    }
}
