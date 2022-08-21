package dev.logchange.core.infrastructure.persistance.changelog;

import dev.logchange.core.application.changelog.repository.ChangelogRepository;
import dev.logchange.core.domain.changelog.model.Changelog;
import dev.logchange.core.domain.changelog.model.archive.ChangelogArchive;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntry;
import dev.logchange.core.domain.changelog.model.version.ChangelogVersion;
import dev.logchange.core.domain.changelog.model.version.Version;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.changelog.MDChangelog;
import dev.logchange.core.format.release_date.ReleaseDate;
import dev.logchange.core.format.yml.changelog.entry.YMLChangelogEntry;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileChangelogRepository implements ChangelogRepository {

    private final File inputDirectory;
    private final File outputFile;

    private final Config config;

    public FileChangelogRepository(File inputDirectory, File outputFile, Config config) {
        this.inputDirectory = inputDirectory;
        this.outputFile = outputFile;
        this.config = config;
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
        return Changelog.of(versions, archives);
    }

    @Override
    public void save(Changelog changelog) {
        String md = new MDChangelog(config, changelog).toMD();


        try (OutputStream os = Files.newOutputStream(outputFile.toPath());
             PrintWriter out = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8))) {
            out.println(md);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not save changelog to file: " + outputFile + " because: " + e.getMessage());
        }
    }

    /**
     * Returns: The returning list of files is not sorted.
     */
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
                .releaseDateTime(ReleaseDate.getFromDir(versionDirectory))
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
                .sequential()
                .map(file -> YMLChangelogEntry.of(getEntryInputStream(file)))
                .map(YMLChangelogEntry::to)
                .collect(Collectors.toList());
    }

    private Stream<File> getEntriesFiles(File versionDirectory) {
        File[] entriesFiles = versionDirectory.listFiles();

        if (entriesFiles == null) {
            return Stream.empty();
        }

        return Arrays.stream(entriesFiles)
                .filter(file -> file.getName().contains(".yml") || file.getName().contains(".yaml"))
                .sorted((f1, f2) -> Comparator.comparing(File::getName)
                        .compare(f1, f2));
    }

    private InputStream getEntryInputStream(File entryFile) {
        try {
            return new FileInputStream(entryFile);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Cannot find entry file: " + entryFile.getName());
        }
    }

}
