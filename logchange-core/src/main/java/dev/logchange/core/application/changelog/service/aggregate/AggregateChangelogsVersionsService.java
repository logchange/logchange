package dev.logchange.core.application.changelog.service.aggregate;

import dev.logchange.core.domain.changelog.command.AggregateChangelogsVersionsUseCase;
import dev.logchange.core.domain.config.model.aggregate.AggregatedProject;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Log
@RequiredArgsConstructor
public class AggregateChangelogsVersionsService implements AggregateChangelogsVersionsUseCase {

    @Override
    public void handle(AggregateChangelogsVersionsCommand command) {
        log.info("Started aggregating command");

        Path tmpDir = createTmpDir();
        List<Exception> exceptions = new ArrayList<>();
        TarGzService tarGzService = new TarGzService(tmpDir);
        for (AggregatedProject project : command.getAggregates().getProjects()) {
            try {
                Path extractedProjectChangelogDir = tarGzService.get(project.getUrl(), project.getInputDir());
            } catch (IOException e) {
                exceptions.add(e);
            }
        }

        if (!exceptions.isEmpty()) {
            throw new YMLAggregationException(exceptions);
        }
    }

    private Path createTmpDir() {
        try {
            return Files.createTempDirectory("tmp");
        } catch (IOException e) {
            String msg = "Cannot proceed without temporary directory!";
            log.severe(msg);
            throw new IllegalStateException(msg, e);
        }
    }
}
