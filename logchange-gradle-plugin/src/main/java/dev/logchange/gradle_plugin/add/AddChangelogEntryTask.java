package dev.logchange.gradle_plugin.add;

import dev.logchange.gradle_plugin.LogchangePluginExtension;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

import static dev.logchange.commands.Constants.*;

public abstract class AddChangelogEntryTask extends DefaultTask {

    private String fileName;

    @Setter
    private LogchangePluginExtension extension;

    @Option(option = FILENAME_PROPERTY, description = FILENAME_OPTION_DESCRIPTION)
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @TaskAction
    public void doAdd() {
        getLogger().info(ADD_COMMAND_START_LOG);
        if (fileName == null) {
            getLogger().lifecycle("No --fileName provided. Using default...");
        } else {
            getLogger().lifecycle("Got fileName: " + fileName + " extension: " + extension);
        }
        // Tutaj możesz np. wczytać ten plik, zrobić co trzeba...
    }


}
