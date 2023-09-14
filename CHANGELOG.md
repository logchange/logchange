<!-- @formatter:off -->
<!-- noinspection -->
<!-- Prevents auto format, for JetBrains IDE File > Settings > Editor > Code Style (Formatter Tab) > Turn formatter on/off with markers in code comments  -->

<!-- This file is automatically generate by logchange tool 🌳 🪓 => 🪵 -->
<!-- Visit https://github.com/logchange/logchange and leave a star 🌟 -->
<!-- !!! ⚠️ DO NOT MODIFY THIS FILE, YOUR CHANGES WILL BE LOST ⚠️ !!! -->


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



