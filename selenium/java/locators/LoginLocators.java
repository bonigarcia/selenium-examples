package locators;

import org.openqa.selenium.By;

public class LoginLocators {

    private LoginLocators() {}

    public static final By USERNAME_INPUT =
            By.id("username");

    public static final By PASSWORD_INPUT =
            By.id("password");

    public static final By LOGIN_BUTTON =
            By.cssSelector("button[type='submit']");
}
