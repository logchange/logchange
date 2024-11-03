package dev.logchange.maven_plugin.mojo.aggregate;

import com.soebes.itf.jupiter.extension.MavenGoal;
import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenOption;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.io.File;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;

@MavenJupiterExtension
class AggregateProjectsMojoIT {

    @MavenGoal("${project.groupId}:${project.artifactId}:${project.version}:aggregate")
    @MavenOption("-DaggregateVersion=0.4.0")
    @MavenTest
    @DisplayName("Project with pom.xml and task.yml in changelog dir after release task.yml is moved to version dir and CHANGELOG.md is generated")
    void aggregateLogchangeAndHofund(MavenExecutionResult result) {
        assertThat(result).isSuccessful()
                .project()
                .has("changelog")
                .has("changelog/unreleased")
                .has("changelog/v0.4.0");

        File versionSummary = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/v0.4.0/version-summary.md");
        Assertions.assertThat(versionSummary).exists();
    }
}
