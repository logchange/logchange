package dev.logchange.core.domain.config.model.templates;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class VersionSummaryTemplate {

    private final String path;

    public static VersionSummaryTemplate of(String path) {
        return new VersionSummaryTemplate(path);
    }

    public String getOutputFileName() {
        return new File(path).getName();
    }

    @Override
    public String toString() {
        return "version summary template[path=" + path + "]";
    }
}
