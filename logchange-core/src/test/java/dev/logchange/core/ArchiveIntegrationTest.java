package dev.logchange.core;

import dev.logchange.core.application.changelog.repository.ChangelogPersistence;
import dev.logchange.core.application.changelog.repository.ChangelogQuery;
import dev.logchange.core.application.changelog.service.archive.ArchiveService;
import dev.logchange.core.domain.changelog.command.ArchiveUseCase;
import dev.logchange.core.domain.changelog.model.version.Version;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.archive.FileArchiveRepository;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogRepository;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import dev.logchange.core.infrastructure.query.file.FileReader;
import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArchiveIntegrationTest {

    private static final String PATH = "src/test/resources/ArchiveIntegrationTest/";

    @Test
    void shouldReturnListOfArchivedFiles() throws IOException {
        // given:
        byte[] archiveContent = null;
        List<String> expectedArchivedFiles = Arrays.asList(
                "archive-1.0.0.md",
                "archive-0.1.1.md",
                "archive.md",
                "v1.0.2",
                "v1.0.1"
        );
        File archive = new File(PATH + "changelog/archive.md");
        File expectedArchive = new File(PATH + "EXPECTED_ARCHIVE.md");
        File changelogDirectory = new File(PATH + "changelog");
        String version = "1.0.2";
        if(!archive.createNewFile()){
            archiveContent = Files.readAllBytes(archive.toPath());
        }
        FileRepository fr = FileRepository.of(archive);
        ChangelogPersistence changelogPersistence = new FileArchiveRepository(fr, Config.EMPTY);
        ChangelogQuery changelogQuery = new FileChangelogRepository(PATH, changelogDirectory, Config.EMPTY, new FileReader(), fr, fr);

        ArchiveService archiveService = new ArchiveService(changelogPersistence, changelogQuery);
        ArchiveUseCase.ArchiveCommand command = ArchiveUseCase.ArchiveCommand.of(Version.of(version));

        // when:
        List<String> archivedFiles = archiveService.handle(command);

        // then:
        try {
            assertEquals(expectedArchivedFiles, archivedFiles);
            String expectedContent = FileUtils.fileRead(expectedArchive);
            String actualContent = FileUtils.fileRead(archive);
            assertThat(actualContent).isEqualToIgnoringNewLines(expectedContent);

        } finally {
            // cleanup:
            if(archiveContent != null){
                Files.write(archive.toPath(), archiveContent, StandardOpenOption.TRUNCATE_EXISTING);
            }
        }

    }
}
