package dev.logchange.core.domain.changelog.model.entry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@ToString
@AllArgsConstructor
public class ChangelogEntryLink {
    private final String name;
    private final String url;

    public static ChangelogEntryLink of(String name, String url) {
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("Link url cannot be blank! Current value name: " + name + " url: " + url);
        }
        return new ChangelogEntryLink(name, url);
    }

}
