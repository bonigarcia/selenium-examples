module.exports = {
  // App Launcher and Navigation
  appLauncherIcon: "button[class*='slds-icon-waffle']",
  appLauncherSearchInput: "input[placeholder='Search apps and items...']",
  accountsAppLauncherLink: "a[data-label='Accounts'] span.slds-truncate",

  // Global Search Bar
  globalSearchInput: "input[placeholder*='Search|'], input[title='Search']", // Targets global search bar, often titled 'Search' or has placeholder 'Search|'
  globalSearchDropdownResult: (accountName) => `a[title="${accountName}"]`, // Search result link in dropdown

  // Account Detail Page
  tcvAmountFieldDisplayed: "lightning-output-field[field-name='TCV_Amount__c'] lightning-formatted-text",
  editButton: "button[name='Edit']", // Standard Edit button in highlight panel

  // Account Edit Modal
  tcvAmountFieldEditModal: "lightning-input-field[field-name='TCV_Amount__c'] input[type='text']",
  saveEditButton: "button[name='SaveEdit']",
  cancelEditButton: "button[name='CancelEdit']"
};