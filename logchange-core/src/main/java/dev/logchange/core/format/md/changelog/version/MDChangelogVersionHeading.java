package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.version.Version;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.changelog.Configurable;
import dev.logchange.md.MarkdownBasics;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

class MDChangelogVersionHeading extends Configurable implements MD {

    public static final String RELEASE_DATE_FORMAT = "yyyy-MM-dd";

    private static final String versionHeaderFormat = "[${version}]${releaseData}";
    private static final String releaseDatePrefix = " - ";

    private final Version version;
    private final OffsetDateTime releaseDateTime;

    public MDChangelogVersionHeading(Version version, OffsetDateTime releaseDateTime, Config config) {
        super(config);
        this.version = version;
        this.releaseDateTime = releaseDateTime;
    }

    @Override
    public String toString() {
        return getVersionHeading() + "\n\n";
    }

    private String getVersionHeading() {
        Map<String, String> valuesMap = new HashMap<>();
        if (version.isUnreleased()) {
            valuesMap.put("version", getConfig().getLabels().getUnreleased());
        } else {
            valuesMap.put("version", version.getValue());
        }
        valuesMap.put("releaseData", getVersionDate());

        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return MarkdownBasics.heading(sub.replace(versionHeaderFormat), 2);
    }

    private String getVersionDate() {
        if (releaseDateTime == null) {
            return StringUtils.EMPTY;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RELEASE_DATE_FORMAT);
        return releaseDatePrefix + formatter.format(releaseDateTime);
    }
}
