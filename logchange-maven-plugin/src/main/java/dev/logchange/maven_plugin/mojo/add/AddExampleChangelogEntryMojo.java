package dev.logchange.maven_plugin.mojo.add;

import dev.logchange.commands.add.AddEntryCommand;
import dev.logchange.commands.add.ChangelogEntryProviderFactory;
import dev.logchange.commands.add.OutputFileNameProvider;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.codehaus.plexus.components.interactivity.Prompter;

import javax.inject.Inject;

import static dev.logchange.commands.Constants.*;
import static dev.logchange.commands.add.AddChangelogEntryBatchModeParams.empty;

@Mojo(name = ADD_EXAMPLE_COMMAND,
        defaultPhase = LifecyclePhase.NONE,
        requiresProject = false,
        threadSafe = true,
        requiresDependencyResolution = ResolutionScope.NONE,
        aggregator = true
)
public class AddExampleChangelogEntryMojo extends AbstractMojo {

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, property = UNRELEASED_VERSION_DIR_PROPERTY)
    private String unreleasedVersionDir;

    @Parameter(defaultValue = DEFAULT_EXAMPLE_OUTPUT_FILENAME, property = FILENAME_PROPERTY)
    private String fileName;

    @Inject
    private Prompter prompter;

    @Override
    public void execute() {
        getLog().info(ADD_EXAMPLE_COMMAND_START_LOG);

        MavenAddEntryPrompter mavenPrompter = MavenAddEntryPrompter.of(prompter);
        AddEntryCommand addEntryCommand = AddEntryCommand.of(DEFAULT_PATH, inputDir, unreleasedVersionDir);
        fileName = new OutputFileNameProvider(true, mavenPrompter, fileName).get();
        ChangelogEntry entry = new ChangelogEntryProviderFactory(
                true,
                false,
                empty(),
                mavenPrompter).create().get();
        addEntryCommand.execute(entry, fileName);

        getLog().info(ADD_EXAMPLE_COMMAND_END_LOG);
    }
}
