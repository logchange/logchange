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
            String inputRequired = JOptionPane.showInputDialog(
                    null,
                    message + " [press cancel to terminate task]",
                    "Input required",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (inputRequired != null) {
                return inputRequired;
            } else {
                String msg = "Cancel pressed, terminating task";
                System.out.println(msg);
                throw new RuntimeException(msg);
            }
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

}
