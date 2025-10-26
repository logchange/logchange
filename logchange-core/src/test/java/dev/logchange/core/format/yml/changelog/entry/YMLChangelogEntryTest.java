package dev.logchange.core.format.yml.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YMLChangelogEntryTest {

    @Test
    void toConvertsFullyPopulatedEntry() {
        // given:
        YMLChangelogEntry yml = get();

        // when:
        ChangelogEntry domain = yml.to();

        // then:
        assertNotNull(domain);
        assertEquals("Some title", domain.getTitle().getValue());
        assertEquals("added", domain.getType().getKey());

        assertEquals(1, domain.getMergeRequests().size());
        assertEquals(1L, domain.getMergeRequests().get(0).getValue());

        assertEquals(1, domain.getIssues().size());
        assertEquals(1L, domain.getIssues().get(0));

        assertEquals(1, domain.getLinks().size());
        assertEquals("marwin1991", domain.getLinks().get(0).getName());
        assertEquals("https://github.com/users/marwin1991", domain.getLinks().get(0).getUrl());

        assertEquals(1, domain.getAuthors().size());
        assertEquals("Ala", domain.getAuthors().get(0).getName());
        assertEquals("Ma", domain.getAuthors().get(0).getNick());
        assertEquals("http://ko.ta", domain.getAuthors().get(0).getUrl());

        assertEquals(1, domain.getImportantNotes().size());
        assertEquals("Some important note", domain.getImportantNotes().get(0).getValue());

        assertEquals(1, domain.getConfigurations().size());
        assertEquals("DB", domain.getConfigurations().get(0).getType());
        assertEquals("add", domain.getConfigurations().get(0).getAction().getAction());
        assertEquals("aaa.bbb", domain.getConfigurations().get(0).getKey());
        assertEquals("", domain.getConfigurations().get(0).getDefaultValue());
        assertEquals("Some desc", domain.getConfigurations().get(0).getDescription());
        assertEquals("some more info", domain.getConfigurations().get(0).getMoreInfo());

        assertTrue(domain.getModules() == null || domain.getModules().isEmpty());
    }

    @Test
    void toThrowsWhenTypeMissing() {
        // given:
        YMLChangelogEntry yml = YMLChangelogEntry.builder()
                .title("Some title")
                .author(new YMLChangelogEntryAuthor("Ala", "Ma", "http://ko.ta"))
                .build();

        // when-then:
        YMLChangelogEntryParseException ex = assertThrows(YMLChangelogEntryParseException.class, yml::to);
        assertTrue(ex.getMessage().contains("Missing type property!"));
    }

    @Test
    void toThrowsWhenInvalidPropertyWasCollected() {
        // given:
        YMLChangelogEntry yml = getSimple();
        // simulate unknown property collected during parsing
        yml.anySetter("unknown", "value");

        // when-then:
        YMLChangelogEntryParseException ex = assertThrows(YMLChangelogEntryParseException.class, yml::to);
        assertTrue(ex.getMessage().contains("Unknown property [unknown]"));
    }

    @Test
    void canCovertToYAMLString() {
        // given:
        YMLChangelogEntry entry = get();

        // when:
        String result = entry.toYMLString();

        // then:
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
        // given:
        YMLChangelogEntry entry = getSimple();

        // when:
        String result = entry.toYMLString();

        // then:
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