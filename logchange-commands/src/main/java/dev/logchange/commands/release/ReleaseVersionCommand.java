package dev.logchange.commands.release;

import dev.logchange.commands.generate.GenerateProjectCommand;
import dev.logchange.commands.init.InitProjectCommand;
import dev.logchange.commands.lint.LintProjectCommand;
import dev.logchange.core.format.release_date.ReleaseDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static dev.logchange.commands.Constants.GIT_KEEP;

@Log
@RequiredArgsConstructor(staticName = "of")
public class ReleaseVersionCommand {

    private final String rootPath;
    private final String version;
    private final String unreleasedVersionDir;
    private final String inputDir;
    private final String outputFile;
    private final String configFile;
    private final boolean isGenerateChangesXml;
    private final String xmlOutputFile;

    public void execute() {
        log.info("Begin preparation from new changelog release: " + version);

        String unreleasedDir = rootPath + "/" + inputDir + "/" + unreleasedVersionDir;
        String newDirName = rootPath + "/" + inputDir + "/" + "v" + version;

        LintProjectCommand.of(rootPath, inputDir, outputFile, configFile).validate();

        ReleaseDate.addToDir(unreleasedDir);
        removeGitKeep(unreleasedDir);
        renameOrMergeDir(unreleasedDir, newDirName);

        GenerateProjectCommand.of(rootPath, inputDir, outputFile, configFile)
                .withXml(xmlOutputFile)
                .execute(isGenerateChangesXml);

        InitProjectCommand.createUnreleased(rootPath, inputDir, unreleasedVersionDir);
        log.info("New changelog release successful");
    }


    public static String getVersion(String version) {
        if (StringUtils.containsIgnoreCase(version, "-SNAPSHOT")) {
            return version.substring(0, StringUtils.indexOfIgnoreCase(version, "-SNAPSHOT"));
        } else {
            return version;
        }
    }

    private void renameOrMergeDir(String unreleasedDirName, String newDirName) {
        File unreleasedDir = new File(unreleasedDirName);
        File newDir = new File(newDirName);
        if (unreleasedDir.renameTo(newDir)) {
            log.info("Renamed " + unreleasedDirName + " to " + newDirName + " successfully");
        } else {
            log.info("Rename unsuccessful. Merging contents of " + unreleasedDirName + " into " + newDirName);

            if (!newDir.exists()) {
                throw new RuntimeException("Target directory " + newDirName + " does not exist and renaming failed.");
            }

            moveDirectoryContents(unreleasedDir.toPath(), newDir.toPath());

            if (unreleasedDir.delete()) {
                log.info("Deleted empty folder: " + unreleasedDirName);
            } else {
                log.warning("Failed to delete folder: " + unreleasedDirName);
            }
        }
    }

    private void moveDirectoryContents(Path sourceDir, Path targetDir) {
        File[] files = sourceDir.toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                Path targetPath = targetDir.resolve(file.getName());
                try {
                    Files.move(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                log.info("Moved file: " + file.getName() + " to " + targetPath);
            }
        }
    }

    private void removeGitKeep(String unreleasedDir) {
        File gitKeep = new File(unreleasedDir + "/" + GIT_KEEP);
        if (gitKeep.delete()) {
            log.info("Deleted: " + gitKeep.getName());
        } else {
            log.warning(gitKeep.getName() + " cannot be deleted.");
        }
    }
}
