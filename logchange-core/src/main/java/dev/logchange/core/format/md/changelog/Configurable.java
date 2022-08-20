package dev.logchange.core.format.md.changelog;

import dev.logchange.core.domain.config.model.Config;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Configurable {
    private final Config config;
}
