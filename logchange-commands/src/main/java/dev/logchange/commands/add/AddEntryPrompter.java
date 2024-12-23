package dev.logchange.commands.add;

public interface AddEntryPrompter {

    String prompt(String message);

    void showMessage(String message);
}
