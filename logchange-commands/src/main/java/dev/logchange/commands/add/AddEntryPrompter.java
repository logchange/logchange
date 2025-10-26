package dev.logchange.commands.add;

public interface AddEntryPrompter {

    String prompt(PromptMessage message);

    void showMessage(String message);

    class PromptMessage {
        private final String message;

        public PromptMessage(String message) {
            this.message = adjustMessage(message);
        }

        private String adjustMessage(String message) {
            if (message == null) {
                return ": ";
            }

            if (message.endsWith(": ")) {
                return message;
            }

            if (message.endsWith(" ")) {
                return message.trim() + ": ";
            }

            return message + ": ";
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return getMessage();
        }
    }
}
