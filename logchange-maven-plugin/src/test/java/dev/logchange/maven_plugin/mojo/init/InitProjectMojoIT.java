package dev.logchange.maven_plugin.mojo.init;

import com.soebes.itf.jupiter.extension.MavenGoal;
import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;
import org.junit.jupiter.api.DisplayName;

import java.io.File;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;

@MavenJupiterExtension
class InitProjectMojoIT {

    @MavenGoal("${project.groupId}:${project.artifactId}:${project.version}:init")
    @MavenTest
    @DisplayName("Project with pom.xml not containing plugin, not init before, when init, all files should be created")
    void pomNotContainsPluginAndProjectNotInit(MavenExecutionResult result) {
        assertThat(result).isSuccessful()
                .project()
                .has("changelog")
                .has("changelog/unreleased");


        File gitKeep = new File(result.getMavenProjectResult().getTargetProjectDirectory(), "changelog/unreleased/.gitkeep");
        File config = new File(result.getMavenProjectResult().getTargetProjectDirectory(), "changelog/logchange-config.yml");
        File changelog = new File(result.getMavenProjectResult().getTargetProjectDirectory(), "CHANGELOG.md");

        assertThat(gitKeep).exists();
        assertThat(config).exists();
        assertThat(changelog).exists();
    }

}