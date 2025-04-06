package dev.logchange.core.domain.config.model.templates;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangelogTemplate {

    private final String path;

    public static ChangelogTemplate of(String path) {
        return new ChangelogTemplate(path);
    }

    public String getOutputFileName() {
        return new File(path).getName();
    }

    @Override
    public String toString() {
        return "changelog template[path=" + path + "]";
    }
}
