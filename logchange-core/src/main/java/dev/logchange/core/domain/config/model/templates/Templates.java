package dev.logchange.core.domain.config.model.templates;


import lombok.Builder;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

@Builder
public class Templates {

    public static final String DEFAULT_ENTRY_FORMAT = "${prefix}${title} ${merge_requests} ${issues} ${links} ${authors}";
    public static final String DEFAULT_AUTHOR_FORMAT = "([${name}](${url}) @${nick})";
    public static final Templates EMPTY = Templates.builder().build();
    private String entryFormat;
    private String authorFormat;

    public String getEntryFormat() {
        return defaultIfBlank(entryFormat, DEFAULT_ENTRY_FORMAT);
    }

    public String getAuthorFormat() {
        return defaultIfBlank(authorFormat, DEFAULT_AUTHOR_FORMAT);
    }
}
