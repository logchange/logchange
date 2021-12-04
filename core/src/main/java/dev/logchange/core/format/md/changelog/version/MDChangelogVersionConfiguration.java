package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.MDList;
import dev.logchange.core.model.entry.ChangelogEntryConfiguration;
import net.steppschuh.markdowngenerator.table.Table;
import net.steppschuh.markdowngenerator.text.code.Code;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class MDChangelogVersionConfiguration implements MD {

    private final List<ChangelogEntryConfiguration> configurations;

    public MDChangelogVersionConfiguration(List<ChangelogEntryConfiguration> configurations) {
        this.configurations = configurations;
    }

    @Override
    public String toString() {
        return getConfiguration();
    }

    private String getConfiguration() {
        if (configurations.size() != 0) {
            StringBuilder markdownConfiguration = new StringBuilder(new Heading("Configuration changes", 3) + "\n\n");
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

    private Table getConfigurationTable(List<ChangelogEntryConfiguration> configurations, String type) {
        Table.Builder tableBuilder = new Table.Builder().addRow("Type: " + type);

        for (ChangelogEntryConfiguration configuration : configurations) {
            MDList configDetails = new MDList();
            configDetails.add(configuration.getAction().getDisplayText() + new Code(configuration.getKey()) + " with default value: " + new Code(configuration.getDefaultValue()));
            configDetails.add("Description: " + configuration.getDescription());
            configDetails.add(configuration.getMoreInfo());

            tableBuilder.addRow(configDetails);
        }

        return tableBuilder.build();
    }
}
