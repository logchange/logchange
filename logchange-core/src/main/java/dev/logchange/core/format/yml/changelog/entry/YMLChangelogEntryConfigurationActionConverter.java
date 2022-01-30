package dev.logchange.core.format.yml.changelog.entry;

import de.beosign.snakeyamlanno.convert.Converter;

public class YMLChangelogEntryConfigurationActionConverter implements Converter<YMLChangelogEntryConfigurationAction> {

    @Override
    public String convertToYaml(YMLChangelogEntryConfigurationAction configurationAction) {
        return configurationAction.getAction();
    }

    @Override
    public YMLChangelogEntryConfigurationAction convertToModel(Object o) {
        for (YMLChangelogEntryConfigurationAction configurationAction : YMLChangelogEntryConfigurationAction.values()) {
            if (configurationAction.getAction().equals(o)) {
                return configurationAction;
            }
        }

        throw new IllegalArgumentException("Illegal value of action: " + toString());
    }

}
