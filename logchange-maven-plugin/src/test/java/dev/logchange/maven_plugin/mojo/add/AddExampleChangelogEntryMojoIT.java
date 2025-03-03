package dev.logchange.maven_plugin.mojo.add;

import com.soebes.itf.extension.assertj.MavenITAssertions;
import com.soebes.itf.jupiter.extension.*;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@MavenJupiterExtension
public class AddExampleChangelogEntryMojoIT {
    @MavenGoal("${project.groupId}:${project.artifactId}:${project.version}:example")
    @MavenTest
    @DisplayName("Example entry is generated")
    void exampleEntryIsGenerated(MavenExecutionResult result) throws IOException {
        MavenITAssertions.assertThat(result).isSuccessful()
                .project()
                .has("changelog")
                .has("changelog/unreleased");

        File newEntry = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/unreleased/00000-entry.yml");

        assertThat(newEntry).exists();

        assertThat(FileUtils.readFileToString(newEntry, StandardCharsets.UTF_8)).contains(expectedExampleEntryContent());
    }


    @MavenGoal("${project.groupId}:${project.artifactId}:${project.version}:example")
    @MavenTest
    @MavenOption("-DfileName=example-entry.yml")
    @DisplayName("Example entry with passed file name is generated")
    void exampleEntryWithPassedFileNameIsGenerated(MavenExecutionResult result) throws IOException {
        MavenITAssertions.assertThat(result).isSuccessful()
                .project()
                .has("changelog")
                .has("changelog/unreleased");

        File newEntry = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/unreleased/example-entry.yml");

        assertThat(newEntry).exists();

        assertThat(FileUtils.readFileToString(newEntry, StandardCharsets.UTF_8)).contains(expectedExampleEntryContent());
    }


    private String expectedExampleEntryContent(){
        return "# This file is used by logchange tool to generate CHANGELOG.md \uD83C\uDF33 \uD83E\uDE93 => \uD83E\uDEB5 \n" +
                "# Visit https://github.com/logchange/logchange and leave a star \uD83C\uDF1F \n" +
                "# More info about configuration you can find https://github.com/logchange/logchange#yaml-format ⬅\uFE0F⬅ \uFE0F\n" +
                "title: \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\"\n" +
                "authors:\n" +
                "  - name: Gal Anonim\n" +
                "    nick: GaLaNo\n" +
                "    url: https://github.com/anon\n" +
                "merge_requests:\n" +
                "  - 1\n" +
                "issues:\n" +
                "  - 2\n" +
                "links:\n" +
                "  - name: Google\n" +
                "    url: https://google.com\n" +
                "type: added\n" +
                "important_notes:\n" +
                "  - \"Phasellus euismod placerat ullamcorper. Nam a vehicula sapien, at accumsan purus.\"\n" +
                "configurations:\n" +
                "  - type: system environment\n" +
                "    action: add\n" +
                "    key: ABC_DFG\n" +
                "    default_value: \"true\"\n" +
                "    description: Phasellus euismod placerat ullamcorper.\n" +
                "    more_info: \"Nam a vehicula sapien, at accumsan purus.\"";
    }
}
