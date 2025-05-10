package dev.logchange.core.format.md.changelog.version;

import dev.logchange.core.domain.changelog.model.HasModules;
import dev.logchange.core.domain.changelog.model.entry.ChangelogModule;

import java.util.*;

public class MDModuleStructure<T extends HasModules> {

    static public <T extends HasModules> MDModuleStructure<T> build(List<T> entries, Comparator<T> entryComparator) {

        Comparator<ChangelogModule> moduleComparator = Comparator.comparing(ChangelogModule::getName);
        Map<ChangelogModule, List<T>> groups = new TreeMap<>(moduleComparator);
        List<T> noModules = new ArrayList<>();

        for (T entry : entries) {
            List<ChangelogModule> modules = entry.getModules();
            if(modules.isEmpty()) {
                noModules.add(entry);
            }
            for (ChangelogModule module: modules) {
                groups.putIfAbsent(module, new ArrayList<>());
                List<T> groupEntries = groups.get(module);
                groupEntries.add(entry);
            }
        }

        if(entryComparator != null) {
            noModules.sort(entryComparator);
            for (Map.Entry<ChangelogModule, List<T>> entry : groups.entrySet()) {
                entry.getValue().sort(entryComparator);
            }
        }

        return new MDModuleStructure<>(noModules, groups);
    }

    static public <T extends HasModules> MDModuleStructure<T> build(List<T> entries) {
        return build(entries, null);
    }

    private final List<T> noModules;

    private final Map<ChangelogModule, List<T>> groups;

    public MDModuleStructure(List<T> noModules, Map<ChangelogModule, List<T>> groups) {
        this.noModules = noModules;
        this.groups = groups;
    }

    public List<T> getNoModules() {
        return noModules;
    }

    public Map<ChangelogModule, List<T>> getGroups() {
        return groups;
    }
}
