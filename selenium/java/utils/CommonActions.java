package utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonActions {

    private final WebDriver driver;

    private final WebDriverWait wait;

    public CommonActions(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );
    }

    public void click(WebElement element) {

        wait.until(
                ExpectedConditions
                        .elementToBeClickable(element)
        );

        element.click();
    }

    public void type(
            WebElement element,
            String text
    ) {

        wait.until(
                ExpectedConditions
                        .visibilityOf(element)
        );

        element.clear();

        element.sendKeys(text);
    }

    public void jsClick(WebElement element) {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].click();",
                element
        );
    }

    public String getTitle() {

        return driver.getTitle();
    }

    public String getCurrentUrl() {

        return driver.getCurrentUrl();
    }
}
