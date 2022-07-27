package dev.logchange.maven_plugin.mojo;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.application.changelog.repository.VersionSummaryRepository;
import dev.logchange.core.application.changelog.service.generate.GenerateChangelogService;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.infrastructure.persistance.changelog.FileChangelogRepository;
import dev.logchange.core.infrastructure.persistance.changelog.FileVersionSummaryRepository;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

import static dev.logchange.maven_plugin.Constants.*;

@Mojo(name = GENERATE_COMMAND, defaultPhase = LifecyclePhase.NONE)
public class GenerateChangelogMojo extends AbstractMojo {

    @Parameter(defaultValue = "", property = HEADING_MVN_PROPERTY)
    private String heading;

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_MVN_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_OUTPUT_FILE, property = OUTPUT_FILE_MVN_PROPERTY)
    private String outputFile;

    @Override
    public void execute() {
        executeGenerate(outputFile, inputDir);
    }

    public void executeGenerate(String finalChangelogName, String yamlFilesDirectory) {
        getLog().info("Started generating " + finalChangelogName);
        File changelogDirectory = findChangelogDirectory("./" + yamlFilesDirectory);

        ChangelogRepository repository = new FileChangelogRepository(changelogDirectory, new File(finalChangelogName));
        VersionSummaryRepository versionSummaryRepository = new FileVersionSummaryRepository(changelogDirectory);
        GenerateChangelogUseCase generateChangelog = new GenerateChangelogService(repository, versionSummaryRepository);
        GenerateChangelogUseCase.GenerateChangelogCommand command = GenerateChangelogUseCase.GenerateChangelogCommand.of(heading);

        generateChangelog.handle(command);

        getLog().info("Generating " + finalChangelogName + " successful");
    }

    private File findChangelogDirectory(String directoryPath) {
        File changelogDir = new File(directoryPath);
        if (!changelogDir.exists()) {
            getLog().error("There is no " + directoryPath + " directory in this project !!!");
            throw new RuntimeException("No changelog directory");
        }

        if (!changelogDir.isDirectory()) {
            getLog().error("File " + directoryPath + " is not a directory !!!");
            throw new RuntimeException("File " + directoryPath + " is not a directory");
        }

        return changelogDir;
    }
}