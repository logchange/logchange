package dev.logchange.md.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkdownListsTest {

    private static Stream<Arguments> provideObjectsForUnorderedListItemCheck() {
        return Stream.of(
                Arguments.of(null, "- null"),
                Arguments.of(true, "- true"),
                Arguments.of("", "- "),
                Arguments.of("string", "- string"),
                Arguments.of('c', "- c"),
                Arguments.of(1, "- 1"),
                Arguments.of(9223372036854775807L, "- 9223372036854775807"),
                Arguments.of(3f, "- 3.0"),
                Arguments.of(4.56, "- 4.56"),
                Arguments.of(Arrays.asList("orange", "banana", "apple"), "- [orange, banana, apple]")
        );
    }

    @ParameterizedTest
    @MethodSource("provideObjectsForUnorderedListItemCheck")
    public void shouldParseAnyObjectAsUnorderedListItem(Object input, String expectedOutput) {
        // given
        assertEquals(expectedOutput, MarkdownLists.unorderedListItem(input));
    }

    @Test
    public void shouldParseListOfDifferentTypesAsUnorderedList() {
        // given
        List<Object> input = Arrays.asList("string", 'c', 1, 9223372036854775807L, 3f, 4.56, true, null, Arrays.asList("orange", "banana", "apple"));
        String lineSeparator = System.lineSeparator();
        String expectedOutput =
                "- string" + lineSeparator +
                        "- c" + lineSeparator +
                        "- 1" + lineSeparator +
                        "- 9223372036854775807" + lineSeparator +
                        "- 3.0" + lineSeparator +
                        "- 4.56" + lineSeparator +
                        "- true" + lineSeparator +
                        "- null" + lineSeparator +
                        "- [orange, banana, apple]";

        // when
        String result = MarkdownLists.unorderedList(input);

        // then
        assertEquals(expectedOutput, result);
    }

    @Test
    public void shouldParseNestedListOfDifferentTypesAsUnorderedNestedList() {
        // given
        List<Object> input = Arrays.asList("string", 'c', 1, 9223372036854775807L, 3f, 4.56, true, null, Arrays.asList("fruits", Arrays.asList("orange", "banana", "apple")));
        String lineSeparator = System.lineSeparator();
        String expectedOutput =
                "- string" + lineSeparator +
                        "- c" + lineSeparator +
                        "- 1" + lineSeparator +
                        "- 9223372036854775807" + lineSeparator +
                        "- 3.0" + lineSeparator +
                        "- 4.56" + lineSeparator +
                        "- true" + lineSeparator +
                        "- null" + lineSeparator +
                        "  - fruits" + lineSeparator +
                        "    - orange" + lineSeparator +
                        "    - banana" + lineSeparator +
                        "    - apple";

        // when
        String result = MarkdownLists.unorderedNestedList(input);

        // then
        assertEquals(expectedOutput, result);
    }

}