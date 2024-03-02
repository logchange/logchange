package dev.logchange.maven_plugin.mojo.add;

import com.soebes.itf.jupiter.extension.*;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;

@MavenJupiterExtension
class AddChangelogEntryMojoIT {


    @MavenGoal("${project.groupId}:${project.artifactId}:${project.version}:add")
    @MavenOption(MavenCLIOptions.NON_RECURSIVE)
    @MavenOption(MavenCLIOptions.BATCH_MODE)
    @MavenOption("-DbatchMode")
    @MavenOption("-DfileName=new-entry-1.yml")
    @MavenOption("-Dtitle=\"Upgraded A from 1.0.0 to 2.0.0\"")
    @MavenOption("-Dauthor=marwin1991")
    @MavenOption("-Dtype=dependency_update")
    @MavenOption("-Dlink.name=notes")
    @MavenOption("-Dlink.url=https://logchange.dev")
    @MavenTest
    @DisplayName("New entry is add")
    void newEntryIsAdded(MavenExecutionResult result) throws IOException {

        File newEntry = new File(result.getMavenProjectResult().getTargetProjectDirectory().toString(), "changelog/unreleased/new-entry-1.yml");

        assertThat(newEntry).exists();

        assertThat(FileUtils.readFileToString(newEntry, StandardCharsets.UTF_8))
                .contains(expectedNewEntryContent());
    }


    private String expectedNewEntryContent(){
        return "# This file is used by logchange tool to generate CHANGELOG.md \uD83C\uDF33 \uD83E\uDE93 => \uD83E\uDEB5 \n" +
                "# Visit https://github.com/logchange/logchange and leave a star \uD83C\uDF1F \n" +
                "# More info about configuration you can find https://github.com/logchange/logchange#yaml-format ⬅\uFE0F⬅ \uFE0F\n" +
                "title: '\"Upgraded A from 1.0.0 to 2.0.0\"'\n" +
                "authors:\n" +
                "  - nick: marwin1991\n" +
                "links:\n" +
                "  - name: notes\n" +
                "    url: https://logchange.dev\n" +
                "type: dependency_update";
    }


}