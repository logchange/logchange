package dev.logchange.core.format.release_date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static dev.logchange.core.format.release_date.FileReleaseDateTime.RELEASE_DATE_FORMAT;
import static org.junit.jupiter.api.Assertions.*;

class ReleaseDateOptionTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(RELEASE_DATE_FORMAT);

    @Test
    void given_Blank_when_Of_then_ReturnsToday() {
        // given
        String expected = LocalDate.now().format(FORMATTER);

        // when
        ReleaseDateOption option = ReleaseDateOption.of(" ");

        // then
        assertNotNull(option);
        assertFalse(option.isNone());
        assertEquals(expected, option.getValue());
    }

    @Test
    void given_Null_when_Of_then_ReturnsToday() {
        // given
        String expected = LocalDate.now().format(FORMATTER);

        // when
        ReleaseDateOption option = ReleaseDateOption.of(null);

        // then
        assertNotNull(option);
        assertFalse(option.isNone());
        assertEquals(expected, option.getValue());
    }

    @Test
    void given_TodayString_when_Of_then_ReturnsToday() {
        // given
        String expected = LocalDate.now().format(FORMATTER);

        // when
        ReleaseDateOption option = ReleaseDateOption.of("today");

        // then
        assertNotNull(option);
        assertFalse(option.isNone());
        assertEquals(expected, option.getValue());
    }

    @Test
    void given_NoneString_when_Of_then_IsNone() {
        // when
        ReleaseDateOption option = ReleaseDateOption.of("none");

        // then
        assertTrue(option.isNone());
        assertEquals("none", option.getValue());
    }

    @Test
    void given_ValidDateString_when_Of_then_ReturnsSameValue() {
        // given
        String date = "2021-02-03";

        // when
        ReleaseDateOption option = ReleaseDateOption.of(date);

        // then
        assertNotNull(option);
        assertFalse(option.isNone());
        assertEquals(date, option.getValue());
    }

    @Test
    void given_InvalidDateString_when_Of_then_ThrowsIllegalArgumentException() {
        // given
        String invalid = "2021/02/03";

        // when / then
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ReleaseDateOption.of(invalid));
        assertTrue(ex.getMessage().contains("Invalid release date"));
    }
}
