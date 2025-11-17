package dev.logchange.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Project guardian: no one should use Lombok {@code @Log}.
 * It is required to use {@code @CustomLog} (configuration in lombok.config).
 */
class NoLombokLogAnnotationTest {

    private static final Pattern FORBIDDEN_ANNOTATION = Pattern.compile("(?m)@Log\\b");
    private static final Pattern FORBIDDEN_IMPORT = Pattern.compile("(?m)^\\s*import\\s+lombok\\.extern\\.java\\.Log\\s*;\\s*$");

    private static List<Path> findFilesWithLogAnnotation(Path projectRoot) throws IOException {
        List<Path> offenders = new ArrayList<>();

        Files.walkFileTree(projectRoot, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                String name = dir.getFileName() == null ? "" : dir.getFileName().toString();
                // Skip the build/target directories and test sources
                if (name.equals("target") || name.equals("build") || name.equals("out") || name.equals(".git")) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!file.toString().endsWith(".java")) {
                    return FileVisitResult.CONTINUE;
                }
                if (!file.toString().contains(Paths.get("src", "main", "java").toString())) {
                    return FileVisitResult.CONTINUE;
                }

                String content = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
                if (containsForbiddenLogAnnotation(content) || containsForbiddenLogImport(content)) {
                    offenders.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        return offenders;
    }

    private static boolean containsForbiddenLogAnnotation(String content) {
        if (content.contains("@CustomLog")) {
            // If the file uses @CustomLog, do not treat the mere occurrence of the string "@Log" as a violation
            // (e.g., imports or comments may contain text fragments).
            // We check every match of @Log and make sure it is not part of @CustomLog.
            Matcher m = FORBIDDEN_ANNOTATION.matcher(content);
            while (m.find()) {
                int start = m.start();
                int prefixStart = Math.max(0, start - "@Custom".length());
                String prefix = content.substring(prefixStart, start);
                if (!prefix.equals("@Custom")) {
                    return true;
                }
            }
            return false;
        }

        return FORBIDDEN_ANNOTATION.matcher(content).find();
    }

    private static boolean containsForbiddenLogImport(String content) {
        return FORBIDDEN_IMPORT.matcher(content).find();
    }

    private static Path locateProjectRoot(Path start) throws IOException {
        Path current = start;
        while (current != null) {
            Path pom = current.resolve("pom.xml");
            if (Files.exists(pom)) {
                String xml = new String(Files.readAllBytes(pom), StandardCharsets.UTF_8);
                if (xml.contains("<modules>") && Files.exists(current.resolve("lombok.config"))) {
                    return current;
                }
            }
            current = current.getParent();
        }

        return start.getParent() != null ? start.getParent() : start;
    }

    @Test
    void given_project_sources_when_scanned_then_no_plain_Lombok_Log_annotation_is_used() throws IOException {
        Path projectRoot = locateProjectRoot(Paths.get("").toAbsolutePath());
        List<Path> offenders = findFilesWithLogAnnotation(projectRoot);

        if (!offenders.isEmpty()) {
            StringBuilder message = new StringBuilder();
            message.append("Detected use of Lombok @Log (annotation or import). Use @CustomLog instead.\n")
                    .append("Files violating the rules (paths relative to the project root):\n");
            offenders.stream()
                    .sorted()
                    .forEach(p -> message.append(" - ")
                            .append(projectRoot.relativize(p))
                            .append('\n'));
            fail(message.toString());
        }
    }
}
