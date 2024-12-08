package dev.logchange.maven_plugin.mojo.add;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.components.interactivity.Prompter;
import org.codehaus.plexus.components.interactivity.PrompterException;

import static dev.logchange.commands.Constants.EMPTY_MVN_PROPERTY;
import static dev.logchange.commands.Constants.FILENAME_MVN_PROPERTY;

@RequiredArgsConstructor
public class OutputFileNameProvider {

    private final boolean empty;
    private final Prompter prompter;
    private final String commandLineOutputFileName;

    public String get() {
        if (StringUtils.isNotBlank(commandLineOutputFileName)) {
            return adjust(commandLineOutputFileName);
        }

        if (empty) {
            throw new IllegalArgumentException("When using -D" + EMPTY_MVN_PROPERTY + " or -DbatchMode option, you have to also use -D" + FILENAME_MVN_PROPERTY + "=0001-some-change.yml");
        }

        try {
            return getOutputFileName();
        } catch (PrompterException e) {
            throw new IllegalArgumentException("Couldn't get file name", e);
        }
    }

    String adjust(String commandLineOutputFileName) {
        return commandLineOutputFileName
                .replaceAll("\\.yml", "")
                .replaceAll("\\.yaml", "")
                .replaceAll("/", "_")
                .replaceAll("\\.", "_")
                .replaceAll(":", "_")
                + ".yml";
    }

    private String getOutputFileName() throws PrompterException {
        while (true) {
            String name = prompter.prompt("What is the filename(e.g. 000231-adding-new-product)");
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
