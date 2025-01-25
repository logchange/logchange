package dev.logchange.core.format.md.changelog.entry;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryAuthor;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.domain.config.model.CustomChangelogEntryType;
import dev.logchange.core.domain.config.model.Heading;
import dev.logchange.core.domain.config.model.aggregate.Aggregates;
import dev.logchange.core.domain.config.model.labels.Labels;
import dev.logchange.core.domain.config.model.templates.Templates;
import org.junit.jupiter.api.Test;

import static dev.logchange.core.domain.config.model.templates.Templates.DEFAULT_ENTRY_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MDChangelogEntryAuthorTest {

    @Test
    void givenAuthorWithAllFieldsAndCustomTemplate_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("FirstName LastName", "NickName", "https://google.com");
        Templates templates = Templates.builder()
                .entryFormat(DEFAULT_ENTRY_FORMAT)
                .authorFormat("([${nick}](${url}) @${name})")
                .build();

        Config config = Config.builder()
                .heading(Heading.EMPTY)
                .entryTypes(CustomChangelogEntryType.EMPTY)
                .labels(Labels.EMPTY)
                .templates(templates)
                .aggregates(Aggregates.EMPTY)
                .build();

        //when:
        String result = new MDChangelogEntryAuthor(author, config).toString();

        //then:
        assertEquals("([NickName](https://google.com) @FirstName LastName)", result);
    }

    @Test
    void givenAuthorWithEmptyNameAndCustomTemplate_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("", "NickName", "https://google.com");
        Templates templates = Templates.builder()
                .entryFormat(DEFAULT_ENTRY_FORMAT)
                .authorFormat("([${nick}](${url}) @${name})")
                .build();

        Config config = Config.builder()
                .heading(Heading.EMPTY)
                .entryTypes(CustomChangelogEntryType.EMPTY)
                .labels(Labels.EMPTY)
                .templates(templates)
                .aggregates(Aggregates.EMPTY)
                .build();

        //when:
        String result = new MDChangelogEntryAuthor(author, config).toString();

        //then:
        assertEquals("([NickName](https://google.com))", result);
    }

    @Test
    void givenAuthorWithAllFieldsAndCustomTemplateWithOnlyNick_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("FirstName LastName", "NickName", "https://google.com");
        Templates templates = Templates.builder()
                .entryFormat(DEFAULT_ENTRY_FORMAT)
                .authorFormat("#${nick}")
                .build();

        Config config = Config.builder()
                .heading(Heading.EMPTY)
                .entryTypes(CustomChangelogEntryType.EMPTY)
                .labels(Labels.EMPTY)
                .templates(templates)
                .aggregates(Aggregates.EMPTY)
                .build();

        //when:
        String result = new MDChangelogEntryAuthor(author, config).toString();

        //then:
        assertEquals("#NickName", result);
    }

    @Test
    void givenAuthorWithName_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("FirstName LastName", "", "");

        //when:
        String result = new MDChangelogEntryAuthor(author, Config.EMPTY).toString();

        //then:
        assertEquals("(FirstName LastName)", result);
    }

    @Test
    void givenAuthorWithNameAndNickName_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("FirstName LastName", "NickName", "");

        //when:
        String result = new MDChangelogEntryAuthor(author, Config.EMPTY).toString();

        //then:
        assertEquals("(FirstName LastName @NickName)", result);
    }

    @Test
    void givenAuthorWithNameAndNickNameAndUrl_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("FirstName LastName", "NickName", "https://google.com");

        //when:
        String result = new MDChangelogEntryAuthor(author, Config.EMPTY).toString();

        //then:
        assertEquals("([FirstName LastName](https://google.com) @NickName)", result);
    }

    @Test
    void givenAuthorWithNickNameAndUrl_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("", "NickName", "https://google.com");

        //when:
        String result = new MDChangelogEntryAuthor(author, Config.EMPTY).toString();

        //then:
        assertEquals("([LINK](https://google.com) @NickName)", result);
    }

    @Test
    void givenAuthorWithNameAndUrl_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("FirstName LastName", "", "https://google.com");

        //when:
        String result = new MDChangelogEntryAuthor(author, Config.EMPTY).toString();

        //then:
        assertEquals("([FirstName LastName](https://google.com))", result);
    }

    @Test
    void givenAuthorWithdUrl_whenToString_thenResultMatchesFormat() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("", "", "https://google.com");

        //when:
        String result = new MDChangelogEntryAuthor(author, Config.EMPTY).toString();

        //then:
        assertEquals("([LINK](https://google.com))", result);
    }

    @Test
    void givenSomeAuthor_whenToString_thenResultMatchToMD() {
        //given:
        ChangelogEntryAuthor author = ChangelogEntryAuthor.of("FirstName LastName", "NickName", "https://google.com");

        //when:
        String result1 = new MDChangelogEntryAuthor(author, Config.EMPTY).toString();
        String result2 = new MDChangelogEntryAuthor(author, Config.EMPTY).toMD();

        //then:
        assertEquals(result2, result1);
    }
}
