// ADDED NEW COMMENT
package pages;
import org.openqa.selenium.WebDriver;
import locators.AccountLocators;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigates to the Accounts tab using the App Launcher.
     */
    public void navigateToAccountsTab() {
        waitForClickable(AccountLocators.APP_LAUNCHER_BUTTON).click();
        fill(AccountLocators.APP_LAUNCHER_SEARCH_INPUT, "Accounts");
        waitForClickable(AccountLocators.ACCOUNTS_APP_LAUNCHER_ITEM).click();
        waitForElement(AccountLocators.GLOBAL_SEARCH_INPUT); // Wait for Accounts list view to load
    }

    /**
     * Searches for an account using the global search bar and clicks on the result.
     * @param accountName The name of the account to search for.
     */
    public void searchAndSelectAccount(String accountName) {
        waitForClickable(AccountLocators.GLOBAL_SEARCH_INPUT).sendKeys(accountName);
        waitForElement(AccountLocators.getAccountSearchResultLink(accountName)).click();
        waitForElement(AccountLocators.ACCOUNT_DETAIL_PAGE_HEADER_TITLE); // Wait for account detail page to load
    }

    /**
     * Retrieves the displayed Total Contract Value (TCV) amount from the Account detail page.
     * @return The TCV amount as a String.
     */
    public String getTCVAmountDisplayed() {
        waitForElement(AccountLocators.TCV_AMOUNT_DISPLAY_FIELD);
        return getText(AccountLocators.TCV_AMOUNT_DISPLAY_FIELD);
    }

    /**
     * Clicks the 'Edit' button on the Account highlight panel to open the edit modal.
     */
    public void clickEditButton() {
        waitForClickable(AccountLocators.EDIT_BUTTON_HIGHLIGHT_PANEL).click();
        waitForElement(AccountLocators.EDIT_MODAL_TITLE); // Wait for edit modal to open
    }

    /**
     * Enters a new value into the TCV Amount field within the edit modal.
     * @param newAmount The new TCV amount to enter.
     */
    public void editTCVAmount(String newAmount) {
        waitForElement(AccountLocators.TCV_AMOUNT_INPUT_EDIT_MODAL);
        fill(AccountLocators.TCV_AMOUNT_INPUT_EDIT_MODAL, newAmount);
    }

    /**
     * Clicks the 'Save' button in the edit modal.
     */
    public void clickSaveButton() {
        click(AccountLocators.SAVE_EDIT_BUTTON);
    }

    /**
     * Retrieves the text of the validation error message displayed in the modal.
     * @return The validation error message as a String.
     */
    public String getValidationErrorMessage() {
        waitForElement(AccountLocators.VALIDATION_ERROR_MESSAGE_IN_MODAL);
        return getText(AccountLocators.VALIDATION_ERROR_MESSAGE_IN_MODAL);
    }

    /**
     * Clicks the 'Cancel' button in the edit modal to dismiss it.
     */
    public void clickCancelEditButton() {
        click(AccountLocators.CANCEL_EDIT_BUTTON);
        waitForElementHidden(AccountLocators.EDIT_MODAL_TITLE); // Wait for modal to close
    }
}