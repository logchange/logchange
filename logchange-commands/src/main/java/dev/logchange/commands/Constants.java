package dev.logchange.commands;

public class Constants {

    public static final String OPTION_SEPARATOR = " ";
    private static final String OPTION_SHORT_PREFIX = "-";
    private static final String OPTION_PREFIX = "--";

    public static final String BASIC_FOOTER = "\n\n\uD83E\uDEB5\uD83E\uDEB5 Feel free to contact us via email team@logchange.dev \uD83E\uDEB5\uD83E\uDEB5\n\uD83E\uDEB5\uD83E\uDEB5 or visit https://github.com/logchange/logchange \uD83E\uDEB5\uD83E\uDEB5";

    public static final String DEFAULT_OUTPUT_FILE = "CHANGELOG.md";
    public static final String DEFAULT_INPUT_DIR = "changelog";
    public static final String DEFAULT_UNRELEASED_VERSION_DIR = "unreleased";
    public static final String DEFAULT_CONFIG_FILE = "logchange-config.yml";
    public static final String DEFAULT_XML_OUTPUT_FILE = "changes.xml";

    public static final String GIT_KEEP = ".gitkeep";

    public static final String INIT_COMMAND = "init";
    public static final String INIT_COMMAND_DESCRIPTION = "Initialize current directory (project) with basic logchange configuration and directory structure";
    public static final String INIT_COMMAND_START_LOG = "Initialize project for logchange";
    public static final String INIT_COMMAND_END_LOG = "Initialize project successful. We are happy that you have trusted logchange community!\n\n" + BASIC_FOOTER;


    public static final String GENERATE_COMMAND = "generate";
    public static final String GENERATE_COMMAND_DESCRIPTION = "Generates changelog file based on current file content";
    public static final String GENERATE_COMMAND_START_LOG = "Running generate command...";
    public static final String GENERATE_COMMAND_END_LOG = "Changelog successfully generated";


    public static final String LINT_COMMAND = "lint";
    public static final String LINT_COMMAND_DESCRIPTION = "Lints and validates yml files and logchange config";
    public static final String LINT_COMMAND_START_LOG = "Running lint command...";
    public static final String LINT_COMMAND_END_LOG = "No problems found, lint passed successfully";

    public static final String ADD_COMMAND = "add";
    public static final String ADD_COMMAND_DESCRIPTION = "Creates new .yml file with logchange structure";
    public static final String ADD_COMMAND_START_LOG = "Running add command...";
    public static final String ADD_COMMAND_END_LOG = "Changelog entry successfully added";

    public static final String RELEASE_COMMAND = "release";
    public static final String RELEASE_COMMAND_DESCRIPTION = "Creates new changelog release by moving files from unreleased to vX.X.X directory";
    public static final String RELEASE_COMMAND_START_LOG = "Running release command for version: ";
    public static final String RELEASE_COMMAND_END_LOG = "Changelog entry successfully added";

    public static final String AGGREGATE_COMMAND = "aggregate";
    public static final String AGGREGATE_COMMAND_DESCRIPTION = "Aggregates projects changelogs to create one";
    public static final String AGGREGATE_COMMAND_START_LOG = "Running aggregate command...";
    public static final String AGGREGATE_COMMAND_END_LOG = "Aggregate successfully";

    public static final String DEFAULT_PATH = ".";
    public static final String PATH_PROPERTY = "path";
    public static final String PATH_OPTION = OPTION_PREFIX + PATH_PROPERTY;
    public static final String PATH_SHORT_PROPERTY = "p";
    public static final String PATH_SHORT_OPTION = OPTION_SHORT_PREFIX + PATH_SHORT_PROPERTY;
    public static final String PATH_OPTION_DESCRIPTION = "Path to the root directory";

    public static final String INPUT_DIR_PROPERTY = "inputDir";
    public static final String INPUT_DIR_OPTION = OPTION_PREFIX + INPUT_DIR_PROPERTY;
    public static final String INPUT_DIR_OPTION_DESCRIPTION = "Logchange project directory, where config and YML files are stored in version directories";

    public static final String OUTPUT_FILE_MVN_PROPERTY = "outputFile";
    public static final String OUTPUT_FILE_OPTION = OPTION_PREFIX + OUTPUT_FILE_MVN_PROPERTY;
    public static final String OUTPUT_FILE_OPTION_DESCRIPTION = "Name of changelog file, it will be created if it's not present";

    public static final String UNRELEASED_VERSION_DIR_MVN_PROPERTY = "unreleasedVersionDir";
    public static final String UNRELEASED_VERSION_DIR_OPTION = OPTION_PREFIX + UNRELEASED_VERSION_DIR_MVN_PROPERTY;
    public static final String UNRELEASED_VERSION_DIR_OPTION_DESCRIPTION = "Name of directory, where YML for unreleased version are stored";


    public static final String AGGREGATE_VERSION_MVN_PROPERTY = "aggregateVersion";
    public static final String FILENAME_MVN_PROPERTY = "fileName";
    public static final String EMPTY_MVN_PROPERTY = "empty";

    public static final String BATCH_MODE_MVN_PROPERTY = "batchMode";
    public static final String CONFIG_FILE_MVN_PROPERTY = "configFile";
    public static final String GENERATE_CHANGES_XML_PROPERTY = "changesXml";
    public static final String XML_OUTPUT_FILE_PROPERTY = "outputFileXml";

    public static final String RELEASE_DATE_FORMAT = "yyyy-MM-dd";

}
