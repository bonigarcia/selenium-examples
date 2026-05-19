package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import org.openqa.selenium.WebDriver;

import utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {

        driver =
                DriverFactory.initDriver();
    }

    @AfterMethod
    public void teardown() {

        DriverFactory.quitDriver();
    }
}
