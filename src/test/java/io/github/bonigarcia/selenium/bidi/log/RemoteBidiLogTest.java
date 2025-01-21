/*
 * (C) Copyright 2024 Boni Garcia (https://bonigarcia.github.io/)
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
package io.github.bonigarcia.selenium.bidi.log;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.GenericLogEntry;
import org.openqa.selenium.bidi.module.LogInspector;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

class RemoteBidiLogTest {

    WebDriver driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.enableBiDi();
        driver = new Augmenter().augment(
                new RemoteWebDriver(new URL("http://localhost:4444"), options));
    }

    @Test
    void test() {
        List<GenericLogEntry> logs = new ArrayList<>();
        try (LogInspector logInspector = new LogInspector(driver)) {
            logInspector.onGenericLog(logs::add);
            logInspector.onConsoleEntry(logs::add);
            logInspector.onJavaScriptException(logs::add);
        }

        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/console-logs.html");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(_d -> logs.size() > 3);

        for (GenericLogEntry log : logs) {
            System.out.println(log.getText());
        }
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
