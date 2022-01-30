package dev.logchange.core.format.md;

public interface MD {
    default String toMD() {
        return toString();
    }
}
