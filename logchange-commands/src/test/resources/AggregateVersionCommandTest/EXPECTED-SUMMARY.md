<!-- @formatter:off -->
<!-- noinspection -->
<!-- Prevents auto format, for JetBrains IDE File > Settings > Editor > Code Style (Formatter Tab) > Turn formatter on/off with markers in code comments  -->

<!-- This file is automatically generate by logchange tool 🌳 🪓 => 🪵 -->
<!-- Visit https://github.com/logchange/logchange and leave a star 🌟 -->
<!-- !!! ⚠️ DO NOT MODIFY THIS FILE, YOUR CHANGES WILL BE LOST ⚠️ !!! -->


[0.4.0] - 2023-02-20
--------------------

### Added (7 changes)

- **hofund** - Added base Grafana dashboard to `grafana-dashboard` directory. ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)
- **hofund** - Added git based information to metrics in `hofund_git_info` !12 ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)
- **hofund** - Added yaml based examples in readme !13 #9 ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)
- **hofund** - Added your custom connection by extending `AbstractHofundBasicHttpConnection` see README for more details. ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991)
- **logchange** - At the begging of `CHANGELOG.md` added comment which disables auto format in JetBrains IDEs. ([Peter](https://github.com/marwin1991) @marwin1991)
- **logchange** - At the begging of `CHANGELOG.md` added noinspection tag and also added it and formatteroff to `version-summar.md`. ([Peter](https://github.com/marwin1991) @marwin1991)
- **logchange** - Added `add` plugin command to generate empty (when using -Dempty flag) or ask user for input, changelog entry. ([Peter](https://github.com/marwin1991) @marwin1991)

### Changed (3 changes)

- **logchange** - Changed YAML keyword `configuration` to `configurations` ([Peter](https://github.com/marwin1991) @marwin1991)
- **logchange** - Changed the `merge_request` to `merge_requests` to allow using list of numbers instead of just a one number. ([Peter](https://github.com/marwin1991) @marwin1991)
- **logchange** - Migrated project to new GitHub repository. ([Peter](https://github.com/marwin1991) @marwin1991)

### Fixed (1 change)

- **hofund** - Spring Boot 3 compatibility !14 [source](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0.0-M5-Release-Notes#auto-configuration-registration) ([Peter Zmilczak](https://github.com/marwin1991) @marwin1991) (@jfoder [LINK](https://github.com/jfoder))

### Configuration changes

| Type: application.properties                                                                                                                                                                                                      |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| <ul><li>hofund</li><li>Added `hofund.git-info.branch` with default value: ``</li><li>Description: Short git commit id, set it to `@git.branch@` and configure `git-commit-id-plugin` see README.md.</li></ul>                     |
| <ul><li>hofund</li><li>Added `hofund.git-info.build.host` with default value: ``</li><li>Description: Short git commit id, set it to `@git.build.host@` and configure `git-commit-id-plugin` see README.md.</li></ul>             |
| <ul><li>hofund</li><li>Added `hofund.git-info.build.time` with default value: ``</li><li>Description: Short git commit id, set it to `@git.build.time@` and configure `git-commit-id-plugin` see README.md.</li></ul>             |
| <ul><li>hofund</li><li>Added `hofund.git-info.commit.id` with default value: ``</li><li>Description: Git commit id, set it to `@git.commit.id@` and configure `git-commit-id-plugin`, see README.md.</li></ul>                    |
| <ul><li>hofund</li><li>Added `hofund.git-info.commit.id-abbrev` with default value: ``</li><li>Description: Short git commit id, set it to `@git.commit.id.abbrev@` and configure `git-commit-id-plugin` see README.md.</li></ul> |
| <ul><li>hofund</li><li>Added `hofund.git-info.dirty` with default value: ``</li><li>Description: Short git commit id, set it to `@git.dirty@` and configure `git-commit-id-plugin` see README.md.</li></ul>                       |


