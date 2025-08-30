package dev.logchange.core.infrastructure.persistance.changelog;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.infrastructure.persistance.file.FileRepository;
import dev.logchange.core.infrastructure.query.file.FileReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FileChangelogRepositoryVersionParsingTest {

    private static final String PATH = "src/test/resources/FileChangelogRepositoryVersionParsingTest/";

    @Test
    void shouldStripOnlySingleLeadingVFromVersionDirectoryNames() {
        // given
        File changelogInputDir = new File(PATH + "changelog");
        // writer/xmlWriter not used for findMarkdown, but repository requires them
        File dummyOutput = new File(PATH + "CHANGELOG.md");
        FileRepository fr = FileRepository.of(dummyOutput);
        ChangelogRepository repository = new FileChangelogRepository(PATH, changelogInputDir, Config.EMPTY, new FileReader(), fr, fr);

        // when
        Changelog changelog = repository.findMarkdown();
        List<ChangelogVersion> versions = new ArrayList<>();
        for (ChangelogVersion v : changelog.getVersions()) {
            versions.add(v);
        }
        Set<String> parsedValues = versions.stream()
                .map(v -> v.getVersion().getValue())
                .collect(Collectors.toSet());

        // then
        // Expect: leading 'v'/'V' stripped, internal 'v' untouched, and only a single leading 'v' removed
        assertThat(parsedValues).contains("1.2.3");      // from v1.2.3
        assertThat(parsedValues).contains("2.0.0");      // from V2.0.0
        assertThat(parsedValues).contains("1.2.3-ver8"); // internal 'v' remains
        assertThat(parsedValues).contains("v3.1.4");     // from vv3.1.4 -> only one 'v' removed
    }
}
