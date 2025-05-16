package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.DetachedConfiguration;
import dev.logchange.core.domain.changelog.model.entry.ChangelogEntryConfiguration;
import dev.logchange.core.domain.changelog.model.entry.ChangelogModule;
import dev.logchange.core.domain.config.model.Config;
import dev.logchange.core.format.md.MD;
import dev.logchange.core.format.md.MDList;
import dev.logchange.core.format.md.changelog.Configurable;
import dev.logchange.core.format.md.changelog.entry.MDChangelogEntryPrefix;
import dev.logchange.md.table.MarkdownTableBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static dev.logchange.md.MarkdownBasics.code;
import static dev.logchange.md.MarkdownBasics.heading;

class MDChangelogVersionConfiguration extends Configurable implements MD {

    private final List<DetachedConfiguration> configurations;

    public MDChangelogVersionConfiguration(List<DetachedConfiguration> configurations, Config config) {
        super(config);
        this.configurations = configurations;
    }

    @Override
    public String toString() {
        return getConfiguration();
    }

    private String getConfiguration() {
        if (configurations == null || configurations.isEmpty()) {
            return StringUtils.EMPTY;
        }

        MDModuleStructure<DetachedConfiguration> structure = MDModuleStructure.build(configurations, Comparator.naturalOrder());

        StringBuilder markdownConfiguration = new StringBuilder(heading(getConfig().getLabels().getConfiguration().getHeading(), 3) + "\n\n");
        Set<String> types = getConfigurationsTypes();

        for (String type : types) {
            markdownConfiguration.append(getConfigurationTable(structure, type)).append("\n\n");
        }

        return markdownConfiguration.toString();
    }

    private Set<String> getConfigurationsTypes() {
        return configurations.stream()
                .map(DetachedConfiguration::getType)
                .collect(Collectors.toSet());
    }

    private String getConfigurationTable(MDModuleStructure<DetachedConfiguration> structure, String type) {
        MarkdownTableBuilder tableBuilder = new MarkdownTableBuilder(getConfig().getLabels().getConfiguration().getType() + ": " + type);

        structure.getNoModules().forEach(configuration -> {
            if (type.equals(configuration.getType())) {
                MDList line = renderLine(configuration.getConfiguration(), Optional.empty());
                tableBuilder.addRow(line);
            }
        });
        structure.getGroups().forEach((module, configurations) -> {
            configurations.forEach(configuration -> {
                if (type.equals(configuration.getType())) {
                    MDList line = renderLine(configuration.getConfiguration(), Optional.of(module));
                    tableBuilder.addRow(line);
                }
            });
        });

        return tableBuilder.build();
    }

    private MDList renderLine(ChangelogEntryConfiguration configuration, Optional<ChangelogModule> maybeModule) {
        MDList configDetails = new MDList();
        maybeModule.ifPresent(module -> {
            String prefix = module.getName();
            configDetails.add(prefix);
        });
        configDetails.add(getConfig().getLabels().getConfiguration().getActions().getAction(configuration.getAction()) + " "
                + code(configuration.getKey()) + " "
                + getConfig().getLabels().getConfiguration().getWithDefaultValue() + ": "
                + code(configuration.getDefaultValue()));
        configDetails.add(getConfig().getLabels().getConfiguration().getDescription() + ": " + configuration.getDescription());
        configDetails.add(configuration.getMoreInfo());
        return configDetails;
    }
}
