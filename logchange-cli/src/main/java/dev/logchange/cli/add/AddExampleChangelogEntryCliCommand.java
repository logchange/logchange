package dev.logchange.cli.add;

import dev.logchange.cli.BaseCommand;
import dev.logchange.commands.add.AddEntryCommand;
import dev.logchange.commands.add.ChangelogEntryProviderFactory;
import dev.logchange.commands.add.OutputFileNameProvider;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import lombok.CustomLog;

import static dev.logchange.commands.Constants.*;
import static dev.logchange.commands.add.AddChangelogEntryBatchModeParams.empty;
import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;

@CustomLog
@Command(name = ADD_EXAMPLE_COMMAND,
        description = ADD_EXAMPLE_COMMAND_DESCRIPTION,
        separator = OPTION_SEPARATOR,
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class AddExampleChangelogEntryCliCommand extends BaseCommand {

    @Option(defaultValue = DEFAULT_INPUT_DIR, names = INPUT_DIR_OPTION)
    private String inputDir;

    @Option(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, names = UNRELEASED_VERSION_DIR_OPTION)
    private String unreleasedVersionDir;

    @Option(names = FILENAME_OPTION, description = FILENAME_OPTION_DESCRIPTION, defaultValue = DEFAULT_EXAMPLE_OUTPUT_FILENAME)
    private String outputFileName;

    @Override
    public void runCommand() {
        log.info(ADD_EXAMPLE_COMMAND_START_LOG);

        CliAddEntryPrompter cliPrompter = CliAddEntryPrompter.of();
        AddEntryCommand addEntryCommand = AddEntryCommand.of(path(), inputDir, unreleasedVersionDir);
        outputFileName = new OutputFileNameProvider(true, cliPrompter, outputFileName).get();
        ChangelogEntry entry = new ChangelogEntryProviderFactory(
                true,
                false,
                empty(),
                cliPrompter).create().get();
        addEntryCommand.execute(entry, outputFileName);

        log.info(ADD_EXAMPLE_COMMAND_END_LOG);
    }
}
