package dev.logchange.cli.archive;

import dev.logchange.cli.BaseCommand;
import dev.logchange.commands.archive.ArchiveVersionCommand;
import lombok.CustomLog;

import static dev.logchange.commands.Constants.*;
import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;

@CustomLog
@Command(name = ARCHIVE_COMMAND,
        description = ARCHIVE_COMMAND_DESCRIPTION,
        separator = OPTION_SEPARATOR,
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class ArchiveVersionCliCommand extends BaseCommand {

    @Option(defaultValue = DEFAULT_INPUT_DIR, names = INPUT_DIR_OPTION, description = INPUT_DIR_OPTION_DESCRIPTION)
    private String inputDir;

    @Option(required = true, names = VERSION_OPTION, description = VERSION_OPTION_DESCRIPTION)
    private String version;

    @Option(defaultValue = DEFAULT_CONFIG_FILE, names = CONFIG_FILE_OPTION, description = CONFIG_FILE_OPTION_DESCRIPTION)
    private String configFile;

    public void runCommand() {
        log.info(ARCHIVE_COMMAND_START_LOG);
        ArchiveVersionCommand.of(path(), inputDir, version, configFile).execute(false);
        log.info(ARCHIVE_COMMAND_END_LOG);
    }
}
