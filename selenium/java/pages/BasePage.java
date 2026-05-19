package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;

    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );
    }

    protected WebElement find(By locator) {

        return wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(locator)
        );
    }

    protected void click(By locator) {

        wait.until(
                ExpectedConditions
                        .elementToBeClickable(locator)
        ).click();
    }

    protected void type(By locator, String text) {

        WebElement element = find(locator);

        element.clear();

        element.sendKeys(text);
    }

    protected String getText(By locator) {

        return find(locator).getText();
    }

    protected boolean isDisplayed(By locator) {

        try {

            return find(locator).isDisplayed();

        } catch (TimeoutException e) {

            return false;
        }
    }

    protected void scrollIntoView(By locator) {

        WebElement element = find(locator);

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView(true);",
                element
        );
    }

    public String getPageTitle() {

        return driver.getTitle();
    }

    public String getCurrentUrl() {

        return driver.getCurrentUrl();
    }
}
