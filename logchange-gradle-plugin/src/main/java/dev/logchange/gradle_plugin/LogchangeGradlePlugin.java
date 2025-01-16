package dev.logchange.gradle_plugin;

import dev.logchange.gradle_plugin.add.AddChangelogEntryTask;
import dev.logchange.gradle_plugin.generate.GenerateChangelogTask;
import dev.logchange.gradle_plugin.init.InitTask;
import dev.logchange.gradle_plugin.lint.LintChangelogTask;
import dev.logchange.gradle_plugin.release.ReleaseVersionTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import static dev.logchange.commands.Constants.*;

public class LogchangeGradlePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        LogchangePluginExtension extension = project.getExtensions()
                .create("logchange", LogchangePluginExtension.class);

        project.getTasks().register("addChangelogEntry", AddChangelogEntryTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(ADD_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

        project.getTasks().register("initLogchange", InitTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(INIT_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

        project.getTasks().register("lintChangelog", LintChangelogTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(LINT_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

        project.getTasks().register("generateChangelog", GenerateChangelogTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(GENERATE_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

        project.getTasks().register("releaseChangelog", ReleaseVersionTask.class, task -> {
            task.setExtension(extension);
            task.setProject(project);
            task.setDescription(GENERATE_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

        project.getTasks().register("aggregateChangelogs", ReleaseVersionTask.class, task -> {
            task.setExtension(extension);
            task.setProject(project);
            task.setDescription(AGGREGATE_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

    }

}
