package dev.logchange.commands.add;

import dev.logchange.core.domain.changelog.model.entry.*;
import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddEntryCommandTest {

    private static final String PATH = "src/test/resources/AddEntryCommandTest";
    private static final String INPUT_DIR = "changelog";
    private static final String UNRELEASED = "unreleased";
    private static final String OUTPUT_FILE = "00001-new-entry.yml";
    private static final String TEST_FILE = "test-file.yml";

    @AfterEach
    void cleanup() {
        new File(PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + OUTPUT_FILE).delete();
    }

    @Test
    void shouldAddEntry() throws IOException {
        // given:
        File outputFile = new File(PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + OUTPUT_FILE);
        assertFalse(outputFile.exists());

        ChangelogEntry entry = ChangelogEntry.builder()
                .title(ChangelogEntryTitle.of("title"))
                .type(ChangelogEntryType.fromNameIgnoreCase("added"))
                .mergeRequest(ChangelogEntryMergeRequest.of(1000L))
                .issue(100L)
                .link(ChangelogEntryLink.of("name", "url"))
                .author(ChangelogEntryAuthor.of("NAME", "NICK", "URL"))
                .importantNote(ChangelogEntryImportantNote.of("important note"))
                .configuration(ChangelogEntryConfiguration.of(
                        "type",
                        ChangelogEntryConfigurationAction.ADD,
                        "key",
                        "defaultValue",
                        "description",
                        "moreInfo"))
                .build();


        // when:
        AddEntryCommand.of(PATH, INPUT_DIR, UNRELEASED).execute(entry, OUTPUT_FILE);

        // then:
        assertTrue(outputFile.exists());
        String expectedContent = FileUtils.fileRead(PATH + "/" + INPUT_DIR + "/" + UNRELEASED + "/" + TEST_FILE, "UTF-8");
        String actualContent = FileUtils.fileRead(outputFile, "UTF-8");
        assertThat(actualContent).isEqualToIgnoringWhitespace(expectedContent);
    }
}
