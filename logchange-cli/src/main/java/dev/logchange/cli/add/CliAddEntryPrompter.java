package dev.logchange.cli.add;

import dev.logchange.commands.add.AddEntryPrompter;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor(staticName = "of")
public class CliAddEntryPrompter implements AddEntryPrompter {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String prompt(PromptMessage message) {
        System.out.print(message);
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
