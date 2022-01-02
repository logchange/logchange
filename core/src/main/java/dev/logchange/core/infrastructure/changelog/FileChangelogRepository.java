package dev.logchange.core.infrastructure.changelog;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.domain.changelog.model.archive.ChangelogArchive;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.changelog.model.version.Version;
import dev.logchange.core.format.md.changelog.MDChangelog;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntry;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileChangelogRepository implements ChangelogRepository {

    private final String heading;
    private final File inputDirectory;
    private final File outputFile;

    public FileChangelogRepository(String heading, File inputDirectory, File outputFile) {
        this.heading = heading;
        this.inputDirectory = inputDirectory;
        this.outputFile = outputFile;
    }

    @Override
    public Changelog find() {
        List<File> inputFiles = getInputFiles();

        List<ChangelogVersion> versions = new LinkedList<>();
        List<ChangelogArchive> archives = new LinkedList<>();

        for (File file : inputFiles) {
            if (isVersionDirectory(file)) {
                versions.add(getChangelogVersion(file));
            }
            if (isArchive(file)) {
                archives.add(getChangelogArchive(file));
            }
        }
        versions.sort(Collections.reverseOrder());
        return Changelog.of(heading, versions, archives);
    }

    @Override
    public void save(Changelog changelog) {
        String md = new MDChangelog(changelog).toMD();

        try (PrintWriter out = new PrintWriter(outputFile)) {
            out.println(md);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Could not save changelog to file: " + outputFile + " because: " + e.getMessage());
        }
    }

    private List<File> getInputFiles() {
        File[] files = inputDirectory.listFiles();

        if (files == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(files);
    }

    private boolean isVersionDirectory(File file) {
        return file.isDirectory();
    }

    private boolean isArchive(File file) {
        return file.getName().startsWith("archive");
    }

    private ChangelogVersion getChangelogVersion(File versionDirectory) {
        return ChangelogVersion.builder()
                .version(getVersion(versionDirectory))
                // used to skip "v" from directories names
                // we can use "(?!\.)(\d+(\.\d+)+)([-.][A-Z]+)?(?![\d.])$" to get version and skipp all letters before version number
                // but we have to make exception for "unreleased" string as it is not matching this regexp
                .entries(getEntries(versionDirectory))
                .releaseDateTime(getReleaseDateTime(versionDirectory))
                .build();
    }

    private ChangelogArchive getChangelogArchive(File file) {
        try {
            return ChangelogArchive.of(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage());
        }
    }

    private Version getVersion(File versionDirectory) {
        return Version.of(versionDirectory.getName().replace("v", ""));
    }

    private List<ChangelogEntry> getEntries(File versionDirectory) {
        return getEntriesFiles(versionDirectory)
                .map(file -> YMLChangelogEntry.of(getEntryInputStream(file)))
                .map(YMLChangelogEntry::to)
                .collect(Collectors.toList());
    }

    private OffsetDateTime getReleaseDateTime(File versionDirectory) {
//        return Arrays.stream(versionDirectory.listFiles())
//                .filter(file -> file.getName().equals(RELEASE_DATE))
//                .map(ReleaseDateFileParser::getReleaseDateFromFile)
//                .findFirst().orElse(null);
        return null;
    }

    private Stream<File> getEntriesFiles(File versionDirectory) {
        File[] entriesFiles = versionDirectory.listFiles();

        if (entriesFiles == null) {
            return Stream.empty();
        }

        return Arrays.stream(entriesFiles)
                .filter(file -> file.getName().contains(".yml") || file.getName().contains(".yaml"));
    }

    private InputStream getEntryInputStream(File entryFile) {
        try {
            return new FileInputStream(entryFile);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Cannot find entry file: " + entryFile.getName());
        }
    }

}
