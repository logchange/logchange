package dev.logchange.md.table;

/**
 * Base class that every markdown element extends.
 */
public abstract class MarkdownElement {
    /**
     * Attempts to generate a String representing this markdown element.
     *
     * @return Markdown as String
     * @throws MarkdownSerializationException If unable to generate a markdown String
     */
    protected abstract String serialize() throws MarkdownSerializationException;

    /**
     * Returns the result of {@link MarkdownElement#serialize()} or the specified fallback if a
     * {@link MarkdownSerializationException} occurred.
     *
     * @param fallback String to return if serialization fails
     * @return Markdown as String or specified fallback
     */
    private String getSerialized(String fallback) {
        try {
            return serialize();
        } catch (MarkdownSerializationException e) {
            return fallback;
        }
    }

    @Override
    public String toString() {
        return getSerialized(this.getClass().getSimpleName());
    }

}
