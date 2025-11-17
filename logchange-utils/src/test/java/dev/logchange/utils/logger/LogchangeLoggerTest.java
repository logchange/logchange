package dev.logchange.utils.logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class LogchangeLoggerTest {

    private final LogchangeLogger logger = LogchangeLogger.getLogger(LogchangeLoggerTest.class);

    private PrintStream originalOut;
    private PrintStream originalErr;
    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    @BeforeEach
    void setUp() {
        // Capture std streams
        originalOut = System.out;
        originalErr = System.err;
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        // Ensure a known starting level. Calling setLevel prints an info line,
        // so set it and then clear buffers to keep tests focused on their assertions.
        LogchangeLogger.setLevel(LoggerLevel.INFO);
        clearBuffers();
    }

    @AfterEach
    void tearDown() {
        // Restore streams first to avoid polluting captured buffers in other tests
        System.setOut(originalOut);
        System.setErr(originalErr);

        // Reset logger level to default for isolation
        LogchangeLogger.setLevel(LoggerLevel.INFO);
    }

    @Test
    void given_defaultLevelInfo_when_info_then_printsMessageWithoutPrefixToStdout() {
        // when
        logger.info("Hello");

        // then
        assertThat(stdout()).isEqualTo("Hello" + System.lineSeparator());
        assertThat(stderr()).isEmpty();
    }

    @Test
    void given_defaultLevelInfo_when_warn_then_printsWarnWithPrefixToStdout() {
        // when
        logger.warn("Watch out");

        // then
        assertThat(stdout()).isEqualTo("[WARN] Watch out" + System.lineSeparator());
        assertThat(stderr()).isEmpty();
    }

    @Test
    void given_defaultLevelInfo_when_error_then_printsErrorWithPrefixToStderr() {
        // when
        logger.error("Boom");

        // then
        assertThat(stderr()).isEqualTo("[ERROR] Boom" + System.lineSeparator());
        assertThat(stdout()).isEmpty();
    }

    @Test
    void given_defaultLevelInfo_when_debug_then_printsNothing() {
        // when
        logger.debug("hidden");

        // then
        assertThat(stdout()).isEmpty();
        assertThat(stderr()).isEmpty();
    }

    @Test
    void given_debugLevel_when_debugString_then_printsWithDebugPrefixToStdout() {
        // given
        LogchangeLogger.setLevel(LoggerLevel.DEBUG);
        clearBuffers(); // ignore the informational line from setLevel

        // when
        logger.debug("details");

        // then
        assertThat(stdout()).isEqualTo("[DEBUG] details" + System.lineSeparator());
        assertThat(stderr()).isEmpty();
    }

    @Test
    void given_debugLevel_when_debugException_then_printsMessageAndStackTraceToStdout() {
        // given
        LogchangeLogger.setLevel(LoggerLevel.DEBUG);
        clearBuffers(); // ignore the informational line from setLevel

        // when
        Exception e = new IllegalStateException("state broken");
        logger.debug(e);

        // then
        String out = stdout();
        assertThat(out)
                .startsWith("[DEBUG] state broken" + System.lineSeparator())
                .contains("java.lang.IllegalStateException: state broken")
                .contains("at "); // contains stack trace frames
        assertThat(stderr()).isEmpty();
    }

    private String stdout() {
        return outContent.toString();
    }

    private String stderr() {
        return errContent.toString();
    }

    private void clearBuffers() {
        outContent.reset();
        errContent.reset();
    }
}
