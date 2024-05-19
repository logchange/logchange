package dev.logchange.core.application.changelog.service.aggregate;

import dev.logchange.core.domain.changelog.command.AggregateChangelogsVersionsUseCase;
import dev.logchange.core.domain.config.model.aggregate.AggregatedProject;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Log
@RequiredArgsConstructor
public class AggregateChangelogsVersionsService implements AggregateChangelogsVersionsUseCase {

    @Override
    public void handle(AggregateChangelogsVersionsCommand command) {
        log.info("Started aggregating command");

        Path tmpDir = createTmpDir();

        for (AggregatedProject project : command.getAggregates().getProjects()) {

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
