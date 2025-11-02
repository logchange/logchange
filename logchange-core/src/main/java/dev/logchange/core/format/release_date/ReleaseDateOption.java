package dev.logchange.core.format.release_date;

import lombok.CustomLog;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static dev.logchange.core.format.release_date.FileReleaseDateTime.RELEASE_DATE_FORMAT;

@CustomLog
public class ReleaseDateOption {

    private static final String TODAY_OPTION = "today";
    private static final String NONE_VALUE = "none";

    private static final ReleaseDateOption NONE = new ReleaseDateOption(NONE_VALUE);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RELEASE_DATE_FORMAT);
    private static final ReleaseDateOption TODAY = new ReleaseDateOption(LocalDateTime.now().format(formatter));
    private final String value;

    private ReleaseDateOption(String value) {
        this.value = value;
    }

    public static ReleaseDateOption of(String releaseDate) {
        if (StringUtils.isBlank(releaseDate)) {
            return TODAY;
        }

        if (releaseDate.equals(NONE_VALUE)) {
            return NONE;
        }

        if (releaseDate.equals(TODAY_OPTION)) {
            return TODAY;
        }

        try {
            formatter.parse(releaseDate);
        } catch (DateTimeParseException e) {
            String msg = "Invalid release date: " + releaseDate + " please provide date in format: " + RELEASE_DATE_FORMAT + " or don't set this parameter if you want to use today's date, or use: " + NONE_VALUE;
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        return new ReleaseDateOption(releaseDate);
    }

    public boolean isNone() {
        return value.equals(NONE_VALUE);
    }

    public String getValue() {
        return value;
    }
}
