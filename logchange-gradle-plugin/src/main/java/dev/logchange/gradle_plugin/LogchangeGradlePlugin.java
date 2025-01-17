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

        String taskPrefix = extension.getTaskPrefix();

        project.getTasks().register(taskPrefix + ADD_COMMAND, AddChangelogEntryTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(ADD_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

        project.getTasks().register(taskPrefix + INIT_COMMAND + "Logchange", InitTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(INIT_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

        project.getTasks().register(taskPrefix + LINT_COMMAND, LintChangelogTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(LINT_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

        project.getTasks().register(taskPrefix + GENERATE_COMMAND, GenerateChangelogTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(GENERATE_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

        project.getTasks().register(taskPrefix + RELEASE_COMMAND, ReleaseVersionTask.class, task -> {
            task.setExtension(extension);
            task.setProject(project);
            task.setDescription(RELEASE_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

        project.getTasks().register(taskPrefix + AGGREGATE_COMMAND, ReleaseVersionTask.class, task -> {
            task.setExtension(extension);
            task.setProject(project);
            task.setDescription(AGGREGATE_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

    }

}
