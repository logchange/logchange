package dev.logchange.core.domain.changelog.model;

import dev.logchange.core.domain.changelog.model.entry.ChangelogModule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@ToString
@Getter
public class DetachedImportantNote implements HasModules {

    private final String value;
    private final List<ChangelogModule> modules;
}
