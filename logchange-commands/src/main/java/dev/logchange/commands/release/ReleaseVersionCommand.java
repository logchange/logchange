package dev.logchange.commands.release;

import dev.logchange.commands.generate.GenerateProjectCommand;
import dev.logchange.commands.init.InitProjectCommand;
import dev.logchange.commands.lint.LintProjectCommand;
import dev.logchange.core.format.release_date.FileReleaseDateTime;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static dev.logchange.commands.Constants.GIT_KEEP;

@CustomLog
@RequiredArgsConstructor(staticName = "of")
public class ReleaseVersionCommand {

    /**
     * Separates <unreleasedVersionDir> from version for example
     * if unreleasedVersionDir is "unreleased" and version to release is 1.0.0
     * we have to check if dir unreleased-1.0.0 exists
     */
    private static final String UNRELEASED_DIR_SEPARATOR = "-";

    private final String rootPath;
    private final String version;
    private final String unreleasedVersionDir;
    private final String inputDir;
    private final String outputFile;
    private final String configFile;
    private final boolean isGenerateChangesXml;
    private final String xmlOutputFile;

    public static String getVersion(String version) {
        if (StringUtils.containsIgnoreCase(version, "-SNAPSHOT")) {
            return version.substring(0, StringUtils.indexOfIgnoreCase(version, "-SNAPSHOT"));
        } else {
            return version;
        }
    }

    public void execute() {
        log.info("Begin preparation from new changelog release: " + version);

        Path unreleasedDir = findUnreleasedDir();
        String newDirName = rootPath + "/" + inputDir + "/" + "v" + version;

        LintProjectCommand.of(rootPath, inputDir, outputFile, configFile).validate();

        checkIfAlreadyExists(newDirName);

        FileReleaseDateTime.addToDir(unreleasedDir);
        removeGitKeep(unreleasedDir);
        renameOrMoveDir(unreleasedDir, newDirName);

        GenerateProjectCommand.of(rootPath, inputDir, outputFile, configFile).withXml(xmlOutputFile).execute(isGenerateChangesXml);

        InitProjectCommand.createUnreleased(rootPath, inputDir, unreleasedVersionDir);
        log.info("New changelog release successful");
    }

    private void checkIfAlreadyExists(String newDirName) {
        File newDir = new File(newDirName);
        if (newDir.exists()) {
            throw new RuntimeException("Target directory " + newDirName + " already exist! Are you trying to release it again? Check if its correct version numer you want to release or remove already existing directory!");
        }
    }

    private void renameOrMoveDir(Path unreleasedDirPath, String newDirName) {
        File unreleasedDir = unreleasedDirPath.toFile();
        File newDir = new File(newDirName);
        if (unreleasedDir.renameTo(newDir)) {
            log.info("Renamed " + unreleasedDirPath + " to " + newDirName + " successfully");
        } else {
            log.info("Rename unsuccessful. Moving contents of " + unreleasedDirPath + " into " + newDirName);

            if (!newDir.exists()) {
                if (newDir.mkdir()) {
                    throw new RuntimeException("Target directory " + newDirName + " does not exist and cannot be created, renaming failed, so probably logchange lacks of permissions!");
                }
            }

            moveDirectoryContents(unreleasedDir.toPath(), newDir.toPath());

            if (unreleasedDir.delete()) {
                log.info("Deleted empty folder: " + unreleasedDirPath);
            } else {
                log.warn("Failed to delete folder: " + unreleasedDirPath);
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

    private void removeGitKeep(Path unreleasedDir) {
        File gitKeep = new File(unreleasedDir + "/" + GIT_KEEP);
        if (gitKeep.delete()) {
            log.info("Deleted: " + gitKeep.getName());
        } else {
            log.warn(gitKeep.getName() + " cannot be deleted.");
        }
    }

    private Path findUnreleasedDir() {
        Path dirWithVersion = Paths.get(rootPath, inputDir, unreleasedVersionDir + UNRELEASED_DIR_SEPARATOR + version);

        if (isDir(dirWithVersion)) {
            return dirWithVersion;
        }

        Path standardUnreleasedDir = Paths.get(rootPath, inputDir, unreleasedVersionDir);
        log.info("Could not find " + dirWithVersion + " so checking if " + standardUnreleasedDir + " exists " + "(ps. you can check out unreleased directories with specific version to allow simultaneous development " + "of more than one version at same branch)");

        if (isDir(standardUnreleasedDir)) {
            return standardUnreleasedDir;
        }

        String msg = "THERE IS NO DIRECTORY TO RELEASE FROM! Check if your project contains: " + standardUnreleasedDir + " or " + dirWithVersion + " with YML files as changelog entries. Visit https://github.com/logchange/logchange for more details.";
        log.error(msg);
        throw new RuntimeException(msg);
    }

    private boolean isDir(Path path) {
        if (path.toFile().exists()) {
            if (path.toFile().isDirectory()) {
                log.info("Found directory: " + path);
                return true;
            } else {
                String msg = path + " is not a directory!";
                log.error(msg);
                throw new RuntimeException(msg);
            }
        }
        return false;
    }
}
