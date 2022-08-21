package dev.logchange.core.domain.config.model.labels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

@Builder
@AllArgsConstructor
public class NumberOfChangesLabels {

    public static final String DEFAULT_SINGULAR = "change";
    public static final String DEFAULT_PLURAL = "changes";

    public static final NumberOfChangesLabels EMPTY = NumberOfChangesLabels.builder().build();

    private String singular;
    private String plural;

    public String getSingular() {
        return StringUtils.defaultIfBlank(singular, DEFAULT_SINGULAR);
    }

    public String getPlural() {
        return StringUtils.defaultIfBlank(plural, DEFAULT_PLURAL);
    }
}
