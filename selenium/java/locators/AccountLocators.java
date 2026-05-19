package locators;

import org.openqa.selenium.By;

public class AccountLocators {

    // App Launcher and Navigation
    public static final By APP_LAUNCHER_BUTTON = By.cssSelector("div.slds-icon-waffle");
    public static final By SEARCH_APPS_INPUT = By.cssSelector("input.slds-input[placeholder='Search apps and items...']");
    public static final By ACCOUNTS_NAV_ITEM = By.xpath("//mark[text()='Accounts']/ancestor::a");

    // Global Search
    public static final By GLOBAL_SEARCH_INPUT = By.cssSelector("input[placeholder='Search Salesforce']");
    public static final By ACCOUNT_LINK_BY_TITLE_FORMAT = By.xpath("//a[@title='%s']");

    // Account Detail Page
    public static final By TCV_AMOUNT_DISPLAY = By.cssSelector("lightning-output-field[field-name='TCV_Amount__c'] lightning-formatted-text");
    public static final By EDIT_BUTTON = By.xpath("//button[@name='Edit']");

    // Account Edit Modal
    public static final By TCV_AMOUNT_INPUT_EDIT = By.cssSelector("lightning-input-field[field-name='TCV_Amount__c'] input[type='text']");
    public static final By SAVE_EDIT_BUTTON = By.cssSelector("button[name='SaveEdit']");
    public static final By CANCEL_EDIT_BUTTON = By.cssSelector("button[name='CancelEdit']");

    // Toast Messages
    public static final By SUCCESS_TOAST_MESSAGE = By.cssSelector("div.forceToastMessage");

    private AccountLocators() {}
}