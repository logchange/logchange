package dev.logchange.commands.add;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PromptMessageTest {

    @Test
    void shouldReturnMessageAsIs_WhenMessageEndsWithColonAndSpace() {
        // given:
        String input = "Enter your name: ";

        // when:
        AddEntryPrompter.PromptMessage promptMessage = new AddEntryPrompter.PromptMessage(input);

        // then:
        assertThat(promptMessage.getMessage()).isEqualTo("Enter your name: ");
    }

    @Test
    void shouldTrimAndAddColonSpace_WhenMessageEndsWithSpace() {
        // given:
        String input = "Enter your name ";

        // when:
        AddEntryPrompter.PromptMessage promptMessage = new AddEntryPrompter.PromptMessage(input);

        // then:
        assertThat(promptMessage.getMessage()).isEqualTo("Enter your name: ");
    }

    @Test
    void shouldTrimAndAddColonSpace_WhenMessageEndsWithMultipleSpaces() {
        // given:
        String input = "Enter your name   ";

        // when:
        AddEntryPrompter.PromptMessage promptMessage = new AddEntryPrompter.PromptMessage(input);

        // then:
        assertThat(promptMessage.getMessage()).isEqualTo("Enter your name: ");
    }

    @Test
    void shouldAddColonSpace_WhenMessageDoesNotEndWithSpace() {
        // given:
        String input = "Enter your name";

        // when:
        AddEntryPrompter.PromptMessage promptMessage = new AddEntryPrompter.PromptMessage(input);

        // then:
        assertThat(promptMessage.getMessage()).isEqualTo("Enter your name: ");
    }

    @Test
    void shouldAddColonSpace_WhenMessageEndsWithColon() {
        // given:
        String input = "Enter your name:";

        // when:
        AddEntryPrompter.PromptMessage promptMessage = new AddEntryPrompter.PromptMessage(input);

        // then:
        assertThat(promptMessage.getMessage()).isEqualTo("Enter your name:: ");
    }

    @Test
    void shouldHandleEmptyMessage() {
        // given:
        String input = "";

        // when:
        AddEntryPrompter.PromptMessage promptMessage = new AddEntryPrompter.PromptMessage(input);

        // then:
        assertThat(promptMessage.getMessage()).isEqualTo(": ");
    }

    @Test
    void shouldHandleNullMessage() {
        // given:
        String input = null;

        // when:
        AddEntryPrompter.PromptMessage promptMessage = new AddEntryPrompter.PromptMessage(input);

        // then:
        assertThat(promptMessage.getMessage()).isEqualTo(": ");
    }

    @Test
    void shouldHandleMessageWithOnlySpaces() {
        // given:
        String input = "   ";

        // when:
        AddEntryPrompter.PromptMessage promptMessage = new AddEntryPrompter.PromptMessage(input);

        // then:
        assertThat(promptMessage.getMessage()).isEqualTo(": ");
    }

    @Test
    void shouldHandleComplexMessage() {
        // given:
        String input = "Please enter the changelog entry title";

        // when:
        AddEntryPrompter.PromptMessage promptMessage = new AddEntryPrompter.PromptMessage(input);

        // then:
        assertThat(promptMessage.getMessage()).isEqualTo("Please enter the changelog entry title: ");
    }

    @Test
    void shouldHandleMessageEndingWithColonSpaceAndMoreSpaces() {
        // given:
        String input = "Enter your name:  ";

        // when:
        AddEntryPrompter.PromptMessage promptMessage = new AddEntryPrompter.PromptMessage(input);

        // then:
        assertThat(promptMessage.getMessage()).isEqualTo("Enter your name:: ");
    }

    @Test
    void shouldReturnInputWhenToString() {
        // given:
        String input = "Enter your name:  ";

        // when:
        AddEntryPrompter.PromptMessage promptMessage = new AddEntryPrompter.PromptMessage(input);

        // then:
        assertThat(promptMessage.toString()).isEqualTo("Enter your name:: ");
    }
}