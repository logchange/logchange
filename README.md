![logchange-banner](https://user-images.githubusercontent.com/25181517/138590008-f98457b3-602a-4af5-9b28-0c499fe7e378.png)

`CHANGELOG.md` is one of the most important files in a repository. It allows others to find out about 
the most important changes in the project in short time.
To achieve this, `CHANGELOG.md` should be created be in accordance with the rules, however there is no one
standard agreed by community.

Another big problem with `CHANGELOG.md` is a problem with merge conflicts. Probably you, as a developer, also
encounter it when someone merged changes to `CHANGELOG.md` before you.

**To solve these problems, this project was created. It allows to keep a changelog style and reduce merge request conflicts by keeping every change
in a separate YAML file and generate `CHANGELOG.md` during release.**

This tool is also helpful to create reliable release notes during your release process. 

### Reference

The convention is maintained according to the principles set out in the [Keep a Changelog](https://keepachangelog.com/en/1.0.0/) and with some additions as a result from experience in developing various types of projects.

The same problem with merge conflicts with `CHANGELOG.md` was described by GitLab. [LINK](https://about.gitlab.com/blog/2018/07/03/solving-gitlabs-changelog-conflict-crisis/)

### Examples
[TODO]
The example project with this plugin usage you can find in `example` directory.

## Usage
### Maven Plugin
### Starting
Add a plugin to your `pom.xml`. For multi-module projects with one `CHANGELOG.md` add it in main `pom.xml`.

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>io.github.marwin1991</groupId>
                <artifactId>keep-changelog-maven-plugin</artifactId>
                <version>0.3.0</version>
            </plugin>
        </plugins>
    </build>
```

Use this command from your terminal to create important directories and empty CHANGELOG.md
```shell
mvn keep-changelog:init
```

If you already had a `CHANGELOG.md` file you can move it to `changelog/archive.md` file. The name of the archive file have to start from `archive` phrase (f.e. `archive-1.0.0.md`).

After using `init` command or just creating `changelog/unreleased` directory your project is ready, and you can start
adding new changelog entries by creating YAML files.

**IMPORTANT:** If you develop on two main branches like f.e 1.1.X and 1.2.X **do not** merge 1.1.X branch to 1.2.X
before release otherwise YAML files will merge in one big version.
_In future there is a plan to support `unreleased*` directories names like `unreleased-1.1`_

### Adding new change

Adding new change is really simple, you can just add new YAML file to `changelog/unreleased` directory and keep format
presented below. You can also use command to generate this file:

[TODO] - functionality not done yet

```shell
mvn keep-changelog:add
```

### YAML format

```yml
title: Test title
authors:
  - nick: marwin1991
    url: https://github.com/marwin1991
merge_request: 1
issues:
  - 1
links: # links to external systems, like jira or redmine
  - name: TASK_NUMBER
    url: https://www.google.pl
type: fixed # [added/changed/deprecated/removed/fixed/security]
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
| `merge_request`  | The merge request's number which is adding this change.  |
| `issues` | List of issues associated with this change. |
| `type` | Type of the change. |
| `links` | Any link to other project or external tool witch contains information about this change. |
| `important_notes` | Important notes, that has to be included in release. |
| `configurations` | Configurations changes important during release. |

### Generating `CHANGELOG.md`

Everytime you want to generate `CHANGELOG.md` you can use command:

```shell
mvn keep-changelog:generate
```

To prevent merge conflicts (it is idea of this project) generation should be done with use of CI/CD Tool on a main
branch of a project.

[TODO]
Create Docker image with this tool, that will be use in CI/CD Tool, so that not only java projects will be able to use
it.

### Creating release (from unreleased)

```shell
mvn keep-changelog:release
```

### `CHANGELOG.md` structure overview

TODO

### Versions summaries

TODO

### Releasing the version

TODO

### Archives

TODO

## TODO:

- add command to create archive-X.md from selected directory
- add tests
- add javadocs
- support  `unreleased*` directories names like `unreleased-1.1`
