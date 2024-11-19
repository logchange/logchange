package dev.logchange.core.format.md.changelog;

import dev.logchange.core.domain.config.model.Heading;
import dev.logchange.core.format.md.MD;
import dev.logchange.md.MarkdownBasics;
import org.apache.commons.lang3.StringUtils;

public class MDChangelogHeading implements MD {

    private final Heading heading;

    public MDChangelogHeading(Heading heading) {
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

        return MarkdownBasics.text(heading.getValue()) + "\n\n\n";
    }
}
