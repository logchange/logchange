package dev.logchange.core.format.yml.changelog.entry;

import de.beosign.snakeyamlanno.convert.Converter;

public class YMLChangelogEntryTypeConverter implements Converter<YMLChangelogEntryType> {

    @Override
    public String convertToYaml(YMLChangelogEntryType changeLogEntryType) {
        return changeLogEntryType.getType();
    }

    @Override
    public YMLChangelogEntryType convertToModel(Object o) {
        for (YMLChangelogEntryType changeLogEntryType : YMLChangelogEntryType.values()) {
            if (changeLogEntryType.getType().equals(o)) {
                return changeLogEntryType;
            }
        }

        throw new IllegalArgumentException("Illegal value of type: " + toString());
    }

}

