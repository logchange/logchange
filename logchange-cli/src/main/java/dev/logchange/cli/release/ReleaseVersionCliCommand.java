package dev.logchange.cli.release;

import dev.logchange.cli.BaseCommand;
import dev.logchange.commands.release.ReleaseVersionCommand;
import lombok.CustomLog;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import static dev.logchange.commands.Constants.*;

@CustomLog
@Command(name = RELEASE_COMMAND,
        description = RELEASE_COMMAND_DESCRIPTION,
        separator = OPTION_SEPARATOR,
        mixinStandardHelpOptions = true,
        showDefaultValues = true)
public class ReleaseVersionCliCommand extends BaseCommand {


    @Option(required = true, names = "--versionToRelease", description = "Name of the version that we want to release (f.e 2.1.1)")
    private String versionToRelease;

    @Option(defaultValue = DEFAULT_UNRELEASED_VERSION_DIR, names = UNRELEASED_VERSION_DIR_OPTION, description = UNRELEASED_VERSION_DIR_OPTION_DESCRIPTION)
    private String unreleasedVersionDir;

    @Option(defaultValue = DEFAULT_INPUT_DIR, names = INPUT_DIR_OPTION, description = INPUT_DIR_OPTION_DESCRIPTION)
    private String inputDir;

    @Option(defaultValue = DEFAULT_OUTPUT_FILE, names = OUTPUT_FILE_OPTION, description = OUTPUT_FILE_OPTION_DESCRIPTION)
    private String outputFile;

    @Option(defaultValue = DEFAULT_CONFIG_FILE, names = CONFIG_FILE_OPTION, description = CONFIG_FILE_OPTION_DESCRIPTION)
    private String configFile;

    @Option(defaultValue = "false", names = GENERATE_CHANGES_XML_OPTION, description = GENERATE_CHANGES_XML_OPTION_DESCRIPTION)
    private boolean isGenerateChangesXml;

    @Option(defaultValue = DEFAULT_XML_OUTPUT_FILE, names = XML_OUTPUT_FILE_OPTION, description = XML_OUTPUT_FILE_OPTION_DESCRIPTION)
    private String xmlOutputFile;

    public void runCommand() {
        log.info(RELEASE_COMMAND_START_LOG + versionToRelease);
        ReleaseVersionCommand.of(
                DEFAULT_PATH,
                versionToRelease,
                unreleasedVersionDir,
                inputDir,
                outputFile,
                configFile,
                isGenerateChangesXml,
                xmlOutputFile).execute();
        log.info(RELEASE_COMMAND_END_LOG);
    }

}
