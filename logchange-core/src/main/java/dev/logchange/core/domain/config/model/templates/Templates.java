package dev.logchange.core.domain.config.model.templates;


import lombok.Builder;

import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

@Builder
public class Templates {

    public static final String DEFAULT_ENTRY_FORMAT = "${prefix}${title} ${merge_requests} ${issues} ${links} ${authors}";
    public static final String DEFAULT_AUTHOR_FORMAT = "([${name}](${url}) @${nick})";
    public static final Templates EMPTY = Templates.builder().build();

    private String entryFormat;
    private String authorFormat;
    private List<VersionSummaryTemplate> versionSummaryTemplates;
    private List<ChangelogTemplate> changelogTemplates;

    public String getEntryFormat() {
        return defaultIfBlank(entryFormat, DEFAULT_ENTRY_FORMAT);
    }

    public String getAuthorFormat() {
        return defaultIfBlank(authorFormat, DEFAULT_AUTHOR_FORMAT);
    }

    public List<VersionSummaryTemplate> getVersionSummaryTemplates() {
        return versionSummaryTemplates == null ? Collections.emptyList() : versionSummaryTemplates;
    }

    public List<ChangelogTemplate> getChangelogTemplates() {
        return changelogTemplates == null ? Collections.emptyList() : changelogTemplates;
    }
}
