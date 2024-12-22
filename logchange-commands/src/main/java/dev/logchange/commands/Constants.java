package dev.logchange.commands;

public class Constants {

    public static final String DEFAULT_OUTPUT_FILE = "CHANGELOG.md";
    public static final String DEFAULT_INPUT_DIR = "changelog";
    public static final String DEFAULT_UNRELEASED_VERSION_DIR = "unreleased";
    public static final String DEFAULT_CONFIG_FILE = "logchange-config.yml";
    public static final String DEFAULT_XML_OUTPUT_FILE = "changes.xml";

    public static final String GIT_KEEP = ".gitkeep";

    public static final String INIT_COMMAND = "init";
    public static final String INIT_COMMAND_DESCRIPTION = "Initialize current project with basic logchange configuration and directory structure";
    public static final String INIT_COMMAND_START_LOG = "Initialize project for logchange";
    public static final String INIT_COMMAND_END_LOG = "Initialize project successful. We are happy that you have trusted logchange community!\n\nFeel free to contact us via email team@logchange.dev or visiting https://github.com/logchange/logchange \uD83E\uDEB5\uD83E\uDEB5";


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

    public static final String INPUT_DIR_MVN_PROPERTY = "inputDir";
    public static final String OUTPUT_FILE_MVN_PROPERTY = "outputFile";
    public static final String UNRELEASED_VERSION_DIR_MVN_PROPERTY = "unreleasedVersionDir";
    public static final String AGGREGATE_VERSION_MVN_PROPERTY = "aggregateVersion";
    public static final String FILENAME_MVN_PROPERTY = "fileName";
    public static final String EMPTY_MVN_PROPERTY = "empty";

    public static final String BATCH_MODE_MVN_PROPERTY = "batchMode";
    public static final String CONFIG_FILE_MVN_PROPERTY = "configFile";
    public static final String GENERATE_CHANGES_XML_PROPERTY = "changesXml";
    public static final String XML_OUTPUT_FILE_PROPERTY = "outputFileXml";

    public static final String RELEASE_DATE_FORMAT = "yyyy-MM-dd";

}
