package dev.logchange.maven_plugin;

public class Constants {

    public static final String DEFAULT_OUTPUT_FILE = "CHANGELOG.md";
    public static final String DEFAULT_AGGREGATE_OUTPUT_FILE = "CHANGELOG-aggregate.md";
    public static final String DEFAULT_INPUT_DIR = "changelog";
    public static final String DEFAULT_UNRELEASED_VERSION_DIR = "unreleased";
    public static final String DEFAULT_CONFIG_FILE = "logchange-config.yml";
    public static final String DEFAULT_XML_OUTPUT_FILE = "changes.xml";

    public static final String GIT_KEEP = ".gitkeep";

    public static final String INIT_COMMAND = "init";
    public static final String GENERATE_COMMAND = "generate";
    public static final String LINT_COMMAND = "lint";
    public static final String ADD_COMMAND = "add";
    public static final String RELEASE_COMMAND = "release";
    public static final String AGGREGATE_COMMAND = "aggregate";


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
