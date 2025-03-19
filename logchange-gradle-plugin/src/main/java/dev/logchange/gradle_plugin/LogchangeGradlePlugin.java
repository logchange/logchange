package dev.logchange.gradle_plugin;

import dev.logchange.gradle_plugin.add.AddChangelogEntryTask;
import dev.logchange.gradle_plugin.add.AddExampleChangelogEntryTask;
import dev.logchange.gradle_plugin.archive.ArchiveVersionTask;
import dev.logchange.gradle_plugin.generate.GenerateChangelogTask;
import dev.logchange.gradle_plugin.init.InitTask;
import dev.logchange.gradle_plugin.lint.LintChangelogTask;
import dev.logchange.gradle_plugin.release.ReleaseVersionTask;
import org.apache.commons.lang3.StringUtils;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import static dev.logchange.commands.Constants.*;

public class LogchangeGradlePlugin implements Plugin<Project> {

    private static final String GRADLE_CONFIG = "logchange";
    private static final String TASK_PREFIX = "logchange";
    private static final String TASK_GROUP = "logchange";

    @Override
    public void apply(Project project) {
        LogchangePluginExtension extension = project.getExtensions()
                .create(GRADLE_CONFIG, LogchangePluginExtension.class);

        project.getTasks().register(getTaskName(ADD_COMMAND), AddChangelogEntryTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(ADD_COMMAND_DESCRIPTION);
            task.setGroup(TASK_GROUP);
        });

        project.getTasks().register(getTaskName(ADD_EXAMPLE_COMMAND), AddExampleChangelogEntryTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(ADD_EXAMPLE_COMMAND_DESCRIPTION);
            task.setGroup(TASK_GROUP);
        });

        project.getTasks().register(getTaskName(ARCHIVE_COMMAND), ArchiveVersionTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(ARCHIVE_COMMAND_DESCRIPTION);
            task.setGroup(TASK_GROUP);
        });

        project.getTasks().register(getTaskName(INIT_COMMAND), InitTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(INIT_COMMAND_DESCRIPTION);
            task.setGroup(TASK_GROUP);
        });

        project.getTasks().register(getTaskName(LINT_COMMAND), LintChangelogTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(LINT_COMMAND_DESCRIPTION);
            task.setGroup(TASK_GROUP);
        });

        project.getTasks().register(getTaskName(GENERATE_COMMAND), GenerateChangelogTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(GENERATE_COMMAND_DESCRIPTION);
            task.setGroup(TASK_GROUP);
        });

        project.getTasks().register(getTaskName(RELEASE_COMMAND), ReleaseVersionTask.class, task -> {
            task.setExtension(extension);
            task.setProject(project);
            task.setDescription(RELEASE_COMMAND_DESCRIPTION);
            task.setGroup(TASK_GROUP);
        });

        project.getTasks().register(getTaskName(AGGREGATE_COMMAND), ReleaseVersionTask.class, task -> {
            task.setExtension(extension);
            task.setProject(project);
            task.setDescription(AGGREGATE_COMMAND_DESCRIPTION);
            task.setGroup(TASK_GROUP);
        });

    }

    private static String getTaskName(String taskName) {
        return TASK_PREFIX + StringUtils.capitalize(taskName);
    }

}
