package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryConfiguration;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.MDList;
import dev.logchange.core.format.md.changelog.Configurable;
import dev.logchange.md.table.MarkdownTableBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static dev.logchange.md.MarkdownBasics.code;
import static dev.logchange.md.MarkdownBasics.heading;

class MDChangelogVersionConfiguration extends Configurable implements MD {

    private final List<ChangelogEntryConfiguration> configurations;

    public MDChangelogVersionConfiguration(List<ChangelogEntryConfiguration> configurations, Config config) {
        super(config);
        this.configurations = configurations;
    }

    @Override
    public String toString() {
        return getConfiguration();
    }

    private String getConfiguration() {
        if (configurations.size() != 0) {
            StringBuilder markdownConfiguration = new StringBuilder(heading(getConfig().getLabels().getConfiguration().getHeading(), 3) + "\n\n");
            Set<String> types = getConfigurationsTypes();

            for (String type : types) {
                markdownConfiguration.append(getConfigurationTable(configurations, type)).append("\n\n");
            }

            return markdownConfiguration.toString();
        } else {
            return StringUtils.EMPTY;
        }
    }

    private Set<String> getConfigurationsTypes() {
        return configurations.stream()
                .map(ChangelogEntryConfiguration::getType)
                .collect(Collectors.toSet());
    }

    private String getConfigurationTable(List<ChangelogEntryConfiguration> configurations, String type) {
        MarkdownTableBuilder tableBuilder = new MarkdownTableBuilder(getConfig().getLabels().getConfiguration().getType() + ": " + type);

        for (ChangelogEntryConfiguration configuration : configurations) {
            if (type.equals(configuration.getType())) {
                MDList configDetails = new MDList();
                configDetails.add(configuration.getPrefix());
                configDetails.add(getConfig().getLabels().getConfiguration().getActions().getAction(configuration.getAction()) + " "
                        + code(configuration.getKey()) + " "
                        + getConfig().getLabels().getConfiguration().getWithDefaultValue() + ": "
                        + code(configuration.getDefaultValue()));
                configDetails.add(getConfig().getLabels().getConfiguration().getDescription() + ": " + configuration.getDescription());
                configDetails.add(configuration.getMoreInfo());

                tableBuilder.addRow(configDetails);
            }
        }

        return tableBuilder.build();
    }
}
