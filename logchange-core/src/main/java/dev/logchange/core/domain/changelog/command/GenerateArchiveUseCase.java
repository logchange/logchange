package dev.logchange.core.domain.changelog.command;

import dev.logchange.core.domain.changelog.model.version.Version;
import lombok.Getter;

import java.util.List;

public interface GenerateArchiveUseCase {

    List<String> handle(GenerateArchiveCommand command);

    @Getter
    class GenerateArchiveCommand {
        private final Version version;

        private GenerateArchiveCommand(Version version) {
            this.version = version;
        }

        public static GenerateArchiveCommand of(Version version) {
            if (version == null) {
                throw new IllegalArgumentException("Version cannot be null!");
            }
            if (version.isUnreleased()) {
                throw new IllegalArgumentException("Version to be archived cannot be unreleased!");
            }
            return new GenerateArchiveCommand(version);
        }
    }
}
