package dev.logchange.commands.add;

import dev.logchange.commands.add.AddEntryPrompter.PromptMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import static dev.logchange.commands.Constants.EMPTY_PROPERTY;
import static dev.logchange.commands.Constants.FILENAME_PROPERTY;

@RequiredArgsConstructor
public class OutputFileNameProvider {

    private final boolean empty;
    private final AddEntryPrompter prompter;
    private final String commandLineOutputFileName;

    public String get() {
        if (StringUtils.isNotBlank(commandLineOutputFileName)) {
            return adjust(commandLineOutputFileName);
        }

        if (empty) {
            throw new IllegalArgumentException("When using -D" + EMPTY_PROPERTY + " or -DbatchMode option, you have to also use -D" + FILENAME_PROPERTY + "=0001-some-change.yml");
        }

        try {
            return getOutputFileName();
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't get file name", e);
        }
    }

    public String adjust(String commandLineOutputFileName) {
        return commandLineOutputFileName
                .replaceAll("\\.yml", "")
                .replaceAll("\\.yaml", "")
                .replaceAll("/", "_")
                .replaceAll("\\.", "_")
                .replaceAll(":", "_")
                + ".yml";
    }

    private String getOutputFileName() {
        while (true) {
            String name = prompter.prompt(new PromptMessage("What is the filename(e.g. 000231-adding-new-product)"));
            if (StringUtils.isBlank(name)) {
                prompter.showMessage("Filename cannot be empty nor blank!!!");
                continue;
            }
            if (StringUtils.isWhitespace(name)) {
                prompter.showMessage("Filename cannot contain whitespace!!!");
                continue;
            }
            name = name.replace(".yml", "").replace(".yaml", "");
            return name + ".yml";
        }
    }
}
