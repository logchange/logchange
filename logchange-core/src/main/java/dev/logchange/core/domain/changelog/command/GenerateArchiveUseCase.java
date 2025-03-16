package dev.logchange.core.domain.changelog.command;

import dev.logchange.core.domain.changelog.model.version.Version;
import lombok.Getter;

import java.util.List;

public interface GenerateArchiveUseCase {

    List<String> handle(GenerateArchiveCommand command);

    @Getter
    class GenerateArchiveCommand {
        private final Version version;
        private final boolean single;

        private GenerateArchiveCommand(Version version, boolean single) {
            this.version = version;
            this.single = single;
        }

        public static GenerateArchiveCommand of(Version version, boolean single) {
            if (version == null) {
                throw new IllegalArgumentException("Version cannot be null!");
            }
            if (version.isUnreleased()) {
                throw new IllegalArgumentException("Version to be archived cannot be unreleased!");
            }
            return new GenerateArchiveCommand(version, single);
        }
    }
}
