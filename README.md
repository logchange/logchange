![logchange-banner](https://user-images.githubusercontent.com/25181517/138590008-f98457b3-602a-4af5-9b28-0c499fe7e378.png)

🌳 `CHANGELOG.md` is one of the most important files in a repository. It allows others to find out about 
the most important changes in the project in short time.
To achieve this, `CHANGELOG.md` should be created be in accordance with the rules, however there is no one
standard agreed by community.

🪓 Another big problem with `CHANGELOG.md` is a problem with merge conflicts. Probably you, as a developer, also encounter
it when someone merged changes to `CHANGELOG.md` before you.

🌲 When you create new merge/pull request and in the meantime you will release version, with old-fashioned `CHANGELOG.md`
you have to remember, to move new changelog's entry up, to the new version section. With this tool you don't have to!

🪓 If your PO is against you to use this tool in your projects, don't worry... just ask him to do `CHANGELOG.md` by his
own, and he will be the first one, who will ask you to use this tool. 🔥

**🪵 To solve these problems, this project was created. It allows to keep a changelog style and reduce merge request
conflicts by keeping every change in a separate YAML file and generate `CHANGELOG.md` during release.**

This tool is also helpful to create reliable release notes during your release process. 

### Reference

The convention is maintained according to the principles set out in the [Keep a Changelog](https://keepachangelog.com/en/1.0.0/) and with some additions as a result from experience in developing various types of projects.

The same problem with merge conflicts with `CHANGELOG.md` was described by GitLab. [LINK](https://about.gitlab.com/blog/2018/07/03/solving-gitlabs-changelog-conflict-crisis/)

## Usage
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
         <version>0.5.0</version>
         <inherited>false</inherited> <!-- For multi module project -->
      </plugin>
   </plugins>
</build>
```

2. You don't have to add anything to your `pom.xml`, just use all commands with `groupId` and full plugin name, like
   here:

```shell
mvn dev.logchange:logchange-maven-plugin:init 
```

**⚠️⚠️ IMPORTANT ⚠️⚠️** For multi-module projects add `--non-recursive` to all executed commands! f.e.

```shell
mvn dev.logchange:logchange-maven-plugin:init --non-recursive
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

**IMPORTANT:** If you develop on two main branches like f.e 1.1.X and 1.2.X **do not** merge 1.1.X branch to 1.2.X
before release otherwise YAML files (stored in `unreleased` directory) will merge in one big version (because all files
from both versions will be in the same directory).
_In the future, if there will be a need, there is a plan to support `unreleased*` directories names
like `unreleased-1.1`_

### Adding new change

Adding new change is really simple, you can just add new YAML file to `changelog/unreleased` directory and keep format
presented below, or you can use command to generate this file, this one will guide you through whole process:

```shell
mvn logchange:add
```

In other hand, if you would like to just generate an empty file, with some default strings, you can use:
```shell
mvn logchange:add -Dempty -DfileName=000001-some-name.yml
```


### YAML format

```yml
title: Test title
authors:
   - name: Peter
     nick: marwin1991
     url: https://github.com/marwin1991
merge_requests:
   - 1
issues:
   - 1
links: # links to external systems, like jira or redmine
   - name: TASK_NUMBER
     url: https://www.google.pl
type: fixed # [added/changed/deprecated/removed/fixed/security/other]
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

| Keyword  | Description |
| :------------: | ------------- |
| `title`  | Description of change that has been made.  |
| `authors`  | List of change's authors.  |
| `merge_requests`  | The merge requests' numbers which are adding this change.  |
| `issues` | List of issues associated with this change. |
| `type` | Type of the change. |
| `links` | Any link to other project or external tool witch contains information about this change. |
| `important_notes` | Important notes, that has to be included in release. |
| `configurations` | Configurations changes important during release. |

### Generating `CHANGELOG.md`

Everytime you want to generate `CHANGELOG.md` you can use command:

```shell
mvn logchange:generate
```

To prevent merge conflicts (it is idea of this project) generation of `CHANGELOG.md` should be done with use of CI/CD Tool on a main
branch of a project during release.

### Creating release (from unreleased directory)

This command is the prefered to create release and generates `CHANGELOG.md` (One of these command steps is to call the generate command).
During release all files from `unreleased` directory are moved to `v.X.X.X` where X.X.X is the version from `pom.xml`.
After moving files, command create file `release-date.txt` with current date, then generation of `CHANGELOG.md` and versions summaries is done.

```shell
mvn logchange:release
```

### `CHANGELOG.md` structure overview

TODO

### Versions summaries

During release `version-summary.md` is created, it is a file that contains current version changelog entries. 
You can see example [**here**](https://github.com/logchange/logchange/blob/main/changelog/v0.4.0/version-summary.md).

This feature is really useful during creating releases on GitHub or GitLab, you can just attach the content of this file as release notes.

### Archives

If your project already contains some `CHANGELOG.md` you don't have to rewrite it to `yml`. Only what have to do is to move it to `changelog` directory and rename it to `archive.md` or `archiveXXXX.md` where XXXX is anything you want f.e. -2.0.0

## TODO:

- add command to create archive-X.md from selected directory
- add javadocs
- support  `unreleased*` directories names like `unreleased-1.1`
- Create Docker image with this tool, that will be use in CI/CD Tool, so that not only java projects will be able to use
it.

## Development

1. Java installed.
2. `mvn` as a command in your terminal(needed for integration tests).
