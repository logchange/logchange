package dev.logchange.core.format.yml.changelog.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YMLChangelogEntryTest {

    @Test
    void canCovertToYAMLString() {
        //given:
        YMLChangelogEntry entry = get();

        //when:
        String result = entry.toYMLString();

        //then:
        assertEquals("title: Some title\n" +
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
                "    description: Some desc\n" +
                "    more_info: some more info\n", result);
    }

    private YMLChangelogEntry get() {
        return YMLChangelogEntry.builder()
                .title("Some title")
                .author(new YMLChangelogEntryAuthor("Ala", "Ma", "http://ko.ta"))
                .mergeRequest(1L)
                .issue(1L)
                .link(new YMLChangelogEntryLink("marwin1991", "https://github.com/users/marwin1991"))
                .type(YMLChangelogEntryType.ADDED)
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