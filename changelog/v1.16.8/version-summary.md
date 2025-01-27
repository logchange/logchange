<!-- @formatter:off -->
<!-- noinspection -->
<!-- Prevents auto format, for JetBrains IDE File > Settings > Editor > Code Style (Formatter Tab) > Turn formatter on/off with markers in code comments  -->

<!-- This file is automatically generate by logchange tool 🌳 🪓 => 🪵 -->
<!-- Visit https://github.com/logchange/logchange and leave a star 🌟 -->
<!-- !!! ⚠️ DO NOT MODIFY THIS FILE, YOUR CHANGES WILL BE LOST ⚠️ !!! -->


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


