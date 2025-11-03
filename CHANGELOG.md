<!-- @formatter:off -->
<!-- noinspection -->
<!-- Prevents auto format, for JetBrains IDE File > Settings > Editor > Code Style (Formatter Tab) > Turn formatter on/off with markers in code comments  -->

<!-- This file is automatically generate by logchange tool 🌳 🪓 => 🪵 -->
<!-- Visit https://github.com/logchange/logchange and leave a star 🌟 -->
<!-- !!! ⚠️ DO NOT MODIFY THIS FILE, YOUR CHANGES WILL BE LOST ⚠️ !!! -->


[1.19.12] - 2025-11-03
----------------------

### Added (1 change)

- Allow to define release date (`--releaseDate`) for selected date (f.e (`--releaseDate=2025-11-02`) or setting `none` (`--releaseDate=none`) to not set release date. If option is not set, current date will be used (as used to). !663 #662 ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)

### Changed (1 change)

- Refactored TarGzExtractorTest to use mockwebserver and fixed downloading bytes logging !657 ([LINK](https://github.com/witx98) @witx98)

### Dependency updates (6 changes)

- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.20.0 to 2.20.1 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.20.0 to 2.20.1 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded com.squareup.okhttp3:mockwebserver from 5.2.1 to 5.3.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.junit.jupiter:junit-jupiter from 6.0.0 to 6.0.1 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.18 to v43.0.19 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.19 to v43.0.20 ([logchange-bot](team@logchange.dev) @logchange-bot)


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


[1.19.10] - 2025-10-01
----------------------

### Changed (1 change)

- Adjusted prompts for logchange add command !542 ([Peter](https://github.com/marwin1991) @marwin1991)

### Fixed (1 change)

- Fixed problem with empty links urls !597 ([Peter](https://github.com/marwin1991) @marwin1991)

### Dependency updates (20 changes)

- Upgraded actions/github-script from v7 to v8 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded actions/setup-python from v5 to v6 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded com.hubspot.jinjava:jinjava from 2.8.0 to 2.8.1 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.groovy:groovy-all from 5.0.0 to 5.0.1 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.maven.plugins:maven-compiler-plugin from 3.14.0 to 3.14.1 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.maven.plugins:maven-failsafe-plugin from 3.5.3 to 3.5.4 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.maven.plugins:maven-javadoc-plugin from 3.11.3 to 3.12.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.maven.plugins:maven-shade-plugin from 3.6.0 to 3.6.1 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.maven.plugins:maven-surefire-plugin from 3.5.3 to 3.5.4 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.assertj:assertj-core from 3.27.4 to 3.27.6 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.graalvm.nativeimage:svm from 24.2.2 to 25.0.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.graalvm.sdk:graal-sdk from 24.2.2 to 25.0.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.mockito:mockito-core from 5.19.0 to 5.20.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.pitest:pitest-maven from 1.20.2 to 1.20.3 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.projectlombok:lombok from 1.18.38 to 1.18.40 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.projectlombok:lombok from 1.18.40 to 1.18.42 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.xmlunit:xmlunit-assertj3 from 2.10.3 to 2.10.4 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.9 to v43.0.10 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.10 to v43.0.11 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.11 to v43.0.14 ([logchange-bot](team@logchange.dev) @logchange-bot)


[1.19.9] - 2025-08-30
---------------------

### Fixed (1 change)

- Fix: strip only a single leading 'v' or 'V' from version directory names when parsing !588 #548 ([Junie by JetBrains](https://github.com/jetbrains-junie) @jetbrains-junie)

### Dependency updates (15 changes)

- Upgraded actions/checkout from v4 to v5 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded actions/download-artifact from v4 to v5 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded actions/setup-java from v4 to v5 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.19.2 to 2.20.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.19.2 to 2.20.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.commons:commons-compress from 1.27.1 to 1.28.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.groovy:groovy-all from 4.0.28 to 5.0.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.maven.plugins:maven-javadoc-plugin from 3.11.2 to 3.11.3 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.assertj:assertj-core from 3.27.3 to 3.27.4 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.mockito:mockito-core from 5.18.0 to 5.19.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.pitest:pitest-maven from 1.20.1 to 1.20.2 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.5 to v43.0.6 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.6 to v43.0.7 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.7 to v43.0.8 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.8 to v43.0.9 ([logchange-bot](team@logchange.dev) @logchange-bot)


[1.19.8] - 2025-07-29
---------------------

### Dependency updates (10 changes)

- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.19.1 to 2.19.2 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.19.1 to 2.19.2 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded commons-io:commons-io from 2.19.0 to 2.20.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.commons:commons-text from 1.13.1 to 1.14.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.groovy:groovy-all from 4.0.27 to 4.0.28 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.graalvm.buildtools:native-maven-plugin from 0.10.6 to 0.11.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.junit.jupiter:junit-jupiter from 5.13.3 to 5.13.4 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.pitest:pitest-maven from 1.20.0 to 1.20.1 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.3 to v43.0.4 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.4 to v43.0.5 ([logchange-bot](team@logchange.dev) @logchange-bot)


[1.19.7] - 2025-07-18
---------------------

### Added (1 change)

- Add support for modules. !503 ([Łukasz](https://github.com/bambuchaAdm) @bambuchaAdm)

### Dependency updates (11 changes)

- Upgraded org.apache.maven:maven-artifact from 3.9.10 to 3.9.11 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.maven:maven-core from 3.9.10 to 3.9.11 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.maven:maven-plugin-api from 3.9.10 to 3.9.11 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.apache.maven.plugins:maven-gpg-plugin from 3.2.7 to 3.2.8 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.graalvm.nativeimage:svm from 24.2.1 to 24.2.2 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.graalvm.sdk:graal-sdk from 24.2.1 to 24.2.2 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.junit.jupiter:junit-jupiter from 5.13.1 to 5.13.2 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.junit.jupiter:junit-jupiter from 5.13.2 to 5.13.3 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.0 to v43.0.1 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.1 to v43.0.2 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v43.0.2 to v43.0.3 ([logchange-bot](team@logchange.dev) @logchange-bot)


[1.19.6] - 2025-06-22
---------------------

### Fixed (1 change)

- Fix custom entry types when using CLI (missing reflect-config.json definitions) !542 #540 ([Peter](https://github.com/marwin1991) @marwin1991)


[1.19.5] - 2025-06-22
---------------------

### Fixed (1 change)

- Fixed support for versioned unreleased directories during CHANGELOG generation !534 !536 #533 ([Jan Høydahl](https://github.com/janhoy) @janhoy)

### Dependency updates (3 changes)

- Upgraded com.google.cloud.tools:jib-maven-plugin from 3.4.5 to 3.4.6 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded org.pitest:pitest-maven from 1.19.6 to 1.20.0 ([logchange-bot](team@logchange.dev) @logchange-bot)
- Upgraded renovatebot/github-action from v42.0.6 to v43.0.0 ([logchange-bot](team@logchange.dev) @logchange-bot)


[1.19.4] - 2025-06-15
---------------------

### Fixed (1 change)

- Fixed publishing logchange to Maven Central - expired GPG keys ([Peter](https://github.com/marwin1991) @marwin1991)


[1.19.3] - 2025-06-15
---------------------

### Dependency updates (27 changes)

- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.18.3 to 2.19.0 (marwin1991)
- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.19.0 to 2.19.1 (marwin1991)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.18.3 to 2.19.0 (marwin1991)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.19.0 to 2.19.1 (marwin1991)
- Upgraded com.hubspot.jinjava:jinjava from 2.7.4 to 2.8.0 (marwin1991)
- Upgraded org.apache.groovy:groovy-all from 4.0.26 to 4.0.27 (marwin1991)
- Upgraded org.apache.maven:maven-artifact from 3.9.9 to 3.9.10 (marwin1991)
- Upgraded org.apache.maven:maven-core from 3.9.9 to 3.9.10 (marwin1991)
- Upgraded org.apache.maven:maven-plugin-api from 3.9.9 to 3.9.10 (marwin1991)
- Upgraded org.junit.jupiter:junit-jupiter from 5.12.2 to 5.13.0 (marwin1991)
- Upgraded org.junit.jupiter:junit-jupiter from 5.13.0 to 5.13.1 (marwin1991)
- Upgraded org.mockito:mockito-core from 5.17.0 to 5.18.0 (marwin1991)
- Upgraded org.pitest:pitest-junit5-plugin from 1.2.2 to 1.2.3 (marwin1991)
- Upgraded org.pitest:pitest-maven from 1.19.1 to 1.19.2 (marwin1991)
- Upgraded org.pitest:pitest-maven from 1.19.2 to 1.19.3 (marwin1991)
- Upgraded org.pitest:pitest-maven from 1.19.3 to 1.19.4 (marwin1991)
- Upgraded org.pitest:pitest-maven from 1.19.4 to 1.19.5 (marwin1991)
- Upgraded org.xmlunit:xmlunit-assertj3 from 2.10.0 to 2.10.1 (marwin1991)
- Upgraded org.xmlunit:xmlunit-assertj3 from 2.10.1 to 2.10.2 (marwin1991)
- Upgraded renovatebot/github-action from v41.0.20 to v41.0.21 (marwin1991)
- Upgraded renovatebot/github-action from v41.0.21 to v41.0.22 (marwin1991)
- Upgraded renovatebot/github-action from v41.0.22 to v42.0.0 (marwin1991)
- Upgraded renovatebot/github-action from v42.0.0 to v42.0.1 (marwin1991)
- Upgraded renovatebot/github-action from v42.0.1 to v42.0.2 (marwin1991)
- Upgraded renovatebot/github-action from v42.0.2 to v42.0.3 (marwin1991)
- Upgraded renovatebot/github-action from v42.0.3 to v42.0.4 (marwin1991)
- Upgraded renovatebot/github-action from v42.0.4 to v42.0.5 (marwin1991)


[1.19.2] - 2025-04-19
---------------------

### Added (1 change)

- Added archivization of old CHANGELOG.md during init command execution !493 #492 ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Dependency updates (6 changes)

- Upgraded info.picocli:picocli from 4.7.6 to 4.7.7 (marwin1991)
- Upgraded info.picocli:picocli-codegen from 4.7.6 to 4.7.7 (marwin1991)
- Upgraded org.graalvm.nativeimage:svm from 24.2.0 to 24.2.1 (marwin1991)
- Upgraded org.graalvm.sdk:graal-sdk from 24.2.0 to 24.2.1 (marwin1991)
- Upgraded org.junit.jupiter:junit-jupiter from 5.11.4 to 5.12.2 (marwin1991)
- Upgraded renovatebot/github-action from v41.0.19 to v41.0.20 (marwin1991)


[1.19.1] - 2025-04-12
---------------------

### Added (1 change)

- Enabled HTTP/HTTPS URL protocols in native image build !482 ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Dependency updates (4 changes)

- Upgraded commons-io:commons-io from 2.18.0 to 2.19.0 (marwin1991)
- Upgraded org.apache.commons:commons-text from 1.13.0 to 1.13.1 (marwin1991)
- Upgraded org.pitest:pitest-maven from 1.19.0 to 1.19.1 (marwin1991)
- Upgraded renovatebot/github-action from v41.0.18 to v41.0.19 (marwin1991)


[1.19.0] - 2025-04-06
---------------------

### Added (1 change)

- Jinja2 changelog templates - allows to define custom changelog styles. !466 #406 ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)

### Fixed (1 change)

- Fixed validating entries with missing title or type !476 #473 [issue#473](https://github.com/logchange/logchange/issues/473) ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Dependency updates (7 changes)

- Upgraded org.apache.maven.plugins:maven-failsafe-plugin from 3.5.2 to 3.5.3 (marwin1991)
- Upgraded org.apache.maven.plugins:maven-surefire-plugin from 3.5.2 to 3.5.3 (marwin1991)
- Upgraded org.codehaus.plexus:plexus-interactivity-api from 1.3 to 1.4 (marwin1991)
- Upgraded org.mockito:mockito-junit-jupiter from 5.16.1 to 5.17.0 (marwin1991)
- Upgraded org.projectlombok:lombok from 1.18.36 to 1.18.38 (marwin1991)
- Upgraded renovatebot/github-action from v41.0.16 to v41.0.17 (marwin1991)
- Upgraded renovatebot/github-action from v41.0.17 to v41.0.18 (marwin1991)


[1.18.0] - 2025-03-20
---------------------

### Added (1 change)

- Created 'archive' command allowing to cleanup old versions files !462 #434 ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Dependency updates (8 changes)

- Upgraded com.google.cloud.tools:jib-maven-plugin from 3.4.4 to 3.4.5 (marwin1991)
- Upgraded org.graalvm.buildtools:native-maven-plugin from 0.10.5 to 0.10.6 (marwin1991)
- Upgraded org.graalvm.nativeimage:svm from 24.1.2 to 24.2.0 (marwin1991)
- Upgraded org.graalvm.sdk:graal-sdk from 24.1.2 to 24.2.0 (marwin1991)
- Upgraded org.mockito:mockito-junit-jupiter from 5.15.2 to 5.16.0 (marwin1991)
- Upgraded org.mockito:mockito-junit-jupiter from 5.16.0 to 5.16.1 (marwin1991)
- Upgraded org.pitest:pitest-maven from 1.18.2 to 1.19.0 (marwin1991)
- Upgraded renovatebot/github-action from v41.0.14 to v41.0.16 (marwin1991)


[1.17.0] - 2025-03-03
---------------------

### Added (3 changes)

- Created 'example' command allowing to quickly add pre-filled entry !449 #339 ([Mateusz Witkowski](https://github.com/witx98) @witx98)
- Added path option to all CLI commands !447 #373 ([Mateusz Witkowski](https://github.com/witx98) @witx98)
- Created homebrew formula test template !448 ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Dependency updates (8 changes)

- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.18.2 to 2.18.3 (marwin1991)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.18.2 to 2.18.3 (marwin1991)
- Upgraded org.apache.groovy:groovy-all from 4.0.25 to 4.0.26 (marwin1991)
- Upgraded org.apache.maven.plugins:maven-compiler-plugin from 3.13.0 to 3.14.0 (marwin1991)
- Upgraded org.pitest:pitest-junit5-plugin from 1.2.1 to 1.2.2 (marwin1991)
- Upgraded org.pitest:pitest-maven from 1.18.1 to 1.18.2 (marwin1991)
- Upgraded org.slf4j:slf4j-api from 2.0.16 to 2.0.17 (marwin1991)
- Upgraded renovatebot/github-action from v41.0.13 to v41.0.14 (marwin1991)


[1.16.10] - 2025-02-13
----------------------


[1.16.9] - 2025-02-13
---------------------

### Added (1 change)

- Created workflow automation of logchange homebrew formula generation !443 #405 ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Changed (1 change)

- Added standalone CLI build workflow and simplified release process !442 ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Dependency updates (5 changes)

- Upgraded org.graalvm.buildtools:native-maven-plugin from 0.10.4 to 0.10.5 (marwin1991)
- Upgraded org.pitest:pitest-maven from 1.17.4 to 1.18.0 (marwin1991)
- Upgraded org.pitest:pitest-maven from 1.18.0 to 1.18.1 (marwin1991)
- Upgraded renovatebot/github-action from v41.0.11 to v41.0.12 (marwin1991)
- Upgraded renovatebot/github-action from v41.0.12 to v41.0.13 (marwin1991)


[1.16.8] - 2025-01-27
---------------------

### Added (2 changes)

- Added for Maven release command versionToRelease property to define releasing version otherwise it is taken from pom.xml. !425 ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)
- Added possibility to define author templates. !431 #510 ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Fixed (3 changes)

- Fix plugin for multi module maven plugins. Maven mojos should only be run for parent module. !425 #101 ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)
- Allowed to run all Maven commands without project. !425 ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)
- Added validation to prevent release when release with the same version exists. !427 #426 ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)

### Dependency updates (5 changes)

- Upgraded com.marcnuri.plugins:gradle-api-maven-plugin from 0.0.6 to 0.0.7 (marwin1991)
- Upgraded org.apache.groovy:groovy-all from 4.0.24 to 4.0.25 (marwin1991)
- Upgraded org.graalvm.nativeimage:svm from 24.1.1 to 24.1.2 (marwin1991)
- Upgraded org.graalvm.sdk:graal-sdk from 24.1.1 to 24.1.2 (marwin1991)
- Upgraded renovatebot/github-action from v41.0.10 to v41.0.11 (marwin1991)


[1.16.7] - 2025-01-21
---------------------

### Added (1 change)

- Allowed to define custom entry types in `logchange-config.yml` !421 #399 ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)


[1.16.6] - 2025-01-20
---------------------

### Changed (1 change)

- Added `logchange` prefix to all Gradle tasks. Removed broken taskPrefix property from Gradle plugin configuration. [Issue-397 Commnet](https://github.com/logchange/logchange/issues/397#issuecomment-2603254252) ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)

### Fixed (1 change)

- Fixed `cancel` and `x` button for add task of Gradle plugin Swing prompt. ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)

### Dependency updates (1 change)

- Upgraded renovatebot/github-action from v41.0.9 to v41.0.10 (marwin1991)


[1.16.5] - 2025-01-19
---------------------

### Fixed (1 change)

- Changed groovy-all scope dependency for gradle plugin to provided. ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)

### Dependency updates (1 change)

- Upgraded org.assertj:assertj-core from 3.27.2 to 3.27.3 (marwin1991)


[1.16.4] - 2025-01-18
---------------------

### Fixed (1 change)

- Fixed deployed to maven central repository. ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)


[1.16.3] - 2025-01-17
---------------------

### Fixed (1 change)

- Fixed deployed to maven central repository. ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)


[1.16.2] - 2025-01-17
---------------------

### Added (1 change)

- Added possibility to include author name, nick and url information in batch mode options --author and --authors !413 ([Mateusz Witkowski](https://github.com/witx98) @witx98)


[1.16.1] - 2025-01-17
---------------------

### Added (1 change)

- Created authors batch mode option !408 #401 ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Changed (1 change)

- Changed modules to be deployed to maven central to parent (logchange), maven and gradle plugin ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Fixed (2 changes)

- Fixed Gradle release command description ([Peter](https://github.com/marwin1991) @marwin1991)
- Added skipping already provided options in interactive mode (batchMode = false) !409 #400 ([Mateusz Witkowski](https://github.com/witx98) @witx98)


[1.16.0] - 2025-01-16
---------------------

### Added (1 change)

- Added Gradle plugin !398 #397 ([Peter](https://github.com/marwin1991) @marwin1991)

### Dependency updates (1 change)

- Upgraded renovatebot/github-action from v41.0.8 to v41.0.9 (@marwin1991)


[1.15.0] - 2025-01-12
---------------------

### Changed (1 change)

- Changed maven ossrh deployment configuration to prevent publishing of logchange-test-reports and main module. !395 #355 ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Dependency updates (1 change)

- Upgraded org.pitest:pitest-maven from 1.17.3 to 1.17.4 (@marwin1991)


[1.14.0] - 2025-01-10
---------------------

### Fixed (1 change)

- Fixed returned CLI exit code when application fails !392 ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Dependency updates (7 changes)

- Upgraded docker/build-push-action from v5 to v6 (@marwin1991)
- Upgraded org.assertj:assertj-core from 3.27.0 to 3.27.1 (@marwin1991)
- Upgraded org.assertj:assertj-core from 3.27.1 to 3.27.2 (@marwin1991)
- Upgraded org.graalvm.sdk:graal-sdk from 23.1.5 to 24.1.1 (@marwin1991)
- Upgraded org.mockito:mockito-junit-jupiter from 5.14.2 to 5.15.2 (@marwin1991)
- Upgraded peter-evans/dockerhub-description from v3 to v4 (@marwin1991)
- Upgraded renovatebot/github-action from v41.0.7 to v41.0.8 (@marwin1991)


[1.13.0] - 2024-12-26
---------------------

### Added (1 change)

- Added new modules for commands logic and new cli tool !31 !360 !361 !364 !379 ([Mateusz Witkowski](https://github.com/witx98) @witx98) ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)

### Changed (1 change)

- Changed maven deployment configuration to prevent publishing of logchange-test-reports and main module. Updated modules descriptions. !358 #355 ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Dependency updates (16 changes)

- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.18.1 to 2.18.2 (@marwin1991)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.18.1 to 2.18.2 (@marwin1991)
- Upgraded org.apache.commons:commons-text from 1.12.0 to 1.13.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-javadoc-plugin from 3.11.1 to 3.11.2 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-resources-plugin from 3.3.0 to 3.3.1 (@marwin1991)
- Upgraded org.assertj:assertj-core from 3.26.3 to 3.27.0 (@marwin1991)
- Upgraded org.graalvm.buildtools:native-maven-plugin from 0.10.3 to 0.10.4 (@marwin1991)
- Upgraded org.graalvm.sdk:graal-sdk from 23.0.1 to 23.1.5 (@marwin1991)
- Upgraded org.junit.jupiter:junit-jupiter from 5.10.2 to 5.11.4 (@marwin1991)
- Upgraded org.pitest:pitest-maven from 1.17.1 to 1.17.2 (@marwin1991)
- Upgraded org.pitest:pitest-maven from 1.17.2 to 1.17.3 (@marwin1991)
- Upgraded plexsystems/container-structure-test-action from v0.1.0 to v0.3.0 (@marwin1991)
- Upgraded qcastel/github-actions-maven-release from v1.12.41 to v1.12.43 (@marwin1991)
- Upgraded renovatebot/github-action from v41.0.3 to v41.0.4 (@marwin1991)
- Upgraded renovatebot/github-action from v41.0.4 to v41.0.5 (@marwin1991)
- Upgraded renovatebot/github-action from v41.0.6 to v41.0.7 (@marwin1991)


[1.12.0] - 2024-11-21
---------------------

### Added (1 change)

- Added ability to configure the entry format in the Changelog.md file. !351 #338 ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Fixed (1 change)

- Replaced specific ValueInstantiationException handling with a generic Exception catch to streamline error management during YMLChangelogEntry parsing. !343 ([LINK](https://github.com/witx98) @witx98)

### Dependency updates (7 changes)

- Upgraded commons-io:commons-io from 2.17.0 to 2.18.0 (@marwin1991)
- Upgraded org.apache.commons:commons-compress from 1.26.1 to 1.27.1 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-javadoc-plugin from 3.10.1 to 3.11.1 (@marwin1991)
- Upgraded org.pitest:pitest-maven from 1.17.0 to 1.17.1 (@marwin1991)
- Upgraded org.projectlombok:lombok from 1.18.34 to 1.18.36 (@marwin1991)
- Upgraded renovatebot/github-action from v40.3.4 to v40.3.5 (@marwin1991)
- Upgraded renovatebot/github-action from v40.3.5 to v41.0.3 (@marwin1991)


[1.11.0] - 2024-11-03
---------------------

### Added (1 change)

- Introduced functionality for generating an aggregated changelog version summary from multiple projects. !335 ([LINK](https://github.com/witx98) @witx98)

### Dependency updates (8 changes)

- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.18.0 to 2.18.1 (@marwin1991)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.18.0 to 2.18.1 (@marwin1991)
- Upgraded org.apache.maven.plugin-tools:maven-plugin-annotations from 3.15.0 to 3.15.1 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-failsafe-plugin from 3.5.1 to 3.5.2 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-plugin-plugin from 3.15.0 to 3.15.1 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-site-plugin from 3.20.0 to 3.21.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-surefire-plugin from 3.5.1 to 3.5.2 (@marwin1991)
- Upgraded renovatebot/github-action from v40.3.3 to v40.3.4 (@marwin1991)


[1.10.0] - 2024-10-15
---------------------

### Added (1 change)

- Enabled the option to re-release already released version. !328 #316 ([LINK](https://github.com/witx98) @witx98)


[1.9.0] - 2024-10-12
--------------------


[1.8.0] - 2024-10-12
--------------------

### Changed (1 change)

- Enabled the creation of changelog for release versions with suffixes starting with '-' (except for '-SNAPSHOT'). !322 #321 (@witx98)

### Dependency updates (10 changes)

- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.17.2 to 2.18.0 (@marwin1991)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.17.2 to 2.18.0 (@marwin1991)
- Upgraded commons-io:commons-io from 2.16.1 to 2.17.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-failsafe-plugin from 3.5.0 to 3.5.1 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-gpg-plugin from 3.2.5 to 3.2.7 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-javadoc-plugin from 3.10.0 to 3.10.1 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-surefire-plugin from 3.5.0 to 3.5.1 (@marwin1991)
- Upgraded org.pitest:pitest-maven from 1.16.3 to 1.17.0 (@marwin1991)
- Upgraded renovatebot/github-action from v40.2.8 to v40.3.1 (@marwin1991)
- Upgraded renovatebot/github-action from v40.3.1 to v40.3.3 (@marwin1991)


[1.7.0] - 2024-09-13
--------------------

### Dependency updates (5 changes)

- Upgraded org.apache.maven:maven-core from 3.9.8 to 3.9.9 (@marwin1991)
- Upgraded org.apache.maven:maven-plugin-api from 3.9.6 to 3.9.9 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-site-plugin from 3.12.1 to 3.20.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-surefire-plugin from 3.3.1 to 3.5.0 (@marwin1991)
- Upgraded org.pitest:pitest-maven from 1.16.1 to 1.16.3 (@marwin1991)


[1.6.0] - 2024-09-10
--------------------

### Dependency updates (41 changes)

- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.17.0 to 2.17.1 (@marwin1991)
- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.17.1 to 2.17.2 (@marwin1991)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.17.0 to 2.17.1 (@marwin1991)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.17.1 to 2.17.2 (@marwin1991)
- Upgraded commons-io:commons-io from 2.16.0 to 2.16.1 (@marwin1991)
- Upgraded org.apache.commons:commons-text from 1.11.0 to 1.12.0 (@marwin1991)
- Upgraded org.apache.maven:maven-artifact from 3.9.6 to 3.9.7 (@marwin1991)
- Upgraded org.apache.maven:maven-artifact from 3.9.7 to 3.9.8 (@marwin1991)
- Upgraded org.apache.maven:maven-artifact from 3.9.8 to 3.9.9 (@marwin1991)
- Upgraded org.apache.maven:maven-core from 3.9.6 to 3.9.7 (@marwin1991)
- Upgraded org.apache.maven:maven-core from 3.9.7 to 3.9.8 (@marwin1991)
- Upgraded org.apache.maven:maven-core from 3.9.8 to 3.9.9 (@marwin1991)
- Upgraded org.apache.maven:maven-plugin-api from 3.9.6 to 3.9.9 (@marwin1991)
- Upgraded org.apache.maven.plugin-tools:maven-plugin-annotations from 3.12.0 to 3.13.0 (@marwin1991)
- Upgraded org.apache.maven.plugin-tools:maven-plugin-annotations from 3.13.0 to 3.13.1 (@marwin1991)
- Upgraded org.apache.maven.plugin-tools:maven-plugin-annotations from 3.13.1 to 3.15.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-failsafe-plugin from 3.2.5 to 3.3.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-failsafe-plugin from 3.3.0 to 3.3.1 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-failsafe-plugin from 3.3.1 to 3.5.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-gpg-plugin from 3.2.2 to 3.2.3 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-gpg-plugin from 3.2.3 to 3.2.4 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-gpg-plugin from 3.2.4 to 3.2.5 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-javadoc-plugin from 3.7.0 to 3.10.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-javadoc-plugin from 3.6.3 to 3.7.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-plugin-plugin from 3.12.0 to 3.13.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-plugin-plugin from 3.13.0 to 3.13.1 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-plugin-plugin from 3.13.1 to 3.15.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-surefire-plugin from 3.2.5 to 3.3.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-surefire-plugin from 3.3.0 to 3.3.1 (@marwin1991)
- Upgraded org.assertj:assertj-core from 3.25.3 to 3.26.0 (@marwin1991)
- Upgraded org.assertj:assertj-core from 3.26.0 to 3.26.3 (@marwin1991)
- Upgraded org.mockito:mockito-junit-jupiter from 5.11.0 to 5.12.0 (@marwin1991)
- Upgraded org.pitest:pitest-maven from 1.15.8 to 1.16.0 (@marwin1991)
- Upgraded org.pitest:pitest-maven from 1.16.0 to 1.16.1 (@marwin1991)
- Upgraded org.projectlombok:lombok from 1.18.32 to 1.18.34 (@marwin1991)
- Upgraded org.xmlunit:xmlunit-assertj3 from 2.9.1 to 2.10.0 (@marwin1991)
- Upgraded renovatebot/github-action from v40.1.7 to v40.1.10 (@marwin1991)
- Upgraded renovatebot/github-action from v40.1.10 to v40.1.11 (@marwin1991)
- Upgraded renovatebot/github-action from v40.1.11 to v40.1.12 (@marwin1991)
- Upgraded renovatebot/github-action from v40.1.12 to v40.2.2 (@marwin1991)
- Upgraded renovatebot/github-action from v40.2.2 to v40.2.8 (@marwin1991)


[1.5.0] - 2024-04-06
--------------------

### Changed (1 change)

- Migrated from Java-Markdown-Generator to own classes in logchange-md !242 #231 ([Matthew](https://github.com/witx98) @witx98)

### Fixed (1 change)

- Added YML validation before release, so when there is YML syntax error, nothing is intact !258 #201 #139 #102 (@marwin1991)

### Dependency updates (24 changes)

- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.16.1 to 2.16.2 (@marwin1991)
- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.16.2 to 2.17.0 (@marwin1991)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.16.1 to 2.16.2 (@marwin1991)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.16.2 to 2.17.0 (@marwin1991)
- Upgraded commons-io:commons-io from 2.15.1 to 2.16.0 (@marwin1991)
- Upgraded org.apache.maven.plugin-tools:maven-plugin-annotations from 3.10.2 to 3.11.0 (@marwin1991)
- Upgraded org.apache.maven.plugin-tools:maven-plugin-annotations from 3.11.0 to 3.12.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-gpg-plugin from 3.1.0 to 3.2.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-gpg-plugin from 3.2.0 to 3.2.1 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-gpg-plugin from 3.2.1 to 3.2.2 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-plugin-plugin from 3.10.2 to 3.11.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-plugin-plugin from 3.11.0 to 3.12.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-source-plugin from 3.3.0 to 3.3.1 (@marwin1991)
- Upgraded org.assertj:assertj-core from 3.24.2 to 3.25.3 (@marwin1991)
- Upgraded org.codehaus.plexus:plexus-interactivity-api from 1.1 to 1.3 (@marwin1991)
- Upgraded org.pitest:pitest-maven from 1.15.3 to 1.15.8 (@marwin1991)
- Upgraded org.projectlombok:lombok from 1.18.30 to 1.18.32 (@marwin1991)
- Upgraded renovatebot/github-action from v39.2.4 to v40.1.2 (@marwin1991)
- Upgraded renovatebot/github-action from v40.1.2 to v40.1.3 (@marwin1991)
- Upgraded renovatebot/github-action from v40.1.3 to v40.1.4 (@marwin1991)
- Upgraded renovatebot/github-action from v40.1.4 to v40.1.5 (@marwin1991)
- Upgraded renovatebot/github-action from v40.1.5 to v40.1.6 (@marwin1991)
- Upgraded renovatebot/github-action from v40.1.6 to v40.1.7 (@marwin1991)
- Upgraded softprops/action-gh-release from v1 to v2 (@marwin1991)


[1.4.0] - 2024-03-02
--------------------

### Fixed (1 change)

- Resolved problem with translating update property in configurations section !230 #224 ([Matthew](https://github.com/witx98) @witx98)

### Dependency updates (36 changes)

- Upgraded actions/setup-java from v3 to v4 (@marwin1991)
- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.15.2 to 2.15.3 (@marwin1991)
- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.15.3 to 2.16.0 (@marwin1991)
- Upgraded com.fasterxml.jackson.core:jackson-databind from 2.16.0 to 2.16.1 (@marwin1991)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.15.2 to 2.15.3 (@marwin1991)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.15.3 to 2.16.0 (@marwin1991)
- Upgraded com.fasterxml.jackson.dataformat:jackson-dataformat-yaml from 2.16.0 to 2.16.1 (@marwin1991)
- Upgraded org.apache.commons:commons-text from 1.10.0 to 1.11.0 (@marwin1991)
- Upgraded org.apache.maven:maven-artifact from 3.9.4 to 3.9.5 (@marwin1991)
- Upgraded org.apache.maven:maven-artifact from 3.9.5 to 3.9.6 (@marwin1991)
- Upgraded org.apache.maven:maven-core from 3.9.4 to 3.9.5 (@marwin1991)
- Upgraded org.apache.maven:maven-core from 3.9.5 to 3.9.6 (@marwin1991)
- Upgraded org.apache.maven:maven-plugin-api from 3.9.4 to 3.9.5 (@marwin1991)
- Upgraded org.apache.maven:maven-plugin-api from 3.9.5 to 3.9.6 (@marwin1991)
- Upgraded org.apache.maven.plugin-tools:maven-plugin-annotations from 3.9.0 to 3.10.2 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-failsafe-plugin from 3.1.2 to 3.2.2 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-failsafe-plugin from 3.2.2 to 3.2.5 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-javadoc-plugin from 3.5.0 to 3.6.0 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-javadoc-plugin from 3.6.0 to 3.6.2 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-javadoc-plugin from 3.6.2 to 3.6.3 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-plugin-plugin from 3.9.0 to 3.10.1 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-plugin-plugin from 3.10.1 to 3.10.2 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-surefire-plugin from 3.1.2 to 3.2.2 (@marwin1991)
- Upgraded org.apache.maven.plugins:maven-surefire-plugin from 3.2.2 to 3.2.5 (@marwin1991)
- Upgraded org.mockito:mockito-junit-jupiter from 5.4.0 to 5.11.0 (@marwin1991)
- Upgraded org.pitest:pitest-junit5-plugin from 1.2.0 to 1.2.1 (@marwin1991)
- Upgraded org.pitest:pitest-maven from 1.14.4 to 1.15.0 (@marwin1991)
- Upgraded org.pitest:pitest-maven from 1.15.0 to 1.15.1 (@marwin1991)
- Upgraded org.pitest:pitest-maven from 1.15.1 to 1.15.3 (@marwin1991)
- Upgraded org.projectlombok:lombok from 1.18.28 to 1.18.30 (@marwin1991)
- Upgraded qcastel/github-actions-maven-release from v1.12.39 to v1.12.41 (@marwin1991)
- Upgraded renovatebot/github-action from v39.0.5 to v39.1.0 (@marwin1991)
- Upgraded renovatebot/github-action from v39.1.0 to v39.1.2 (@marwin1991)
- Upgraded renovatebot/github-action from v39.1.2 to v39.1.3 (@marwin1991)
- Upgraded renovatebot/github-action from v39.1.3 to v39.2.3 (@marwin1991)
- Upgraded renovatebot/github-action from v39.2.3 to v39.2.4 (@marwin1991)


[1.3.0] - 2023-09-14
--------------------

### Dependency updates (14 changes)

- Upgraded actions/checkout from v3 to v4 (@marwin1991)
- Upgraded org.apache.commons:commons-text from 1.9 to 1.10.0 (@marwin1991)
- Upgraded org.apache.maven:maven-artifact from 3.9.3 to 3.9.4 (@marwin1991)
- Upgraded org.apache.maven:maven-core from 3.9.3 to 3.9.4 (@marwin1991)
- Upgraded org.apache.maven:maven-plugin-api from 3.9.2 to 3.9.3 (@marwin1991)
- Upgraded org.apache.maven:maven-plugin-api from 3.9.3 to 3.9.4 (@marwin1991)
- Upgraded org.apache.maven.plugin-tools:maven-plugin-annotations from 3.8.2 to 3.9.0 (@marwin1991)
- Upgraded org.pitest:pitest-maven from 1.14.2 to 1.14.3 (@marwin1991)
- Upgraded org.pitest:pitest-maven from 1.14.3 to 1.14.4 (@marwin1991)
- Upgraded org.projectlombok:lombok from 1.18.26 to 1.18.28 (@marwin1991)
- Upgraded renovatebot/github-action from v38.1.11 to v39.0.0 (@marwin1991)
- Upgraded renovatebot/github-action from v39.0.0 to v39.0.1 (@marwin1991)
- Upgraded renovatebot/github-action from v39.0.1 to v39.0.5 (@marwin1991)
- Migrated from org.yaml:snakeyaml 1.33 to jackson-dataformat-yaml 2.15.2 (@marwin1991)


[1.2.0] - 2023-07-09
--------------------

### Dependency updates (3 changes)

- Upgraded org.apache.maven:maven-artifact from 3.9.2 to 3.9.3 (@marwin1991)
- Upgraded org.apache.maven:maven-core from 3.9.2 to 3.9.3 (@marwin1991)
- Upgraded org.assertj:assertj-core from 3.24.1 to 3.24.2 (@marwin1991)


[1.1.0] - 2023-07-08
--------------------

### Added (1 change)

- Added -DbatchMode to add command to allow using this command as one line instead of prompt ([Peter](https://github.com/marwin1991) @marwin1991)


[1.0.0] - 2025-08-30
--------------------

### Added (2 changes)

- Added `dependency_update` changelog entry type. ([Peter](https://github.com/marwin1991) @marwin1991)
- Added support for generating Maven's Changes.xml !126 #103 ([LINK](https://github.com/mzaninovic555) @mzaninovic555)


[0.8.0] - 2022-11-12
--------------------

### Changed (1 change)

- Changelog heading from logchange-config.yml instead of maven properties. !73 ([Peter](https://github.com/marwin1991) @marwin1991)

### Fixed (2 changes)

- Fixed typo ued to used in entry yml heading comment !70 #54 ([Peter](https://github.com/marwin1991) @marwin1991)
- Fixed duplicated config entries, when more than one type specified, special thanks to @Riserax !68 #64 ([Peter](https://github.com/marwin1991) @marwin1991)


[0.7.0] - 2022-09-24
--------------------

### Added (2 changes)

- Added info to generated yml entries file with link to documentation. !39 #33 ([Peter](https://github.com/marwin1991) @marwin1991)
- Added info to generated CHANGELOG.md to not modify it. !40 #32 ([Peter](https://github.com/marwin1991) @marwin1991)

### Changed (1 change)

- When initialize project, create default `logchange-config.yml` to encourage usage. !34 #26 ([Peter](https://github.com/marwin1991) @marwin1991)

### Fixed (2 changes)

- Fix releasing new version, when release used, generated files was created without config. !37 #36 ([Peter](https://github.com/marwin1991) @marwin1991)
- Fixed error when version name without SNAPSHOT or RC and releasing version. !38 #35 ([Peter](https://github.com/marwin1991) @marwin1991)


[0.6.1] - 2022-08-22
--------------------

### Fixed (1 change)

- Fixed problem with 'release' maven command that was failing when `logchange-config.yml` missing. !29 #28 ([Peter](https://github.com/marwin1991) @marwin1991)


[0.6.0] - 2022-08-21
--------------------

### Added (1 change)

- Added ability to configure CHANGELOG labels via `logchange-config.yml`. ([Peter](https://github.com/marwin1991) @marwin1991)

### Fixed (3 changes)

- Added other changelog entry type. ([Peter](https://github.com/marwin1991) @marwin1991)
- Added space between configuration type and key. ([Peter](https://github.com/marwin1991) @marwin1991)
- Fixed problem with encoding f.e. Polish charset, set save encoding to UTF-8. ([Peter](https://github.com/marwin1991) @marwin1991)


[0.5.0] - 2022-07-31
--------------------

### Added (1 change)

- Readded generation of version-summary.md in every version's directory during CHANGELOG.md ([LINK](https://github.com/marwin1991) @marwin1991)

### Fixed (1 change)

- Fixed releasing new version of this project. ([Peter](https://github.com/marwin1991) @marwin1991)


[0.4.0] - 2022-07-26
--------------------

### Added (3 changes)

- At the begging of `CHANGELOG.md` added comment which disables auto format in JetBrains IDEs. ([Peter](https://github.com/marwin1991) @marwin1991)
- At the begging of `CHANGELOG.md` added noinspection tag and also added it and formatteroff to `version-summar.md`. ([Peter](https://github.com/marwin1991) @marwin1991)
- Added `add` plugin command to generate empty (when using -Dempty flag) or ask user for input, changelog entry. ([Peter](https://github.com/marwin1991) @marwin1991)

### Changed (3 changes)

- Changed YAML keyword `configuration` to `configurations` ([Peter](https://github.com/marwin1991) @marwin1991)
- Changed the `merge_request` to `merge_requests` to allow using list of numbers instead of just a one number. ([Peter](https://github.com/marwin1991) @marwin1991)
- Migrated project to new GitHub repository. ([Peter](https://github.com/marwin1991) @marwin1991)


[0.3.0] - 2021-04-03
--------------------

### Added (1 change)

- Added generation of version-summary.md in every version's directory during CHANGELOG.md ([LINK](https://github.com/marwin1991) @marwin1991)


[0.2.0] - 2021-04-01
--------------------

### Added (3 changes)

- Added example directory witch contains simple springboot project with `keep-changelog` plugin ([Peter](https://github.com/marwin1991) @marwin1991)
- Added maven command to initialize project with changelog/unreleased directory and empty CHANGELOG.md !3 ([LINK](https://github.com/Glukasze) @Glukasze)
- Added maven command to tag unreleased version of CHANGELOG.md ([Peter](https://github.com/marwin1991) @marwin1991)


[0.1.0] - 2021-03-17
--------------------

### Added (1 change)

- Added 'generate' maven command that generates `CHANGELOG.md` from YAML files ([Peter](https://github.com/marwin1991) @marwin1991)



