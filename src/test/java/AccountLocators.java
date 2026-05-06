package locators;

import org.openqa.selenium.By;

class AccountLocators {
    public static final By NAME_INPUT = By.cssSelector("lightning-input-field[field-name='Name'] input");
    public static final By TYPE_COMBOBOX = By.cssSelector("lightning-combobox[field-name='Type']");
    public static final By OWNER_LOOKUP = By.cssSelector("lightning-lookup[field-name='OwnerId'] input");
    // Add more locators here
}
}