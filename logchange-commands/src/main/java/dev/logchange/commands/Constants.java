package dev.logchange.commands;

import static dev.logchange.core.format.release_date.ReleaseDateOption.TODAY_OPTION;

public class Constants {

    public static final String RELEASE_DATE_FORMAT = "yyyy-MM-dd";

    public static final String LOGCHANGE_DESCRIPTION = "\nlogchange (CLI) - logchange is a tool which helps creating CHANGELOG by keeping one format and solving merge request conflicts problem by extraction of new CHANGELOG entries to separate files. \n\n Visit https://logchange.dev/ for more information\n";
    public static final String BASIC_FOOTER = "\n\nFeel free to contact us via email team@logchange.dev \nor visit https://github.com/logchange/logchange";

    public static final String OPTION_SEPARATOR = " ";
    public static final String OPTION_SHORT_PREFIX = "-";
    public static final String OPTION_PREFIX = "--";

    public static final String INPUT_DIR_PROPERTY = "inputDir";
    public static final String INPUT_DIR_OPTION = OPTION_PREFIX + INPUT_DIR_PROPERTY;
    public static final String INPUT_DIR_OPTION_DESCRIPTION = "Logchange project directory, where config and YML files are stored in version directories";

    public static final String CONFIG_FILE_PROPERTY = "configFile";
    public static final String CONFIG_FILE_OPTION = OPTION_PREFIX + CONFIG_FILE_PROPERTY;
    public static final String CONFIG_FILE_OPTION_DESCRIPTION = "Name of config file at <" + INPUT_DIR_PROPERTY + "> directory";

    public static final String OUTPUT_FILE_PROPERTY = "outputFile";
    public static final String OUTPUT_FILE_OPTION = OPTION_PREFIX + OUTPUT_FILE_PROPERTY;
    public static final String OUTPUT_FILE_OPTION_DESCRIPTION = "Name of changelog file, it will be created if it's not present";

    public static final String VERSION_PROPERTY = "version";
    public static final String VERSION_OPTION = OPTION_PREFIX + VERSION_PROPERTY;
    public static final String VERSION_OPTION_DESCRIPTION = "Specifies the version up to which all released versions should be archived (inclusive).";

    public static final String ARCHIVE_FILE = "archive.md";
    public static final String DEFAULT_OUTPUT_FILE = "CHANGELOG.md";
    public static final String DEFAULT_INPUT_DIR = "changelog";
    public static final String DEFAULT_UNRELEASED_VERSION_DIR = "unreleased";
    public static final String DEFAULT_CONFIG_FILE = "logchange-config.yml";
    public static final String DEFAULT_XML_OUTPUT_FILE = "changes.xml";
    public static final String DEFAULT_EXAMPLE_OUTPUT_FILENAME = "00000-entry.yml";

    public static final String UNRELEASED_VERSION_DIR_PROPERTY = "unreleasedVersionDir";
    public static final String UNRELEASED_VERSION_DIR_OPTION = OPTION_PREFIX + UNRELEASED_VERSION_DIR_PROPERTY;
    public static final String UNRELEASED_VERSION_DIR_OPTION_DESCRIPTION = "Name of directory, where YML for unreleased version are stored";


    public static final String GIT_KEEP = ".gitkeep";

    public static final String INIT_COMMAND = "init";
    public static final String INIT_COMMAND_DESCRIPTION = "Initialize directory (project) with basic logchange configuration and directory structure";
    public static final String INIT_COMMAND_START_LOG = "Initialize project for logchange";
    public static final String INIT_COMMAND_END_LOG = "Initialize project successful. We are happy that you have trusted logchange community!\n\n" + BASIC_FOOTER;


    public static final String GENERATE_COMMAND = "generate";
    public static final String GENERATE_COMMAND_DESCRIPTION = "Generates changelog file (<" + OUTPUT_FILE_PROPERTY + ">) based on .yml entries and archives (does not moves any files)";
    public static final String GENERATE_COMMAND_START_LOG = "Running generate command...";
    public static final String GENERATE_COMMAND_END_LOG = "Changelog successfully generated";

    public static final String LINT_COMMAND = "lint";
    public static final String LINT_COMMAND_DESCRIPTION = "Lints and validates YML files and logchange config";
    public static final String LINT_COMMAND_START_LOG = "Running lint command...";
    public static final String LINT_COMMAND_END_LOG = "No problems found, lint passed successfully";

    public static final String ADD_COMMAND = "add";
    public static final String ADD_COMMAND_DESCRIPTION = "Creates new YML file with logchange structure in <" + UNRELEASED_VERSION_DIR_PROPERTY + "> directory";
    public static final String ADD_COMMAND_START_LOG = "Running add command...";
    public static final String ADD_COMMAND_END_LOG = "Changelog entry successfully added";

    public static final String ADD_EXAMPLE_COMMAND = "example";
    public static final String ADD_EXAMPLE_COMMAND_DESCRIPTION = "Creates new example YML file with logchange structure in <" + UNRELEASED_VERSION_DIR_PROPERTY + "> directory";
    public static final String ADD_EXAMPLE_COMMAND_START_LOG = "Running add example command...";
    public static final String ADD_EXAMPLE_COMMAND_END_LOG = "Example changelog entry successfully added";

    public static final String RELEASE_COMMAND = "release";
    public static final String RELEASE_COMMAND_DESCRIPTION = "Creates new changelog release by moving files from <" + UNRELEASED_VERSION_DIR_PROPERTY + "> directory to vX.X.X directory";
    public static final String RELEASE_COMMAND_START_LOG = "Running release command for version: ";
    public static final String RELEASE_COMMAND_END_LOG = "Changelog entry successfully added";

    public static final String RELEASE_DATE_PROPERTY = "releaseDate";
    public static final String RELEASE_DATE_OPTION = OPTION_PREFIX + RELEASE_DATE_PROPERTY;
    public static final String RELEASE_DATE_DEFAULT = TODAY_OPTION;
    public static final String RELEASE_DATE_OPTION_DESCRIPTION = "Release date for the new version: 'none' to skip writing release-date.txt, or in format " + RELEASE_DATE_FORMAT + " to set an explicit date. Default: today.";

    public static final String AGGREGATE_COMMAND = "aggregate";
    public static final String AGGREGATE_COMMAND_DESCRIPTION = "Aggregates projects changelogs to create one. Useful when we have many projects that make up one product.";
    public static final String AGGREGATE_COMMAND_START_LOG = "Running aggregate command...";
    public static final String AGGREGATE_COMMAND_END_LOG = "Aggregate successfully";

    public static final String ARCHIVE_COMMAND = "archive";
    public static final String ARCHIVE_COMMAND_DESCRIPTION = "Archives the list of released versions up to (and including) the specified version by transferring their summaries to archive.md file, merging all existing archives, and deleting the corresponding version directories.";
    public static final String ARCHIVE_COMMAND_START_LOG = "Running archive command...";
    public static final String ARCHIVE_COMMAND_END_LOG = "Archive successfully";

    public static final String DEFAULT_PATH = ".";
    public static final String PATH_PROPERTY = "path";
    public static final String PATH_OPTION = OPTION_PREFIX + PATH_PROPERTY;
    public static final String PATH_SHORT_PROPERTY = "p";
    public static final String PATH_SHORT_OPTION = OPTION_SHORT_PREFIX + PATH_SHORT_PROPERTY;
    public static final String PATH_OPTION_DESCRIPTION = "Path indicating the directory in which the command is to be executed";

    public static final String AGGREGATE_VERSION_PROPERTY = "aggregateVersion";
    public static final String AGGREGATE_VERSION_OPTION = OPTION_PREFIX + AGGREGATE_VERSION_PROPERTY;
    public static final String AGGREGATE_VERSION_OPTION_DESCRIPTION = "Version number that we want to aggregate across all projects defined at <" + CONFIG_FILE_PROPERTY + "> in section aggregates";

    public static final String FILENAME_PROPERTY = "fileName";
    public static final String FILENAME_OPTION = OPTION_PREFIX + FILENAME_PROPERTY;
    public static final String FILENAME_OPTION_DESCRIPTION = "Name of file for new changelog entry for example: task1.yml";

    public static final String AUTHOR_PROPERTY = "author";
    public static final String AUTHOR_OPTION = OPTION_PREFIX + AUTHOR_PROPERTY;
    public static final String AUTHOR_OPTION_DESCRIPTION = "Name of the author for new changelog entry";

    public static final String AUTHORS_PROPERTY = "authors";
    public static final String AUTHORS_OPTION = OPTION_PREFIX + AUTHORS_PROPERTY;
    public static final String AUTHORS_OPTION_DESCRIPTION = "List of authors, separated by commas";

    public static final String EMPTY_PROPERTY = "empty";
    public static final String EMPTY_OPTION = OPTION_PREFIX + EMPTY_PROPERTY;
    public static final String EMPTY_OPTION_DESCRIPTION = "true/false; default false; if set to true, generates empty entry with some lorem ipsum content";

    public static final String BATCH_MODE_PROPERTY = "batchMode";
    public static final String BATCH_MODE_OPTION = OPTION_PREFIX + BATCH_MODE_PROPERTY;
    public static final String BATCH_MODE_OPTION_DESCRIPTION = "Batch mode for generating new changelog entry, no user interaction";

    public static final String GENERATE_CHANGES_XML_PROPERTY = "changesXml";
    public static final String GENERATE_CHANGES_XML_OPTION = OPTION_PREFIX + GENERATE_CHANGES_XML_PROPERTY;
    public static final String GENERATE_CHANGES_XML_OPTION_DESCRIPTION = "[true/false] Should generate also XML file?";

    public static final String XML_OUTPUT_FILE_PROPERTY = "outputFileXml";
    public static final String XML_OUTPUT_FILE_OPTION = OPTION_PREFIX + XML_OUTPUT_FILE_PROPERTY;
    public static final String XML_OUTPUT_FILE_OPTION_DESCRIPTION = "";

    public static final String VERSION_TO_RELEASE_PROPERTY = "versionToRelease";
    public static final String VERSION_TO_RELEASE_OPTION = OPTION_PREFIX + VERSION_TO_RELEASE_PROPERTY;
    public static final String VERSION_TO_RELEASE_OPTION_DESCRIPTION = "Name of the version that we want to release (f.e 2.1.1). If we use many unreleased directories (f.e unreleased-2.1.1, unreleased) logchange will first look for directory with version corresponding to releasing version. If there will be not such a folder, logchange will release from unreleased directory.";
    public static final String VERSION_TO_RELEASE_OPTION_DESCRIPTION_EMPTY = VERSION_TO_RELEASE_OPTION_DESCRIPTION + " If not specified, logchange will use version from current project (removing -SNAPSHOT suffix).";
}
