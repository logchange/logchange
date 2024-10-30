package dev.logchange.core;

import dev.logchange.core.application.changelog.repository.AggregatedVersionQuery;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.application.changelog.service.aggregate.AggregateProjectsVersionService;
import dev.logchange.core.application.file.query.TarGzQuery;
import dev.logchange.core.domain.changelog.command.AggregateProjectsVersionUseCase;
import dev.logchange.core.domain.changelog.command.AggregateProjectsVersionUseCase.AggregateChangelogsVersionsCommand;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.domain.config.model.aggregate.AggregatedProject;
import dev.logchange.core.domain.config.model.aggregate.AggregatedProjectType;
import dev.logchange.core.domain.config.model.aggregate.Aggregates;
import dev.logchange.core.infrastructure.persistance.changelog.FileVersionSummaryRepository;
import dev.logchange.core.infrastructure.query.changelog.FileAggregatedVersionFinder;
import dev.logchange.core.infrastructure.query.file.FileReader;
import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GenerateVersionSummaryWithPrefixIntegrationTest {

    private static final String PATH = "src/test/resources/GenerateVersionSummaryWithPrefixIntegrationTest/";

    @Test
    void shouldMatchExpectedChangelog() throws IOException {
        //given:
        File changelogInputDir = new File(PATH + "changelog");
        String VERSION_DIR = PATH + "changelog/v1.0.0/";
        File expectedChangelogOutputFile = new File(VERSION_DIR + "expected-version-summary.md");

        AggregateChangelogsVersionsCommand command = AggregateChangelogsVersionsCommand.of(prepareAggregates(), "1.0.0");
        VersionSummaryRepository vsr = new FileVersionSummaryRepository(changelogInputDir, Config.EMPTY);
        AggregatedVersionQuery avq = new FileAggregatedVersionFinder(command.getVersion(), new FileReader());
        TarGzQuery tarGzQuery = new FakeTarGzExtractor(PATH + "extracted/changelog");
        AggregateProjectsVersionUseCase apv = new AggregateProjectsVersionService(avq, vsr, tarGzQuery);

        //when:
        apv.handle(command);
        File versionSummaryFile = new File(VERSION_DIR + "version-summary.md");

        //then:
        String expectedContent = FileUtils.fileRead(expectedChangelogOutputFile);
        String actualContent = FileUtils.fileRead(versionSummaryFile);
        assertThat(actualContent).isEqualToIgnoringNewLines(expectedContent);
    }

    private Aggregates prepareAggregates() {
        List<AggregatedProject> aggregatedProject = new ArrayList<>();
        aggregatedProject.add(new AggregatedProject("TEST_PROJECT", "URL", AggregatedProjectType.TAR_GZ, "changelog"));
        return Aggregates.builder()
                .projects(aggregatedProject)
                .build();
    }

    static class FakeTarGzExtractor implements TarGzQuery {

        private final Path predefinedPath;

        public FakeTarGzExtractor(String predefinedPath) {
            this.predefinedPath = Paths.get(predefinedPath);
        }

        @Override
        public Path get(String projectUrl, String projectInputDir) throws IOException {
            return this.predefinedPath;
        }
    }
}
