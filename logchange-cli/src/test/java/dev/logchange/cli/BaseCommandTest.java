package dev.logchange.cli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BaseCommandTest {

    private PrintStream originalOut;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void given_command_when_run_then_logsVersionInfoAndRunsCommand() {
        // given
        TestCommand command = new TestCommand(null);

        // when
        command.run();

        // then
        assertThat(command.executed).isTrue();
        assertThat(outContent.toString()).contains(LogchangeVersionProvider.getVersionInfo());
    }

    @Test
    void given_failingCommand_when_run_then_wrapsExceptionInRuntimeException() {
        // given
        TestCommand command = new TestCommand(new IllegalStateException("boom"));

        // when & then
        assertThatThrownBy(command::run)
                .isInstanceOf(RuntimeException.class)
                .hasRootCauseMessage("boom");
    }

    private static class TestCommand extends BaseCommand {

        private final RuntimeException toThrow;
        private boolean executed = false;

        private TestCommand(RuntimeException toThrow) {
            this.toThrow = toThrow;
        }

        @Override
        public void runCommand() {
            executed = true;
            if (toThrow != null) {
                throw toThrow;
            }
        }
    }
}
