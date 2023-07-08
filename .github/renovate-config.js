module.exports = {
  onboarding: false,
  platform: 'github',
  repositories: [
    'logchange/logchange'
  ],
  allowPostUpgradeCommandTemplating: true,
  postUpgradeTasks: {
    //commands: ['curl -sSL https://raw.githubusercontent.com/logchange/logchange/main/add_entry.sh | bash -s -- -DfileName=renovate-{{{depNameSanitized}}}-{{{newVersion}}}.yml -Dtitle="Upgraded {{{depName}}} from {{{currentVersion}}} to {{{newVersion}}}" -Dauthor=marwin1991 -Dtype=dependency_update -Dlink.name=notes -Dlink.url={{{url}}}'],
    commands: ['install-tool maven && mvn dev.logchange:logchange-maven-plugin:add --non-recursive -DbatchMode -DfileName=renovate-{{{depNameSanitized}}}-{{{newVersion}}}.yml -Dtitle="Upgraded {{{depNameLinked}}} from {{{currentVersion}}} to {{{newVersion}}}" -Dauthor=marwin1991 -Dtype=dependency_update -Dlink.name=notes -Dlink.url={{{url}}}'],
    fileFilters: ['**/*.yml'],
    executionMode: 'update',
  }
};
