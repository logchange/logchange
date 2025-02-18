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

import java.util.List;

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

    @Option(names = FILENAME_OPTION, description = FILENAME_OPTION_DESCRIPTION)
    private String outputFileName;

    @Option(defaultValue = "false", names = BATCH_MODE_OPTION, description = BATCH_MODE_OPTION_DESCRIPTION)
    private boolean batchMode;

    @Option(defaultValue = "false", names = EMPTY_OPTION, description = EMPTY_OPTION_DESCRIPTION)
    private boolean empty;

    @Option(names = OPTION_PREFIX + "title")
    private String title;

    @Option(names = AUTHOR_OPTION, description = AUTHOR_OPTION_DESCRIPTION)
    private String author;

    @Option(names = AUTHORS_OPTION, description = AUTHORS_OPTION_DESCRIPTION, split = ",")
    private List<String> authors;

    @Option(names = OPTION_PREFIX + "type")
    private String type;

    @Option(names = OPTION_PREFIX + "link.name")
    private String linkName;

    @Option(names = OPTION_PREFIX + "link.url")
    private String linkUrl;


    public void runCommand() {
        log.info(ADD_COMMAND_START_LOG);

        CliAddEntryPrompter cliPrompter = CliAddEntryPrompter.of();
        AddEntryCommand addEntryCommand = AddEntryCommand.of(path(), inputDir, unreleasedVersionDir);
        outputFileName = new OutputFileNameProvider(empty, cliPrompter, outputFileName).get();
        ChangelogEntry entry = new ChangelogEntryProviderFactory(empty, batchMode, getParams(), cliPrompter).create().get();
        addEntryCommand.execute(entry, outputFileName);

        log.info(ADD_COMMAND_END_LOG);
    }

    private AddChangelogEntryBatchModeParams getParams() {
        return AddChangelogEntryBatchModeParams.of(
                title,
                author,
                authors,
                type,
                linkName,
                linkUrl
        );
    }

}
