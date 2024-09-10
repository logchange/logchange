package dev.logchange.maven_plugin.mojo.lint;

import dev.logchange.maven_plugin.mojo.GenerateChangelogMojo;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import static dev.logchange.maven_plugin.Constants.*;

@Mojo(name = LINT_COMMAND, defaultPhase = LifecyclePhase.NONE)
public class LintChangelogMojo extends AbstractMojo {

    @Parameter(defaultValue = DEFAULT_INPUT_DIR, property = INPUT_DIR_MVN_PROPERTY)
    private String inputDir;

    @Parameter(defaultValue = DEFAULT_OUTPUT_FILE, property = OUTPUT_FILE_MVN_PROPERTY)
    private String outputFile;

    @Parameter(defaultValue = DEFAULT_CONFIG_FILE, property = CONFIG_FILE_MVN_PROPERTY)
    private String configFile;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Begin linting of changelog entries");

        GenerateChangelogMojo generateChangelogMojo = new GenerateChangelogMojo();
        generateChangelogMojo.setLog(getLog());
        generateChangelogMojo.validate(outputFile, inputDir, configFile);

        getLog().info("No errors found");
    }
}
