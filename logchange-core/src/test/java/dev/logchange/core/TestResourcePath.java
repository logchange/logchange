package dev.logchange.core;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestResourcePath {

    public static Path getPath(Class<?> testClass) {
        return  Paths.get(testClass.getClassLoader().getResource(testClass.getSimpleName()).getPath());
    }
}
