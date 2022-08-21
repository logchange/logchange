package dev.logchange.core.application.config;

import dev.logchange.core.domain.config.model.Config;

public interface ConfigRepository {

    Config find();

}
