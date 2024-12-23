package dev.logchange.maven_plugin.mojo.add;

import dev.logchange.commands.add.AddEntryPrompter;
import lombok.RequiredArgsConstructor;
import org.codehaus.plexus.components.interactivity.Prompter;
import org.codehaus.plexus.components.interactivity.PrompterException;

@RequiredArgsConstructor(staticName = "of")
public class MavenAddEntryPrompter implements AddEntryPrompter {

    private final Prompter prompter;

    @Override
    public String prompt(String message) {
        try {
            return prompter.prompt(message);
        } catch (PrompterException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void showMessage(String message) {
        try {
            prompter.showMessage(message);
        } catch (PrompterException e) {
            throw new RuntimeException(e);
        }
    }
}
