<!-- @formatter:off -->
<!-- noinspection -->
<!-- Prevents auto format, for JetBrains IDE File > Settings > Editor > Code Style (Formatter Tab) > Turn formatter on/off with markers in code comments  -->

<!-- This file is automatically generate by logchange tool 🌳 🪓 => 🪵 -->
<!-- Visit https://github.com/logchange/logchange and leave a star 🌟 -->
<!-- !!! ⚠️ DO NOT MODIFY THIS FILE, YOUR CHANGES WILL BE LOST ⚠️ !!! -->


[1.19.11] - 2025-10-26
----------------------

### Fixed (2 changes)

- The NullPointerException caused by an invalid links section in the YAML was fixed by normalizing null link names and improving error handling during deserialization. Invalid YAML entries now produce clear error messages instead of crashes during release. !635 #634 ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)
- Fixed problem with prompt messages in CLI !651 ([Peter](https://github.com/marwin1991) @marwin1991)

### Dependency updates (25 changes)

- Upgraded actions/download-artifact from v5 to v6 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded actions/upload-artifact from v4 to v5 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded com.hubspot.jinjava:jinjava from 2.8.1 to 2.8.2 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded mcr.microsoft.com/devcontainers/java from 1 to 3 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.groovy:groovy-all from 5.0.1 to 5.0.2 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.maven.plugin-tools:maven-plugin-annotations from 3.15.1 to 3.15.2 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.maven.plugins:maven-plugin-plugin from 3.15.1 to 3.15.2 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.graalvm.buildtools:native-maven-plugin from 0.11.0 to 0.11.1 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.graalvm.buildtools:native-maven-plugin from 0.11.1 to 0.11.2 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.graalvm.nativeimage:svm from 25.0.0 to 25.0.1 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.graalvm.sdk:graal-sdk from 25.0.0 to 25.0.1 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.jacoco:jacoco-maven-plugin from 0.8.13 to 0.8.14 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.junit.jupiter:junit-jupiter from 5.13.4 to 5.14.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.junit.jupiter:junit-jupiter from 5.14.0 to 6.0.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.pitest:pitest-maven from 1.20.3 to 1.20.4 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.pitest:pitest-maven from 1.20.4 to 1.20.6 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.pitest:pitest-maven from 1.20.6 to 1.21.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.sonatype.central:central-publishing-maven-plugin from 0.8.0 to 0.9.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.xmlunit:xmlunit-assertj3 from 2.10.4 to 2.11.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded peter-evans/dockerhub-description from v4 to v5 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded qcastel/github-actions-maven-release from v1.12.43 to v1.12.44 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.14 to v43.0.15 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.15 to v43.0.16 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.16 to v43.0.17 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.17 to v43.0.18 ([logchange-bot](team@logchange.dev) @logchange-bot)


