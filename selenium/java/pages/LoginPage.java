package pages;

import locators.LoginLocators;

import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {

        super(driver);
    }

    public LoginPage open(String url) {

        driver.get(url);

        return this;
    }

    public LoginPage enterUsername(String username) {

        type(
                LoginLocators.USERNAME_INPUT,
                username
        );

        return this;
    }

    public LoginPage enterPassword(String password) {

        type(
                LoginLocators.PASSWORD_INPUT,
                password
        );

        return this;
    }

    public LoginPage clickLogin() {

        click(
                LoginLocators.LOGIN_BUTTON
        );

        return this;
    }

    public void login(
            String username,
            String password
    ) {

        enterUsername(username);

        enterPassword(password);

        clickLogin();
    }
}
