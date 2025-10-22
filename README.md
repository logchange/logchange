<p align="center">
  <img src="https://user-images.githubusercontent.com/25181517/138590008-f98457b3-602a-4af5-9b28-0c499fe7e378.png" />
</p>

<p align="center">
    <a href="https://github.com/logchange/logchange/graphs/contributors">
        <img src="https://img.shields.io/github/contributors/logchange/logchange" alt="Contributors"/></a>
    <a href="https://github.com/logchange/logchange/pulse">
        <img src="https://img.shields.io/github/commit-activity/m/logchange/logchange" alt="Activity"/></a>
    <a href="https://hub.docker.com/repository/docker/logchange/logchange/">
        <img src="https://img.shields.io/docker/v/logchange/logchange?sort=semver&color=green&label=DockerHub" alt="DockerHub"/></a>
    <a href="https://hub.docker.com/repository/docker/logchange/logchange/">
        <img src="https://img.shields.io/docker/pulls/logchange/logchange" alt="DockerHub Pulls"/></a>
    <a href="https://central.sonatype.com/artifact/dev.logchange/logchange-maven-plugin">
        <img src="https://img.shields.io/maven-central/v/dev.logchange/logchange-maven-plugin.svg?label=Maven%20Central" alt="Maven Central"/></a>
    <a href="https://codecov.io/gh/logchange/logchange">
        <img src="https://codecov.io/gh/logchange/logchange/graph/badge.svg?token=SP3V6ZQ039" alt="codecov"/></a>
</p>

🌳 `CHANGELOG.md` is one of the most important files in a repository. It allows others to find out about
the most important changes in the project in short time.
To achieve this, `CHANGELOG.md` should be created be in accordance with the rules, however there is no one
standard agreed by community.

🪓 Another big problem with `CHANGELOG.md` is a problem with merge conflicts. Probably you, as a developer, also
encounter it when someone merged changes to `CHANGELOG.md` before you.

🌲 When you create new merge/pull request and in the meantime you will release version, with old-fashioned `CHANGELOG.md`
you have to remember, to move new changelog's entry up, to the new version section. With this tool you don't have to!

🪓 If your PO is against you to use this tool in your projects, don't worry... just ask him to do `CHANGELOG.md` by
himself, and he will be the first one, who will ask you to use this tool. 🔥

**🪵 To solve these problems, this project was created. It allows to keep a changelog style and reduce merge request
conflicts by keeping every change in a separate YAML file and generate `CHANGELOG.md` during release.**

📜 This tool is also helpful to create reliable release notes during your release process.

### Reference

The convention is maintained according to the principles set out in
the [Keep a Changelog](https://keepachangelog.com/en/1.0.0/) and with some additions as a result from experience in
developing various types of projects.

The same problem with merge conflicts with `CHANGELOG.md` was described by
GitLab. [LINK](https://about.gitlab.com/blog/2018/07/03/solving-gitlabs-changelog-conflict-crisis/)

## Usage and installation

<div align="center">
  <img src="https://github.com/logchange/logchange/raw/main/logchange.gif" />
</div>

### logchange is distributed as:

- [**CLI** (binary)](https://github.com/logchange/logchange?tab=readme-ov-file#logchange-cli) - you can use it
  regardless of the technology used in the project. Also, available as:
    - [logchange homebrew formula][] available on Linux and macOS
    - [logchange docker image][] suited for CI/CD
- [**Maven Plugin**](https://github.com/logchange/logchange?tab=readme-ov-file#maven-plugin) - dedicated to projects
  based on the [Maven][] tool
- [**Gradle Plugin**](https://github.com/logchange/logchange?tab=readme-ov-file#gradle-plugin) - dedicated to projects
  based on the [Gradle][] tool

### Maven Plugin

### Starting

You can choose between two options:

1. Add a plugin to your `pom.xml`. For multi-module projects with one `CHANGELOG.md` add it in main `pom.xml`.

```xml

<build>
    <plugins>
        <plugin>
            <groupId>dev.logchange</groupId>
            <artifactId>logchange-maven-plugin</artifactId>
            <version>1.19.9</version>
        </plugin>
    </plugins>
</build>
```

2. You don't have to add anything to your `pom.xml`, just use all commands with `groupId` and full plugin name, like
   here:

```shell
mvn dev.logchange:logchange-maven-plugin:init 
```

After choosing one of the options, how you prefer run this plugin, you can start using plugin as follows. Use this
command from your terminal to create important directories and empty CHANGELOG.md

```shell
mvn logchange:init 
```

If you already had a `CHANGELOG.md` file you can move it to `changelog/archive.md` file. The name of the archive file
have to start from `archive` phrase (f.e. `archive-1.0.0.md`).

After using `init` command (or just creating `changelog/unreleased` directory) your project is ready, and you can start
adding new changelog entries by creating YAML files.

**⚠️IMPORTANT:**
If you’re developing multiple versions simultaneously (whether on the same branch or separate
branches that you merge, cherry-pick, etc.), consider creating dedicated `unreleased` directories
for each version (e.g., `unreleased-1.3.7`). When you release a new version (say, `1.3.7`),
**logchange** will look for `unreleased-1.3.7` first and release it as `v1.3.7`.
If this folder doesn’t exist, **logchange** will fall back to looking
for default `unreleased` directory (ps. `unreleased` can be changed by setting property `unreleasedVersionDir`).

Also, if you have two active main branches (e.g., `1.1.X` and `1.2.X`) but only a single
`unreleased` folder, do **not** merge branch `1.1.X` into `1.2.X` before releasing.
Otherwise, all YAML files from both versions will wind up in the same directory, resulting in a single,
combined release. Use dedicated `unreleased` directories.

### Adding new change

Adding new change is really simple, you can just add new YAML file to `changelog/unreleased` directory and keep format
presented below, or you can use command to generate this file, this one will guide you through whole process:

```shell
mvn logchange:add
```

On the other hand, if you would like to just generate an empty file with some default strings, you can use:

```shell
mvn logchange:add -Dempty -DfileName=000001-some-name.yml
```

You can also run the following command to generate an example entry with pre-filled properties,
which you can then modify as needed (don't forget to change the file name!):

```shell
mvn logchange:example
```

### YAML format

```yml
# This file is used by logchange tool to generate CHANGELOG.md 🌳 🪓 => 🪵
# Visit https://github.com/logchange/logchange and leave a star 🌟
# More info about configuration you can find https://github.com/logchange/logchange#yaml-format ⬅️
title: Test title
authors:
  - name: Peter
    nick: marwin1991
    url: https://github.com/marwin1991
merge_requests:
  - 1
issues:
  - 1
links: # links to different project's issue or external systems, like jira or redmine
  - name: TASK_NUMBER
    url: https://www.google.pl
type: fixed # Default: [added/changed/deprecated/removed/fixed/security/dependency_update/other] It can be overridden in logchange-config.yml
important_notes:
  - Update style.css on server during instalation 1
configurations: # information about changes in available application configuration
  - type: database parameter
    action: add # [add/update/delete]
    key: simpleparam.name.enabled
    default_value: true
    description: Enables displying feature name
    more_info: "You can put more information here, any text f.e !1 #1, even [link test](https://google.com)" #if you want to use # sign, yaml value must be a string inside ""
```

|            Keyword             | Description                                                                                                                                         |
|:------------------------------:|-----------------------------------------------------------------------------------------------------------------------------------------------------|
|            `title`             | Description of change that has been made.                                                                                                           |
|           `authors`            | List of change's authors.                                                                                                                           |
|           `modules`            | List of modules affected by change.                                                                                                                 | 
|        `merge_requests`        | The merge requests' numbers which are adding this change.                                                                                           | 
|            `issues`            | List of issues associated with this change.                                                                                                         |
|             `type`             | Type of the change. Default: `added/changed/deprecated/removed/fixed/security/dependency_update/other` It can be overridden in logchange-config.yml |
|            `links`             | Any link to other project or external tool witch contains information about this change.                                                            |
|       `important_notes`        | Important notes, that has to be included in release.                                                                                                |
|        `configurations`        | Configurations changes important during release.                                                                                                    |
|     `configurations.type`      | Type of configuration method, f.e via file application.properties or system environment's variables                                                 |
|    `configurations.action`     | Define if configuration method was added (add) / updated (update) / deleted (delete)                                                                |
|      `configurations.key`      | A key for configuration property                                                                                                                    |
| `configurations.default_value` | Default value for configuration property                                                                                                            |
|  `configurations.description`  | Description of configuration property                                                                                                               |
|   `configurations.more_info`   | Here you can add anything even links (keep markdown format)                                                                                         |

### Example usage

- [this project](https://github.com/logchange/logchange/blob/main/CHANGELOG.md)
- [integration test](https://github.com/logchange/logchange/blob/main/logchange-core/src/test/resources/GenerateChangelogIntegrationTest/EXPECTED_CHANGELOG.md)
- [hofund project](https://github.com/logchange/hofund/blob/master/CHANGELOG.md)

### Configuration

You can configure this plugin via `logchange-config.yml` file in `changelog` directory.

Copy following section as default config.

```yml
# This file configures logchange tool 🌳 🪓 => 🪵 
# Visit https://github.com/logchange/logchange and leave a star 🌟 
# More info about configuration you can find https://github.com/logchange/logchange#configuration 
changelog:
  heading: Some information that will be display in the top of CHANGELOG.md
  #  You can define custom entry types, which overriders default ones
  #  [added/changed/deprecated/removed/fixed/security/dependency_update/other]
  #  Remember to adjust labels below, to match new entry types f.e. add: New features
  #  entryTypes: # you can define custom entry types
  #    - key: add
  #      order: 1
  #    - key: fix
  #      order: 2
  #    - key: change
  #      order: 3
  #    - key: other
  #      order: 4
  labels:
    unreleased: unreleased
    important_notes: Important notes
    types:
      entryTypesLabels:
        security: Security
        other: Other
        removed: Removed
        added: Added
        deprecated: Deprecated
        fixed: Fixed
        dependency_update: Dependency updates
        changed: Changed
      number_of_changes:
        singular: change
        plural: changes
      security: Security
      other: Other
      removed: Removed
      added: Added
      deprecated: Deprecated
      fixed: Fixed
      dependency_update: Dependency updates
      changed: Changed
    configuration:
      heading: Configuration changes
      type: Type
      actions:
        add: Added
        update: Updated
        delete: Deleted
      with_default_value: with default value
      description: Description
  templates:
    entry: "${prefix}${title} ${merge_requests} ${issues} ${links} ${authors}"
    author: "([${name}](${url}) @${nick})"
    # Check out documentation for templates: https://logchange.dev/tools/logchange/templates/
    # see examples of templates at examples/templates
    # if you are missing some function, which will simplify your template (f.e getNumberOfEntries()) 
    # feel free to create issue or pull request with change
    version_summary_templates:
      # Relative path to the changelog/.templates directory.
      # Following definition will require from you existence of template at 
      # changelog/.templates/my-version-summary.html
      # It will create my-version-summary.html in every version directory 
      # changelog/vX.X.X/my-version-summary.html
      # HOW TO CREATE VERSION SUMMARY TEMPLATE?
      # Main object is version and its type of https://github.com/logchange/logchange/blob/main/logchange-core/src/main/java/dev/logchange/core/domain/changelog/model/version/ChangelogVersion.java
      - path: my-version-summary.html
    changelog_templates:
      # Relative path to the changelog/.templates directory.
      # Following definition will require from you existence of template at 
      # changelog/.templates/my-changelog.html
      # It will generate a single file at the repository root (next to CHANGELOG.md):
      # ./my-changelog.html
      # HOW TO CREATE CHANGELOG TEMPLATE?
      # Main object is changelog and its type of https://github.com/logchange/logchange/blob/main/logchange-core/src/main/java/dev/logchange/core/domain/changelog/model/Changelog.java
      - path: my-changelog.html
#=======================================================================================================================
# this section only makes sense when project is root of aggregation for other projects with changelogs 
# f.e. if we have repositories: mobile-app(root), mobile-app-android, mobile-app-ios 
# and in mobile-app we want to generate changelog with entries from also from mobile-app-android, mobile-app-ios
# but mobile-app-android, mobile-app-ios have to have already released versions that we want to aggregate
aggregates:
  projects:
    - name: hofund
      url: https://github.com/logchange/hofund/archive/refs/heads/master.tar.gz
      type: tar.gz # currently only tar.gz is supported
      input_dir: changelog # the name where dirs with versions are stored for this project
    - name: logchange
      url: https://github.com/logchange/logchange/archive/refs/heads/main.tar.gz
      type: tar.gz
      input_dir: changelog
```

### Templates

The `templates` section in the `logchange-config.yml` configuration file allows you to customize the format of entries
in the `changelog.md` file. Individual elements of an entry can include:

- `${prefix}` => `**Project_name** -` (used only with the `aggregate` command and specifies the project name from which
  the entry was pulled)
- `${title}` => `Description of the change`
- `${merge_requests}` => `!1000`
- `${issues}` => `#1234`
- `${links}` => `[logchange](https://github.com/logchange/logchange)`
- `${authors}` => `([Logchange team](https://github.com/orgs/logchange/teams/logchange-main-team) @logchange-main-team)`

### Generating `CHANGELOG.md`

Everytime you want to generate `CHANGELOG.md` you can use command:

```shell
mvn logchange:generate
```

To prevent merge conflicts (it is idea of this project) generation of `CHANGELOG.md` should be done with use of CI/CD
Tool on a main
branch of a project during release.

### Creating release (from unreleased directory)

This command is the preferred to create release and generates `CHANGELOG.md` (One of these command steps is to call the
generate command).
During release all files from `unreleased` directory are moved to `v.X.X.X` where X.X.X is the version from `pom.xml`.
After moving files, command create file `release-date.txt` with current date, then generation of `CHANGELOG.md` and
versions summaries is done.

```shell
mvn logchange:release
```

### Entries validation

The lint command is used to verify the existence of the `changelog` directory and to check that all `.yml` files within
are syntactically correct.
This command is designed for the early detection of potential issues, particularly in a continuous integration
environment,
allowing developers to address problems before attempting to generate a changelog.

```shell
mvn logchange:lint
```

### Generating

`changes.xml` for [maven-changes-plugin](https://maven.apache.org/plugins/maven-changes-plugin/index.html)

Along with `CHANGELOG.md` there is also the option of generating Maven's `changes.xml`.
To enable the option, add the parameter `ChangesXml`. To specify a different name, also add the
parameter `outputFileXml`.
At the moment archives are not supported, rather `changes.xml` will only contain the releases generated by this plugin.

```shell
mvn logchange:release -DchangesXml
```

For custom file name, add `-DoutputFileXml=CustomName.xml`

```shell
mvn logchange:release -DchangesXml -DoutputFileXml=CustomName.xml
```

### `CHANGELOG.md` structure overview

TODO

### Versions summaries

During release `version-summary.md` is created, it is a file that contains current version changelog entries.
You can see example [**here**](https://github.com/logchange/logchange/blob/main/changelog/v0.4.0/version-summary.md).

This feature is really useful during creating releases on GitHub or GitLab, you can just attach the content of this file
as release notes.

### Modules

Some project are big enough that have separate subsystems. To mark change that affect particular subsystem you can use
`module` property of changelog.

During changelog generation, changes with same module would be aggregated to same area for better readability even 
if they are created in separate entries.

If entry is marked with more than one module, it would appear on list multiple times, with each module aggregation.

### Archives

If your project already contains some `CHANGELOG.md` you don't have to rewrite it to `yml`. Only what have to do is to
move it to `changelog` directory and rename it to `archive.md` or `archiveXXXX.md` where XXXX is anything you want f.e.
`-2.0.0`.

**⚠️⚠️ IMPORTANT ⚠️⚠️** `archive-XXX.md` **has to be** in `changelog` directory (`changelog/archive-1.3.6.md`) do not
add any sub directories.

### Quote or not to quote ?

According to the official YAML specification one should:

- Whenever applicable use the unquoted style since it is the most readable.
- Use the single-quoted style (') if characters such as " and \ are being used inside the string to avoid escaping them
  and therefore improve readability.
- Use the double-quoted style (") when the first two options aren't sufficient, i.e. in scenarios where more complex
  line breaks are required or non-printable characters are needed.

## Aggregating changelogs of the same version across different projects

The `aggregate` goal allows you to compile changelogs from multiple projects into a single,
consolidated changelog for a specified version.
This can be particularly useful when working with a set of related projects or microservices,
providing a centralized view of changes across these projects for a single release version.

### Command

To aggregate changelogs for a specified version, use the following Maven command:

```shell
mvn logchange:aggregate -DaggregateVersion="X.X.X"
```

As `aggregateVersion` you can also use `unreleased`.

### Parameters

- **aggregateVersion** (required) – The version number to aggregate across all specified projects. This parameter
  identifies the changelog entries for the specific version you want to compile.
- **inputDir** (optional) – The directory where the `logchange-config.yml` file is located, defaulting to the
  `changelog` directory.
- **configFile** (optional) – The name of the configuration file, defaulting to `logchange-config.yml`.

### Configuration

In the `logchange-config.yml` file, you define the projects to be aggregated in the aggregates section.
Each project should be configured with its:

- **name** - used as a prefix for changelog entries from each project, making it easy to identify the origin of each
  entry in the aggregated changelog
- **url** - the URL for downloading the project files as a `tar.gz` repository archive
- **type** - specifies the type of archive to download; currently, only `tar.gz` format is supported.
- **input_dir** - indicates the `changelog` directory for each project, where changelog entries for that project are
  stored.

## Logchange CLI

The logchange (CLI) is a standalone tool designed for managing CHANGELOGs, providing flexibility for projects that
cannot or do not use Maven.
It allows you to create and maintain CHANGELOGs independently of your project’s technology stack while ensuring a
consistent format and resolving merge conflicts with ease.

CLI tool offers all the available features of those of maven plugin:

### Starting

TODO

### logchange init

| Option                   | Default Value       | Description                                                                        |
|--------------------------|---------------------|------------------------------------------------------------------------------------|
| `--path, -p`             | `current directory` | Path indicating the directory in which the command is to be executed.              |
| `--inputDir`             | `changelog`         | Specifies the input directory for the logchange data.                              |
| `--unreleasedVersionDir` | `unreleased`        | Specifies the directory for unreleased changes where entries are stored.           |
| `--outputFile`           | `CHANGELOG.md`      | Specifies the name of the output file where the generated changelog will be saved. |

### logchange add

| Option                   | Default Value       | Description                                                                                                                                                                                                                            |
|--------------------------|---------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `--path, -p`             | `current directory` | Path indicating the directory in which the command is to be executed.                                                                                                                                                                  |
| `--inputDir`             | `changelog`         | Specifies the input directory for the logchange data.                                                                                                                                                                                  |
| `--unreleasedVersionDir` | `unreleased`        | Specifies the directory where created entries will be stored.                                                                                                                                                                          |
| `--fileName`             | N/A                 | The name of the entry file.                                                                                                                                                                                                            |
| `--batchMode`            | `false`             | Determines if the command should run in batch mode.                                                                                                                                                                                    |
| `--empty`                | `false`             | Allows adding an empty entry.                                                                                                                                                                                                          |
| `--title`                | N/A                 | The title of the CHANGELOG entry.                                                                                                                                                                                                      |
| `--author`               | N/A                 | The author of the change, assigned to the CHANGELOG entry. The value should follow the format: `"name; nick; url"`, with fields separated by the `;` character.                                                                        |
| `--authors`              | N/A                 | A list of authors, separated by commas. For each author, the same format as in the `--author` option applies, e.g., `"John Doe; jdoe; https://github.com/logchange/hofund, Richard Roe; rroe; https://github.com/logchange/valhalla"`. |
| `--type`                 | N/A                 | The type of change (e.g., "added", "changed", etc.).                                                                                                                                                                                   |
| `--link.name`            | N/A                 | The name of the link to be included in the change description.                                                                                                                                                                         |
| `--link.url`             | N/A                 | The URL associated with the link, such as a bug report or issue.                                                                                                                                                                       |

### logchange example

| Option                   | Default Value       | Description                                                           |
|--------------------------|---------------------|-----------------------------------------------------------------------|
| `--path, -p`             | `current directory` | Path indicating the directory in which the command is to be executed. |
| `--inputDir`             | `changelog`         | Specifies the input directory for the logchange data.                 |
| `--unreleasedVersionDir` | `unreleased`        | Specifies the directory where created entries will be stored.         |
| `--fileName`             | `00000-entry.yml`   | The name of the entry file.                                           |

### logchange generate

| Option         | Default Value          | Description                                                                        |
|----------------|------------------------|------------------------------------------------------------------------------------|
| `--path, -p`   | `current directory`    | Path indicating the directory in which the command is to be executed.              |
| `--inputDir`   | `changelog`            | Specifies the input directory for the logchange data.                              |
| `--outputFile` | `CHANGELOG.md`         | Specifies the name of the output file where the generated CHANGELOG will be saved. |
| `--configFile` | `logchange-config.yml` | Specifies the name of configuration file.                                          |

### logchange lint

| Option         | Default Value          | Description                                                                        |
|----------------|------------------------|------------------------------------------------------------------------------------|
| `--path, -p`   | `current directory`    | Path indicating the directory in which the command is to be executed.              |
| `--inputDir`   | `changelog`            | Specifies the input directory for the logchange data.                              |
| `--outputFile` | `CHANGELOG.md`         | Specifies the name of the output file where the generated changelog will be saved. |
| `--configFile` | `logchange-config.yml` | Specifies the name of configuration file.                                          |

### logchange release

| Option                   | Default Value          | Description                                                                        |
|--------------------------|------------------------|------------------------------------------------------------------------------------|
| `--path, -p`             | `current directory`    | Path indicating the directory in which the command is to be executed.              |
| `--versionToRelease`     | N/A                    | Specifies the name of the version that we want to release (e.g., `2.1.1`).         |
| `--unreleasedVersionDir` | `unreleased`           | Specifies the directory for unreleased changes where entries are stored.           |
| `--inputDir`             | `changelog`            | Specifies the input directory for the logchange data.                              |
| `--outputFile`           | `CHANGELOG.md`         | Specifies the name of the output file where the generated changelog will be saved. |
| `--configFile`           | `logchange-config.yml` | Specifies the name of configuration file.                                          |
| `--generateChangesXml`   | `false`                | Specifies whether to generate an XML file containing the changes.                  |
| `--xmlOutputFile`        | `changes.xml`          | Specifies the name of the XML output file.                                         |

### logchange aggregate

| Option               | Default Value          | Description                                                           |
|----------------------|------------------------|-----------------------------------------------------------------------|
| `--path, -p`         | `current directory`    | Path indicating the directory in which the command is to be executed. |
| `--aggregateVersion` | N/A                    | Specifies the version that the aggregation will be performed for.     |
| `--inputDir`         | `changelog`            | Specifies the input directory for the logchange data.                 |
| `--configFile`       | `logchange-config.yml` | Specifies the name of configuration file.                             |

### logchange archive

| Option         | Default Value          | Description                                                                             |
|----------------|------------------------|-----------------------------------------------------------------------------------------|
| `--path, -p`   | `current directory`    | Path indicating the directory in which the command is to be executed.                   |
| `--version`    | N/A                    | Specifies the version up to which all released versions should be archived (inclusive). |
| `--inputDir`   | `changelog`            | Specifies the input directory for the logchange data.                                   |
| `--configFile` | `logchange-config.yml` | Specifies the name of configuration file.                                               |

## Gradle Plugin

### Starting

Add plugin to `build.gradle`:

```groovy
plugins {
    ....
    id 'dev.logchange' version '1.19.9'
}
```

You can also configure basic properties in `build.gradle`:
(these are defaults so you don't have to define anything)

```groovy
logchange {
    rootPath = "."
    inputDir = "changelog"
    unreleasedVersionDir = "unreleased"
    outputFile = "CHANGELOG.md"
    configFile = "logchange-config.yml"
    generateChangesXml = false
    xmlOutputFile = "changes.xml"
}
```

### Gradle Tasks

```text
Logchange tasks
---------------
logchangeAdd - Creates new YML file with logchange structure in <unreleasedVersionDir> directory
logchangeExample - Creates new YML file with pre-filled properties in <unreleasedVersionDir> directory
logchangeArchive - Archives the list of released versions up to (and including) the specified version by transferring their summaries to archive.md file, merging all existing archives, and deleting the corresponding version directories.
logchangeAggregate - Aggregates projects changelogs to create one. Useful when we have many projects that make up one product.
logchangeGenerate - Generates changelog file (<outputFile>) based on .yml entries and archives (does not moves any files)
logchangeInit - Initialize directory (project) with basic logchange configuration and directory structure
logchangeLint - Lints and validates YML files and logchange config
logchangeRelease - Creates new changelog release by moving files from <unreleasedVersionDir> directory to vX.X.X directory
```

## CI/CD

### GitHub Actions

### GitLab CI/CD

## Development

1. Java installed.
2. `mvn` as a command in your terminal(needed for integration tests, IniteliJ does not support maven integration tests).

[Maven]: https://maven.apache.org/

[logchange docker image]: https://hub.docker.com/r/logchange/logchange

[logchange homebrew formula]: https://github.com/logchange/homebrew-tap

[Gradle]: https://gradle.org/
