package dev.logchange.gradle_plugin;

import dev.logchange.gradle_plugin.add.AddChangelogEntryTask;
import dev.logchange.gradle_plugin.init.InitTask;
import dev.logchange.gradle_plugin.lint.LintChangelogTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import static dev.logchange.commands.Constants.*;

public class LogchangeGradlePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        LogchangePluginExtension extension = project.getExtensions()
                .create("logchange", LogchangePluginExtension.class);


        project.getTasks().register("logchange-add", AddChangelogEntryTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(ADD_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

        project.getTasks().register("logchange-init", InitTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(INIT_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });

        project.getTasks().register("logchange-lint", LintChangelogTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(LINT_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });


    }
}
