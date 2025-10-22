# Jinja templating guide for logchange

This document explains how to use Jinja2 templates (via Jinjava) to customize:
- the main changelog file generated from all versions (changelog templates), and
- per‑version summaries (version‑summary templates).

It includes:
- the directory layout expected by logchange,
- the configuration keys in logchange-config.yml,
- what objects are available to templates, and
- two ready‑to‑use sample templates that mimic the default Java generators in Markdown.

Note: Jinja support is implemented with Jinjava. Most Jinja2 syntax works (loops, if, filters). Trim/lstrip blocks are enabled, so whitespace is handled similarly to standard Jinja2.


Quick start

1) Create the templates folder
Place your templates under: changelog/.templates/

2) Add configuration in changelog/logchange-config.yml

Example snippet (only the templates section shown):

  templates:
    # Controls how single entries and authors are rendered in default MD generators
    entry: "${prefix}${title} ${merge_requests} ${issues} ${links} ${authors}"
    author: "([${name}](${url}) @${nick})"

    # Generate additional files for each released version using a Jinja template
    version_summary_templates:
      # Path is relative to changelog/.templates
      - path: my-version-summary.md.j2

    # Generate a top‑level file from the entire changelog using a Jinja template
    changelog_templates:
      # Path is relative to changelog/.templates
      - path: my-changelog.md.j2

3) Place your template files
- changelog/.templates/my-version-summary.md.j2
- changelog/.templates/my-changelog.md.j2

4) Run logchange as usual
- mvn logchange:generate or mvn logchange:release (or CLI equivalents)

Where generated files are written

- Version‑summary templates
  For each version X.Y.Z, every template listed under templates.version_summary_templates is rendered to a file placed next to version-summary.md inside that version directory:
  changelog/vX.Y.Z/<outputFileName>
  The output file name is the file name part of the path you configured (e.g., my-version-summary.md.j2 -> my-version-summary.md.j2). You can use an extension such as .md or .html in your template file name if you want the output to have that extension.

- Changelog templates (main changelog)
  Every template listed under templates.changelog_templates is rendered once for the whole project and saved at the repository root (next to CHANGELOG.md):
  <repo_root>/<outputFileName>
  The output file name is the file name part of the path you configured (e.g., my-changelog.md.j2 -> my-changelog.md.j2). If you want the output to be my-changelog.md, name the template my-changelog.md (no .j2 is required, logchange treats the configured path as a literal file name).

Important: Unlike version-summary templates, changelog templates do NOT write into each version directory.


Objects available to templates

logchange exposes strongly‑typed domain objects to the template context:

- For changelog_templates (whole project):
  changelog — type: dev.logchange.core.domain.changelog.model.Changelog
  Useful properties/methods:
  - versions: Iterable<ChangelogVersion>
  - archives: ChangelogArchives (optional historical content)

- For version_summary_templates (single release):
  version — type: dev.logchange.core.domain.changelog.model.version.ChangelogVersion
  Useful properties/methods:
  - version: dev.logchange.core.domain.changelog.model.version.Version
  - releaseDateTime: java.time-like value stringified in output
  - entriesGroups: List<ChangelogVersionEntriesGroup>
    Each group has:
      - type: the display name of the group (e.g., Added, Removed, Fixed, etc.)
      - notEmpty: boolean
      - entries: List<ChangelogEntry>
  - getEntries(): all entries (flattened)
  - getEntriesWithOrder(): stream of entries respecting original ordering
  - getConfigurations(): List<ChangelogEntryConfiguration> (useful to build a configuration changes table)
  - getDetachedImportantNotes(): Stream<DetachedImportantNote>
  - getDetachedConfigurations(): Stream<DetachedConfiguration>

Note: Jinjava can iterate over Java Lists and, in most cases, Streams. If a Stream does not iterate in your environment, prefer the list-returning helpers like getEntries() or getConfigurations().


Sample Jinja template — Version summary (Markdown)

Place at changelog/.templates/my-version-summary.md.j2

<!-- Prevents auto-formatting in some IDEs -->
<!-- @formatter:off -->
<!-- noinspection -->

### {{ version.version }}{% if version.releaseDateTime %} - {{ version.releaseDateTime }}{% endif %}

{% set total = 0 %}
{% for group in version.entriesGroups %}
  {% if group.notEmpty %}
### {{ group.type }} ({{ group.entries | length }} change{% if (group.entries | length) != 1 %}s{% endif %})
{% for entry in group.entries %}
- {{ entry }}
{% endfor %}
  {% set total = total + (group.entries | length) %}

  {% endif %}
{% endfor %}

{% set notes = version.detachedImportantNotes %}
{% if notes %}
### Important notes
{% for note in notes %}
- {{ note.message }}
{% endfor %}
{% endif %}

{% set configs = version.configurations %}
{% if configs and (configs | length) > 0 %}
### Configuration changes

| Type: {{ configs[0].type }} |
| --- |
{% for cfg in configs %}
| <ul><li>{{ cfg.action }} `{{ cfg.property }}`{% if cfg.defaultValue %} with default value: `{{ cfg.defaultValue }}`{% endif %}</li><li>Description: {{ cfg.description }}</li></ul> |
{% endfor %}
{% endif %}


Sample Jinja template — Main changelog (Markdown)

Place at changelog/.templates/my-changelog.md.j2

<!-- @formatter:off -->
<!-- noinspection -->

<!-- This file is automatically generated by logchange tool -->
<!-- Visit https://github.com/logchange/logchange and leave a star -->
<!-- DO NOT MODIFY THIS FILE MANUALLY -->

{% for v in changelog.versions %}
{% set is_unreleased = (v.version | string) == 'unreleased' %}
[{{ 'unreleased' if is_unreleased else v.version }}]{% if not is_unreleased and v.releaseDateTime %} - {{ v.releaseDateTime }}{% endif %}
--------------------

{% set notes = v.detachedImportantNotes %}
{% if notes %}
### Important notes
{% for note in notes %}
- {{ note.message }}
{% endfor %}
{% endif %}

{% for group in v.entriesGroups %}
  {% if group.notEmpty %}
### {{ group.type }} ({{ group.entries | length }} change{% if (group.entries | length) != 1 %}s{% endif %})
{% for entry in group.entries %}
- {{ entry }}
{% endfor %}

  {% endif %}
{% endfor %}

{% set configs = v.configurations %}
{% if configs and (configs | length) > 0 %}
### Configuration changes

| Type: {{ configs[0].type }} |
| --- |
{% for cfg in configs %}
| <ul><li>{{ cfg.action }} `{{ cfg.property }}`{% if cfg.defaultValue %} with default value: `{{ cfg.defaultValue }}`{% endif %}</li><li>Description: {{ cfg.description }}</li></ul> |
{% endfor %}
{% endif %}


{% endfor %}


Tips and gotchas

- File locations:
  - Templates are always loaded from changelog/.templates/<path>.
  - For changelog_templates, the output path is <repo_root>/<fileNameFromPath>.
  - For version_summary_templates, the output path is changelog/vX.Y.Z/<fileNameFromPath>.
- Start simple: you can rely on Java objects’ toString for ChangelogEntry ({{ entry }}) to get the default entry format.
- Need HTML instead of Markdown? Just put .html in your template’s file name and write HTML.
- Whitespace: Jinjava is configured with trimBlocks and lstripBlocks to make template whitespace predictable.
- Missing fields? Open an issue describing what you need. The domain models are in logchange-core/src/main/java/dev/logchange/core/domain/changelog/model.
- Examples: See examples/templates in this repository for ready-to-copy templates.
