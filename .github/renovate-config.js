module.exports = {
  onboarding: false,
  platform: 'github',
  repositories: [
    'logchange/logchange'
  ],
  allowPostUpgradeCommandTemplating: true,
  // postUpgradeTasks: {
  //   commands: ['npx renovate-changelog-updater --depName {{{depName}}} --current-version {{{currentVersion}}} --new-version {{{newVersion}}}'],
  //   fileFilters: ['**/*.yml', '**/*.yaml'],
  //   executionMode: 'update',
  // }
};
