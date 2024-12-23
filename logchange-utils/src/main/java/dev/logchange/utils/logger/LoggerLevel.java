package dev.logchange.utils.logger;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LoggerLevel {
    DEBUG(0), INFO(1), WARN(2), ERROR(3);

    private final int level;

    public boolean isEnabled(LoggerLevel level) {
        return level.level >= this.level;
    }
}
