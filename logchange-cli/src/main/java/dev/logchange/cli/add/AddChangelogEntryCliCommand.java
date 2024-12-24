package dev.logchange.cli.add;

import dev.logchange.cli.BaseCommand;
import dev.logchange.commands.add.AddChangelogEntryBatchModeParams;
import dev.logchange.commands.add.AddEntryCommand;
import dev.logchange.commands.add.ChangelogEntryProviderFactory;
import dev.logchange.commands.add.OutputFileNameProvider;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import lombok.CustomLog;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = ADD_COMMAND,
        description = ADD_COMMAND_DESCRIPTION,
        separator = OPTION_SEPARATOR,
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class AddChangelogEntryCliCommand extends BaseCommand {

    @Option(defaultValue = DEFAULT_INPUT_DIR, names = INPUT_DIR_OPTION)
    private String inputDir;

    @Option(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, names = UNRELEASED_VERSION_DIR_OPTION)
    private String unreleasedVersionDir;

    @Option(names = FILENAME_OPTION)
    private String outputFileName;

    @Option(defaultValue = "false", names = BATCH_MODE_OPTION)
    private boolean batchMode;

    @Option(defaultValue = "false", names = EMPTY_OPTION)
    private boolean empty;

    @Option(names = OPTION_PREFIX + "title")
    private String title;

    @Option(names = OPTION_PREFIX + "author")
    private String author;

    @Option(names = OPTION_PREFIX + "type")
    private String type;

    @Option(names = OPTION_PREFIX + "link.name")
    private String linkName;

    @Option(names = OPTION_PREFIX + "link.url")
    private String linkUrl;


    public void runCommand() {
        log.info(ADD_COMMAND_START_LOG);

        CliAddEntryPrompter mavenPrompter = CliAddEntryPrompter.of();
        AddEntryCommand addEntryCommand = AddEntryCommand.of(DEFAULT_PATH, inputDir, unreleasedVersionDir);
        outputFileName = new OutputFileNameProvider(empty, mavenPrompter, outputFileName).get();
        ChangelogEntry entry = new ChangelogEntryProviderFactory(empty, batchMode, getParams(), mavenPrompter).create().get();
        addEntryCommand.execute(entry, outputFileName);

        log.info(ADD_COMMAND_END_LOG);
    }

    private AddChangelogEntryBatchModeParams getParams() {
        return AddChangelogEntryBatchModeParams.of(
                title,
                author,
                type,
                linkName,
                linkUrl
        );
    }

}
