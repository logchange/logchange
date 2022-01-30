package dev.logchange.core.format.md.changelog;

import dev.logchange.core.domain.changelog.model.ChangelogHeading;
import dev.logchange.core.format.md.MD;
import net.steppschuh.markdowngenerator.text.Text;
import org.apache.commons.lang3.StringUtils;

public class MDChangelogHeading implements MD {

    private final ChangelogHeading heading;

    public MDChangelogHeading(ChangelogHeading heading) {
        this.heading = heading;
    }

    @Override
    public String toString() {
        return getChangelogHeading();
    }

    private String getChangelogHeading() {
        if (heading.isBlank()) {
            return StringUtils.EMPTY;
        }

        return new Text(heading.getValue()) + "\n";
    }
}
