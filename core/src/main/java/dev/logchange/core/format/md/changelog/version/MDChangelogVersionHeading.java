package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.version.Version;
import dev.logchange.core.format.md.MD;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

class MDChangelogVersionHeading implements MD {

    public static final String RELEASE_DATE_FORMAT = "yyyy-MM-dd";

    private static final String versionHeaderFormat = "[${version}]${releaseData}";
    private static final String releaseDatePrefix = " - ";

    private final Version version;
    private final OffsetDateTime releaseDateTime;

    public MDChangelogVersionHeading(Version version, OffsetDateTime releaseDateTime) {
        this.version = version;
        this.releaseDateTime = releaseDateTime;
    }

    @Override
    public String toString() {
        return getVersionHeading().toString() + "\n\n";
    }

    private Heading getVersionHeading() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("version", version.getValue());
        valuesMap.put("releaseData", getVersionDate());

        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return new Heading(sub.replace(versionHeaderFormat), 2);
    }

    private String getVersionDate() {
        if (releaseDateTime == null) {
            return StringUtils.EMPTY;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RELEASE_DATE_FORMAT);
        return releaseDatePrefix + formatter.format(releaseDateTime);
    }
}
