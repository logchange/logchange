package dev.logchange.core.application.file.query;

import java.io.IOException;
import java.nio.file.Path;

public interface TarGzQuery {
    Path get(String projectUrl, String projectInputDir) throws IOException;
}
