# CHANGELOG
{% for version in changelog.versions.versions %}
## {{ version.version }} - {{ version.releaseDateTime }}
{% for entriesGroup in version.entriesGroups %}
    {% if entriesGroup.notEmpty %}
    ### {{ entriesGroup.type }}
    {% for entry in entriesGroup.entries %}
       - {{ entry }}
    {% endfor %}
    {% endif %}
{% endfor %}
{% endfor %}
