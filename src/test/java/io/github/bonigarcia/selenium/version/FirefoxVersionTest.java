/*
 * (C) Copyright 2023 Boni Garcia (https://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia.selenium.version;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.manager.SeleniumManager;

class FirefoxVersionTest {

    WebDriver driver;

    @BeforeEach
    void setup() throws Exception {
        Arrays.stream(Logger.getLogger("").getHandlers()).forEach(handler -> {
            handler.setLevel(Level.FINE);
        });
        Logger.getLogger(SeleniumManager.class.getName()).setLevel(Level.FINE);
        Handler handler = new FileHandler("firefox-version-test.xml");
        Logger.getLogger(SeleniumManager.class.getName()).addHandler(handler);

        // https://www.selenium.dev/documentation/webdriver/drivers/options/#browserversion
        FirefoxOptions options = new FirefoxOptions();
        options.setBrowserVersion("beta");
        driver = new FirefoxDriver(options);
    }

    @Test
    void test() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String title = driver.getTitle();
        assertThat(title).contains("Selenium WebDriver");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
