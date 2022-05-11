package dev.logchange.maven_plugin.mojo.add;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.components.interactivity.Prompter;
import org.codehaus.plexus.components.interactivity.PrompterException;

@RequiredArgsConstructor
public class OutputFileNameProvider {

    private final Prompter prompter;
    private final String commandLineOutputFileName;

    public String get() {
        if (StringUtils.isNotBlank(commandLineOutputFileName)) {
            return commandLineOutputFileName;
        }

        try {
            return getOutputFileName();
        } catch (PrompterException e) {
            throw new IllegalArgumentException("Couldn't get file name", e);
        }
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
