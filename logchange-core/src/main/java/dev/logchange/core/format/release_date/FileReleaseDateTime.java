package dev.logchange.core.format.release_date;

import dev.logchange.core.domain.changelog.model.version.ReleaseDateTime;
import lombok.CustomLog;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@CustomLog
public class FileReleaseDateTime {

    public static final String RELEASE_DATE_FILENAME = "release-date.txt";

    public static final String RELEASE_DATE_FORMAT = "yyyy-MM-dd";

    public static ReleaseDateTime getFromDir(File versionDirectory) {
        File[] files = versionDirectory.listFiles();

        if (files == null) {
            return null;
        }

        return Arrays.stream(files)
                .filter(file -> file.getName().equals(FileReleaseDateTime.RELEASE_DATE_FILENAME))
                .map(e -> ReleaseDateTime.of(getFromFile(e)))
                .findFirst()
                .orElse(null);
    }

    public static void addToDir(Path unreleasedDir, ReleaseDateOption releaseDateOption) {
        if (releaseDateOption.isNone()) {
            log.info("Skipping creating " + RELEASE_DATE_FILENAME + " because none option selected during release");
            return;
        }

        File releaseDateFile = unreleasedDir.resolve(RELEASE_DATE_FILENAME).toFile();
        try (FileWriter fileWriter = new FileWriter(releaseDateFile)) {
            fileWriter.write(releaseDateOption.getValue());
        } catch (Exception e) {
            String msg = "Could not create and write to " + RELEASE_DATE_FILENAME + " because: " + e.getMessage();
            log.error(msg);
            throw new IllegalStateException(msg);
        }
    }

    @SneakyThrows
    private static OffsetDateTime getFromFile(File file) {
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        if (lines.isEmpty()) {
            return null;
        }

        return OffsetDateTime.of(
                LocalDate.parse(lines.get(0), DateTimeFormatter.ofPattern(RELEASE_DATE_FORMAT)),
                LocalTime.NOON,
                ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
    }
}