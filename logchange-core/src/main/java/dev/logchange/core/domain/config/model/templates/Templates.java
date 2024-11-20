package dev.logchange.core.domain.config.model.templates;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Templates {

    public static final Templates EMPTY = Templates.builder().build();
    private static final String DEFAULT_ENTRY_FORMAT = "${prefix}${title} ${merge_requests} ${issues} ${links} ${authors}";
    private String entryFormat;

    public String getEntryFormat() {
        return defaultIfBlank(entryFormat, DEFAULT_ENTRY_FORMAT);
    }
}
