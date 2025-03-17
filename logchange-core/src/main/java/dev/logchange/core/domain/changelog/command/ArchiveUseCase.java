package dev.logchange.core.domain.changelog.command;

import dev.logchange.core.domain.changelog.model.version.Version;
import lombok.Getter;

import java.util.List;

public interface ArchiveUseCase {

    List<String> handle(ArchiveCommand command);

    @Getter
    class ArchiveCommand {
        private final Version version;

        private ArchiveCommand(Version version) {
            this.version = version;
        }

        public static ArchiveCommand of(Version version) {
            if (version == null) {
                throw new IllegalArgumentException("Version cannot be null!");
            }
            if (version.isUnreleased()) {
                throw new IllegalArgumentException("Version to be archived cannot be unreleased!");
            }
            return new ArchiveCommand(version);
        }
    }
}
