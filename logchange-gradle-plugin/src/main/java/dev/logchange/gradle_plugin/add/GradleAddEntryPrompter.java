package dev.logchange.gradle_plugin.add;

import dev.logchange.commands.add.AddEntryPrompter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.io.Console;

@RequiredArgsConstructor(staticName = "of")
public class GradleAddEntryPrompter implements AddEntryPrompter {

    @Override
    public String prompt(String message) {
        Console console = System.console();
        if (console != null) {
            return console.readLine(message);
        } else {
            return JOptionPane.showInputDialog(
                    null,
                    message,
                    "Input required",
                    JOptionPane.QUESTION_MESSAGE
            );
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

}
