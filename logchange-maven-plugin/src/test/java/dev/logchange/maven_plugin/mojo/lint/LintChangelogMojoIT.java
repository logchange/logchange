package dev.logchange.maven_plugin.mojo.lint;

import com.soebes.itf.jupiter.extension.MavenGoal;
import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.io.File;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;

@MavenJupiterExtension
public class LintChangelogMojoIT {

    @MavenGoal("${project.groupId}:${project.artifactId}:${project.version}:lint")
    @MavenTest
    @DisplayName("Project with pom.xml, task.yml and logchange-config.yml in changelog directory - lint succeeds and nothing is moved or generated")
    void lintWithLogchangeConfig(MavenExecutionResult result) {
        assertThat(result).isSuccessful()
                .project()
                .has("changelog")
                .has("changelog/unreleased");


        File gitKeep = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/unreleased/.gitkeep");
        File originalTask = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/unreleased/task.yml");
        File changelog = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "CHANGELOG.md");
        File taskMovedToRelease = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/v1.0.0/task.yml");
        File releaseDateFile = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/v1.0.0/release-date.txt");
        File versionSummary = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/v1.0.0/version-summary.md");

        assertThat(gitKeep).exists();
        assertThat(originalTask).exists();
        assertThat(changelog).doesNotExist();
        assertThat(taskMovedToRelease).doesNotExist();
        assertThat(releaseDateFile).doesNotExist();
        assertThat(versionSummary).doesNotExist();
    }

    @MavenGoal("${project.groupId}:${project.artifactId}:${project.version}:lint")
    @MavenTest
    @DisplayName("Project with pom.xml and task.yml in changelog directory - lint succeeds and nothing is moved or generated")
    void lintWithMissingLogchangeConfig(MavenExecutionResult result) {
        assertThat(result).isSuccessful()
                .project()
                .has("changelog")
                .has("changelog/unreleased");


        File gitKeep = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/unreleased/.gitkeep");
        File originalTask = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/unreleased/task.yml");
        File changelog = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "CHANGELOG.md");
        File taskMovedToRelease = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/v1.0.0/task.yml");
        File releaseDateFile = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/v1.0.0/release-date.txt");
        File versionSummary = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/v1.0.0/version-summary.md");

        assertThat(gitKeep).exists();
        assertThat(originalTask).exists();
        assertThat(changelog).doesNotExist();
        assertThat(taskMovedToRelease).doesNotExist();
        assertThat(releaseDateFile).doesNotExist();
        assertThat(versionSummary).doesNotExist();
    }

    @MavenGoal("${project.groupId}:${project.artifactId}:${project.version}:lint")
    @MavenTest
    @DisplayName("Project with pom.xml without changelog directory - lint fails and nothing is moved or generated")
    void lintWithoutChangelogDirectory(MavenExecutionResult result) {
        assertThat(result).isFailure();


        File gitKeep = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/unreleased/.gitkeep");
        File originalTask = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/unreleased/task.yml");
        File changelog = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "CHANGELOG.md");
        File taskMovedToRelease = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/v1.0.0/task.yml");
        File releaseDateFile = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/v1.0.0/release-date.txt");
        File versionSummary = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/v1.0.0/version-summary.md");

        Assertions.assertThat(gitKeep).doesNotExist();
        Assertions.assertThat(originalTask).doesNotExist();
        Assertions.assertThat(changelog).doesNotExist();
        Assertions.assertThat(taskMovedToRelease).doesNotExist();
        Assertions.assertThat(releaseDateFile).doesNotExist();
        Assertions.assertThat(versionSummary).doesNotExist();
    }

    @MavenGoal("${project.groupId}:${project.artifactId}:${project.version}:lint")
    @MavenTest
    @DisplayName("Project with pom.xml, task.yml with syntax error in changelog directory - lint fails and nothing is moved or generated")
    void lintWithYMLSyntaxError(MavenExecutionResult result) {
        assertThat(result).isFailure()
                .project()
                .has("changelog")
                .has("changelog/unreleased");


        File gitKeep = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/unreleased/.gitkeep");
        File originalTask = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/unreleased/task.yml");
        File changelog = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "CHANGELOG.md");
        File taskMovedToRelease = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/v1.0.0/task.yml");
        File releaseDateFile = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/v1.0.0/release-date.txt");
        File versionSummary = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/v1.0.0/version-summary.md");

        Assertions.assertThat(gitKeep).exists();
        Assertions.assertThat(originalTask).exists();
        Assertions.assertThat(changelog).doesNotExist();
        Assertions.assertThat(taskMovedToRelease).doesNotExist();
        Assertions.assertThat(releaseDateFile).doesNotExist();
        Assertions.assertThat(versionSummary).doesNotExist();
    }
}
