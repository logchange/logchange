module.exports = {
  onboarding: false,
  platform: 'github',
  repositories: [
    'logchange/logchange'
  ],
  allowPostUpgradeCommandTemplating: true,
  postUpgradeTasks: {
    commands: ['mvn dev.logchange:logchange-maven-plugin:add --non-recursive -DbatchMode -DfileName=renovate-{{{depNameSanitized}}}-{{{newVersion}}}.yml -Dtitle="Upgraded {{{depNameLinked}}} from {{{currentVersion}}} to {{{newVersion}}}" -Dauthor=marwin1991 -Dtype=dependency_update -Dlink.name=notes -Dlink.url={{{url}}}'],
    fileFilters: ['**/*.yml', '**/*.yaml'],
    executionMode: 'update',
  }
};
