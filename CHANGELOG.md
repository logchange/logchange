<!-- @formatter:off -->
<!-- noinspection -->
<!-- Prevents auto format, for JetBrains IDE File > Settings > Editor > Code Style (Formatter Tab) > Turn formatter on/off with markers in code comments  -->

[0.5.0] - 2022-07-27
--------------------

### Added (1 change)

- Readded generation of version-summary.md in every version's directory during CHANGELOG.md (@marwin1991 [LINK](https://github.com/marwin1991))

### Fixed (1 change)

- Fixed releasing new version of this project. ([Piotr](https://github.com/marwin1991) @marwin1991)


[0.4.0] - 2022-07-26
--------------------

### Added (3 changes)

- At the begging of `CHANGELOG.md` added comment which disables auto format in JetBrains IDEs. ([Piotr](https://github.com/marwin1991) @marwin1991)
- Added `add` plugin command to generate empty (when using -Dempty flag) or ask user for input, changelog entry. ([Piotr](https://github.com/marwin1991) @marwin1991)
- At the begging of `CHANGELOG.md` added noinspection tag and also added it and formatteroff to `version-summar.md`. ([Piotr](https://github.com/marwin1991) @marwin1991)

### Changed (3 changes)

- Changed the `merge_request` to `merge_requests` to allow using list of numbers instead of just a one number. ([Piotr](https://github.com/marwin1991) @marwin1991)
- Migrated project to new GitHub repository. ([Piotr](https://github.com/marwin1991) @marwin1991)
- Changed YAML keyword `configuration` to `configurations` ([Piotr](https://github.com/marwin1991) @marwin1991)


[0.3.0] - 2021-04-03
--------------------

### Added (1 change)

- Added generation of version-summary.md in every version's directory during CHANGELOG.md (@marwin1991 [LINK](https://github.com/marwin1991))


[0.2.0] - 2021-04-01
--------------------

### Added (3 changes)

- Added maven command to initialize project with changelog/unreleased directory and empty CHANGELOG.md !3 (@Glukasze [LINK](https://github.com/Glukasze))
- Added maven command to tag unreleased version of CHANGELOG.md ([Piotr](https://github.com/marwin1991) @marwin1991)
- Added example directory witch contains simple springboot project with `keep-changelog` plugin ([Piotr](https://github.com/marwin1991) @marwin1991)


[0.1.0] - 2021-03-17
--------------------

### Added (1 change)

- Added 'generate' maven command that generates `CHANGELOG.md` from YAML files ([Piotr](https://github.com/marwin1991) @marwin1991)



