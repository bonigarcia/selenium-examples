package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import locators.AccountLocators;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToAccountsTab() {
        waitForClickable(AccountLocators.APP_LAUNCHER_BUTTON).click();
        waitForElement(AccountLocators.SEARCH_APPS_INPUT).sendKeys("Accounts");
        waitForClickable(AccountLocators.ACCOUNTS_NAV_ITEM).click();
        waitForSpinner();
    }

    public void searchAndSelectAccount(String accountName) {
        waitForElement(AccountLocators.GLOBAL_SEARCH_INPUT).sendKeys(accountName);
        // Press Enter or wait for search results to appear
        // In Salesforce Lightning, often results appear dynamically
        // We'll directly click the link once it appears
        By accountLink = By.xpath(String.format(AccountLocators.ACCOUNT_LINK_BY_TITLE_FORMAT, accountName));
        waitForClickable(accountLink).click();
        waitForSpinner();
    }

    public String getTCVAmount() {
        return getText(AccountLocators.TCV_AMOUNT_DISPLAY);
    }

    public boolean isTCVAmountFieldVisible() {
        return isElementVisible(AccountLocators.TCV_AMOUNT_DISPLAY);
    }

    public void clickEditButton() {
        waitForClickable(AccountLocators.EDIT_BUTTON).click();
        waitForSpinner();
    }

    public void enterTCVAmount(String amount) {
        // Clear the field first before entering new value
        fill(AccountLocators.TCV_AMOUNT_INPUT_EDIT, amount);
    }

    public void saveAccountEdit() {
        waitForClickable(AccountLocators.SAVE_EDIT_BUTTON).click();
        waitForSpinner();
    }

    public String getSuccessMessage() {
        waitForElement(AccountLocators.SUCCESS_TOAST_MESSAGE);
        return getText(AccountLocators.SUCCESS_TOAST_MESSAGE);
    }
}
