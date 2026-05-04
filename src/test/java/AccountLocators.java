package locators;

import org.openqa.selenium.By;

public class AccountLocators {
    // App Launcher and Navigation
    public static final By APP_LAUNCHER_BUTTON = By.cssSelector("div.slds-icon-waffle"); // Reusing existing elem_xycr1r
    public static final By APP_LAUNCHER_SEARCH_INPUT = By.cssSelector("input.slds-input[placeholder='Search apps and items...']"); // Reusing existing elem_iutqq6
    public static final By ACCOUNTS_APP_LAUNCHER_ITEM = By.xpath("//mark[text()='Accounts']/ancestor::a"); // Reusing existing elem_b2qekh
    public static final By GLOBAL_SEARCH_INPUT = By.cssSelector("input[placeholder='Search Salesforce']"); // Reusing existing elem_sdqp9b

    // Account Detail Page Elements
    public static final By ACCOUNT_DETAIL_PAGE_HEADER_TITLE = By.xpath("//h1[contains(@class, 'slds-page-header__title')]"); // Part of existing elem_4yem9e context
    public static final By TCV_AMOUNT_DISPLAY_FIELD = By.cssSelector("lightning-output-field[field-name='TCV_Amount__c'] lightning-formatted-text");
    public static final By EDIT_BUTTON_HIGHLIGHT_PANEL = By.xpath("//button[@name='Edit']"); // Reusing existing edit

    // Account Edit Modal Elements
    public static final By EDIT_MODAL_TITLE = By.cssSelector("div.slds-modal__header h2.slds-modal__title");
    public static final By TCV_AMOUNT_INPUT_EDIT_MODAL = By.cssSelector("lightning-input-field[field-name='TCV_Amount__c'] input[type='text']"); // Reusing existing tCVAmountc and refining by type
    public static final By SAVE_EDIT_BUTTON = By.cssSelector("button[name='SaveEdit']"); // Reusing existing saveEdit
    public static final By CANCEL_EDIT_BUTTON = By.cssSelector("button[name='CancelEdit']");

    // Error Messages
    public static final By VALIDATION_ERROR_MESSAGE_IN_MODAL = By.cssSelector("div.forceVisualMessageList ul li");

    private AccountLocators() {}

    // Dynamic locators for search results
    public static By getAccountSearchResultLink(String accountName) {
        return By.xpath("//a[@title='" + accountName + "']"); // Reusing existing elem_q06wto
    }
}