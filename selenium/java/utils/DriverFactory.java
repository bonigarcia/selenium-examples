package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    private DriverFactory() {}

    public static WebDriver initDriver() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options =
                new ChromeOptions();

        options.addArguments(
                "--remote-allow-origins=*"
        );

        options.addArguments(
                "--start-maximized"
        );

        WebDriver driver =
                new ChromeDriver(options);

        driver.manage()
                .timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(10)
                );

        DRIVER.set(driver);

        return driver;
    }

    public static WebDriver getDriver() {

        return DRIVER.get();
    }

    public static void quitDriver() {

        if (DRIVER.get() != null) {

            DRIVER.get().quit();

            DRIVER.remove();
        }
    }
}
