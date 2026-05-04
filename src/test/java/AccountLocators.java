package locators;

import org.openqa.selenium.By;

class AccountLocators {
    public static final By ACCOUNT_NAME_INPUT = By.cssSelector("lightning-input-field[field-name='Name'] input");
    public static final By ACCOUNT_TYPE_COMBOBOX = By.cssSelector("lightning-combobox[field-name='Type']");
    // Add more locators here
    private AccountLocators() {}
}