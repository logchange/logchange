<!-- @formatter:off -->
<!-- noinspection -->
<!-- Prevents auto format, for JetBrains IDE File > Settings > Editor > Code Style (Formatter Tab) > Turn formatter on/off with markers in code comments  -->

<!-- This file is automatically generate by logchange tool 🌳 🪓 => 🪵 -->
<!-- Visit https://github.com/logchange/logchange and leave a star 🌟 -->
<!-- !!! ⚠️ DO NOT MODIFY THIS FILE, YOUR CHANGES WILL BE LOST ⚠️ !!! -->


[1.12.0] - 2024-11-21
---------------------

### Added (1 change)

- Added ability to configure the entry format in the Changelog.md file. !351 #338 ([Mateusz Witkowski](https://github.com/witx98) @witx98)

### Fixed (1 change)

- Replaced specific ValueInstantiationException handling with a generic Exception catch to streamline error management during YMLChangelogEntry parsing. !343 (@witx98 [LINK](https://github.com/witx98))

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

- Introduced functionality for generating an aggregated changelog version summary from multiple projects. !335 (@witx98 [LINK](https://github.com/witx98))

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

- Enabled the option to re-release already released version. !328 #316 (@witx98 [LINK](https://github.com/witx98))


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


[1.0.0] - 2023-05-24
--------------------

### Added (2 changes)

- Added `dependency_update` changelog entry type. ([Peter](https://github.com/marwin1991) @marwin1991)
- Added support for generating Maven's Changes.xml !126 #103 (@mzaninovic555 [LINK](https://github.com/mzaninovic555))


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

- Readded generation of version-summary.md in every version's directory during CHANGELOG.md (@marwin1991 [LINK](https://github.com/marwin1991))

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

- Added generation of version-summary.md in every version's directory during CHANGELOG.md (@marwin1991 [LINK](https://github.com/marwin1991))


[0.2.0] - 2021-04-01
--------------------

### Added (3 changes)

- Added example directory witch contains simple springboot project with `keep-changelog` plugin ([Peter](https://github.com/marwin1991) @marwin1991)
- Added maven command to initialize project with changelog/unreleased directory and empty CHANGELOG.md !3 (@Glukasze [LINK](https://github.com/Glukasze))
- Added maven command to tag unreleased version of CHANGELOG.md ([Peter](https://github.com/marwin1991) @marwin1991)


[0.1.0] - 2021-03-17
--------------------

### Added (1 change)

- Added 'generate' maven command that generates `CHANGELOG.md` from YAML files ([Peter](https://github.com/marwin1991) @marwin1991)



