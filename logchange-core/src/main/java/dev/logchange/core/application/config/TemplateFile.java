package dev.logchange.core.application.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

import static dev.logchange.core.Constants.TEMPLATES_DIR_NAME;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplateFile {

    private final String content;

    public static TemplateFile of(String content) {
        return new TemplateFile(content);
    }

    public static File getTemplatePath(File inputDirectory, String templatePath) {
        return new File(inputDirectory.getPath() + "/" + TEMPLATES_DIR_NAME + "/" + templatePath);
    }

}
