const locators = require('../locators/AccountLocators');

class AccountPage {
  constructor(page) {
    this.page = page;
  }

  async navigateToAccounts() {
    await this.page.click(locators.appLauncherIcon);
    await this.page.waitForSelector(locators.appLauncherSearchInput, { state: 'visible' });
    await this.page.fill(locators.appLauncherSearchInput, 'Accounts');
    await this.page.click(locators.accountsAppLauncherLink);
    await this.page.waitForURL('**/lightning/o/Account/home**', { timeout: 30000 });
    await this.page.waitForLoadState('networkidle');
  }

  async searchAndOpenAccount(accountName) {
    await this.page.waitForSelector(locators.globalSearchInput, { state: 'visible' });
    await this.page.fill(locators.globalSearchInput, accountName);
    const searchResultLocator = locators.globalSearchDropdownResult(accountName);
    await this.page.waitForSelector(searchResultLocator, { state: 'visible' });
    await this.page.click(searchResultLocator);
    await this.page.waitForURL(`**/${accountName}/view**`, { timeout: 30000 });
    await this.page.waitForLoadState('networkidle');
  }

  async getTcvAmountDisplayed() {
    await this.page.waitForSelector(locators.tcvAmountFieldDisplayed, { state: 'visible' });
    return await this.page.textContent(locators.tcvAmountFieldDisplayed);
  }

  async clickEditButton() {
    await this.page.waitForSelector(locators.editButton, { state: 'visible' });
    await this.page.click(locators.editButton);
    await this.page.waitForSelector(locators.tcvAmountFieldEditModal, { state: 'visible' });
  }

  async isTcvAmountFieldEditableInEditModal() {
    const tcvField = this.page.locator(locators.tcvAmountFieldEditModal);
    // Playwright's isEditable checks for disabled, readonly, pointer-events:none, etc.
    return await tcvField.isEditable();
  }

  async attemptToFillTcvAmountInEditModal(newValue) {
    const tcvField = this.page.locator(locators.tcvAmountFieldEditModal);
    // Use fill with a short timeout. If the field is truly not editable, this won't change its value.
    // Playwright won't throw if `force: true` is not used, but will attempt if element is enabled.
    await tcvField.fill(newValue, { timeout: 1000 }).catch(() => {}); // Catch error if fill truly fails (e.g. element not enabled)
    await this.page.keyboard.press('Tab'); // Blur the field to ensure value update/validation triggers
  }

  async getTcvAmountInEditModal() {
    const tcvField = this.page.locator(locators.tcvAmountFieldEditModal);
    await tcvField.waitFor({ state: 'visible' });
    return await tcvField.inputValue();
  }

  async clickCancelEditButton() {
    await this.page.click(locators.cancelEditButton);
    await this.page.waitForLoadState('networkidle'); // Wait for modal to close and page to become stable
  }
}