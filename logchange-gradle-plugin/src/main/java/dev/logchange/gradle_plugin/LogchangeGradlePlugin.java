package dev.logchange.gradle_plugin;

import dev.logchange.gradle_plugin.add.AddChangelogEntryTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import static dev.logchange.commands.Constants.ADD_COMMAND_DESCRIPTION;

public class LogchangeGradlePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        LogchangePluginExtension extension = project.getExtensions()
                .create("logchange", LogchangePluginExtension.class);

        project.getTasks().register("add", AddChangelogEntryTask.class, task -> {
            task.setExtension(extension);
            task.setDescription(ADD_COMMAND_DESCRIPTION);
            task.setGroup("logchange");
        });
    }
}
