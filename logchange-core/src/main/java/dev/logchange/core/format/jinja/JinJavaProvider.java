package dev.logchange.core.format.jinja;

import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.JinjavaConfig;

public class JinJavaProvider {

    public static Jinjava get() {
        JinjavaConfig config = JinjavaConfig.newBuilder()
                .withTrimBlocks(true)
                .withLstripBlocks(true)
                .build();

        return new Jinjava(config);
    }
}
