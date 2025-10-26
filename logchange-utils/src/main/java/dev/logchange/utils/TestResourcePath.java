package dev.logchange.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class to resolve the resource path for a given test class.
 * <p>
 * This class provides a method to retrieve the path to the resource directory
 * corresponding to the provided test class. It is typically useful in test
 * scenarios where resources are bundled alongside test classes, and the
 * relative path to these resources needs to be resolved.
 */
public class TestResourcePath {

    public static Path getPath(Class<?> testClass) {
        return  Paths.get(testClass.getClassLoader().getResource(testClass.getSimpleName()).getPath());
    }
}
