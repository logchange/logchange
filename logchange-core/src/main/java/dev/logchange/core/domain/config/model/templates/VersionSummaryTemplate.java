package dev.logchange.core.domain.config.model.templates;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class VersionSummaryTemplate {
    private final String path;

    public static VersionSummaryTemplate of(String path) {
        return new VersionSummaryTemplate(path);
    }
}
