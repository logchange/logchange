package dev.logchange.maven_plugin.mojo;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.application.changelog.service.generate.GenerateChangelogService;
import dev.logchange.core.domain.changelog.command.GenerateChangelogUseCase;
import dev.logchange.core.infrastructure.changelog.FileChangelogRepository;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.NONE)
public class GenerateChangelogMojo extends AbstractMojo {

    @Parameter(defaultValue = "", property = "heading")
    private String heading;

    @Parameter(defaultValue = "changelog", property = "yamlFilesDirectory")
    private String yamlFilesDirectory;

    @Parameter(defaultValue = "CHANGELOG.md", property = "finalChangelogName")
    private String finalChangelogName;

    @Override
    public void execute() {
        executeGenerate(finalChangelogName, yamlFilesDirectory);
    }

    public void executeGenerate(String finalChangelogName, String yamlFilesDirectory) {
        getLog().info("Started generating " + finalChangelogName);
        File changelogDirectory = findChangelogDirectory("./" + yamlFilesDirectory);

        ChangelogRepository repository = new FileChangelogRepository(changelogDirectory, new File(finalChangelogName));
        GenerateChangelogUseCase generateChangelog = new GenerateChangelogService(repository);
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