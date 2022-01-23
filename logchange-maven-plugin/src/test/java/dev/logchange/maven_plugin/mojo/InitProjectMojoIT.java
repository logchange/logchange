package dev.logchange.maven_plugin.mojo;

import com.soebes.itf.jupiter.extension.MavenGoal;
import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;

@MavenJupiterExtension
class InitProjectMojoIT {

    @MavenGoal("${project.groupId}:${project.artifactId}:${project.version}:init")
    @MavenTest
    void pomNotContainsPluginAndProjectNotInit(MavenExecutionResult result) {
        assertThat(result).isSuccessful()
                .project()
                .has("changelog")
                .has("changelog\\unreleased");

//        assertThat(result).isSuccessful()
//                .project()
//                .withFile("changelog\\unreleased\\.gitkeep")
//                .exists();
//
//        assertThat(result)
//                .project()
//                .withFile("CHANGELOG.md")
//                .exists();
//
//        assertThat(result)
//                .out()
//                .plain()
//                .contains("[INFO] Initialize project for keep-changelog maven plugin\n" +
//                        "[INFO] Created: CHANGELOG.md\n" +
//                        "[INFO] Created: changelog\n" +
//                        "[INFO] Created: unreleased\n" +
//                        "[INFO] Created: .gitkeep\n" +
//                        "[INFO] Initialize project successful");
    }

}