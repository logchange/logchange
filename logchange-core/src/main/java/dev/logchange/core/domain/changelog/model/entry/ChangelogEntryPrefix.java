package dev.logchange.core.domain.changelog.model.entry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class ChangelogEntryPrefix {
    private final String value;
}
