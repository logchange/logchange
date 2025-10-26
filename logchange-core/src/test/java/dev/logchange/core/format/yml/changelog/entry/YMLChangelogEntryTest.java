package dev.logchange.core.format.yml.changelog.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YMLChangelogEntryTest {

    @Test
    void canCovertToYAMLString() {
        //given:
        YMLChangelogEntry entry = get();

        //when:
        String result = entry.toYMLString();

        //then:
        assertEquals("# This file is used by logchange tool to generate CHANGELOG.md \uD83C\uDF33 \uD83E\uDE93 => \uD83E\uDEB5 \n" +
                "# Visit https://github.com/logchange/logchange and leave a star \uD83C\uDF1F \n" +
                "# More info about configuration you can find https://github.com/logchange/logchange#yaml-format ⬅️⬅ ️\n" +
                "title: Some title\n" +
                "authors:\n" +
                "  - name: Ala\n" +
                "    nick: Ma\n" +
                "    url: http://ko.ta\n" +
                "merge_requests:\n" +
                "  - 1\n" +
                "issues:\n" +
                "  - 1\n" +
                "links:\n" +
                "  - name: marwin1991\n" +
                "    url: https://github.com/users/marwin1991\n" +
                "type: added\n" +
                "important_notes:\n" +
                "  - Some important note\n" +
                "configurations:\n" +
                "  - type: DB\n" +
                "    action: add\n" +
                "    key: aaa.bbb\n" +
                "    default_value: \"\"\n" +
                "    description: Some desc\n" +
                "    more_info: some more info\n", result);
    }

    @Test
    void canCovertToSimpleYAMLString() {
        //given:
        YMLChangelogEntry entry = getSimple();

        //when:
        String result = entry.toYMLString();

        //then:
        assertEquals("# This file is used by logchange tool to generate CHANGELOG.md \uD83C\uDF33 \uD83E\uDE93 => \uD83E\uDEB5 \n" +
                "# Visit https://github.com/logchange/logchange and leave a star \uD83C\uDF1F \n" +
                "# More info about configuration you can find https://github.com/logchange/logchange#yaml-format ⬅\uFE0F⬅ \uFE0F\n" +
                "title: Some title\n" +
                "authors:\n" +
                "  - nick: marwin1991\n" +
                "links:\n" +
                "  - url: https://github.com/users/marwin1991\n" +
                "type: added\n", result);
    }

    @Test
    void whenInvalidTypeStringThrowExceptionInBuilder() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                YMLChangelogEntry.builder()
                        .title("Some title")
                        .type(YMLChangelogEntryType.of("some_type"))
                        .build()
        );
        assertEquals("Cannot match YMLChangelogEntryType for string: some_type - Available types: [added, changed, deprecated, removed, fixed, security, dependency_update, other].", exception.getMessage());
    }

    @Test
    void whenEmptyTypeStringThrowExceptionInBuilder() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                YMLChangelogEntry.builder()
                        .title("Some title")
                        .type(YMLChangelogEntryType.of(""))
                        .build()
        );
        assertEquals("Cannot match YMLChangelogEntryType for string:  - Available types: [added, changed, deprecated, removed, fixed, security, dependency_update, other].", exception.getMessage());
    }

    @Test
    void whenNullTypeThrowExceptionInBuilder() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                YMLChangelogEntry.builder()
                        .title("Some title")
                        .type(YMLChangelogEntryType.of((String) null))
                        .build()
        );
        assertEquals("Cannot match YMLChangelogEntryType for string: null - Available types: [added, changed, deprecated, removed, fixed, security, dependency_update, other].", exception.getMessage());
    }

    private YMLChangelogEntry getSimple() {
        return YMLChangelogEntry.builder()
                .title("Some title")
                .author(new YMLChangelogEntryAuthor(null,"marwin1991",null))
                .link(new YMLChangelogEntryLink(null, "https://github.com/users/marwin1991"))
                .type(YMLChangelogEntryType.of("added"))
                .build();
    }

    private YMLChangelogEntry get() {
        return YMLChangelogEntry.builder()
                .title("Some title")
                .author(new YMLChangelogEntryAuthor("Ala", "Ma", "http://ko.ta"))
                .mergeRequest(1L)
                .issue(1L)
                .link(new YMLChangelogEntryLink("marwin1991", "https://github.com/users/marwin1991"))
                .type(YMLChangelogEntryType.of("added"))
                .importantNote("Some important note")
                .configuration(YMLChangelogEntryConfiguration.builder()
                        .type("DB")
                        .action(YMLChangelogEntryConfigurationAction.ADD)
                        .key("aaa.bbb")
                        .defaultValue("")
                        .description("Some desc")
                        .moreInfo("some more info")
                        .build())
                .build();
    }

}