package dev.logchange.core.format.release_date;

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

public class ReleaseDate {

    public static final String RELEASE_DATE_FILENAME = "release-date.txt";

    public static final String RELEASE_DATE_FORMAT = "yyyy-MM-dd";

    public static OffsetDateTime getFromDir(File versionDirectory) {
        File[] files = versionDirectory.listFiles();

        if (files == null) {
            return null;
        }

        return Arrays.stream(files)
                .filter(file -> file.getName().equals(ReleaseDate.RELEASE_DATE_FILENAME))
                .map(ReleaseDate::getFromFile)
                .findFirst()
                .orElse(null);
    }

    @SneakyThrows
    public static void addToDir(Path unreleasedDir) {
        File releaseDateFile = new File(unreleasedDir + "/" + RELEASE_DATE_FILENAME);

        FileWriter fileWriter = new FileWriter(releaseDateFile);
        fileWriter.write(LocalDate.now().toString());
        fileWriter.close();
    }

    @SneakyThrows
    private static OffsetDateTime getFromFile(File file) {
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        if (lines.size() == 0) {
            return null;
        }

        return OffsetDateTime.of(
                LocalDate.parse(lines.get(0), DateTimeFormatter.ofPattern(RELEASE_DATE_FORMAT)),
                LocalTime.NOON,
                ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
    }
}