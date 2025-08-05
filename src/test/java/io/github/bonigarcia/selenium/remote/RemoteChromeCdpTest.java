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
package io.github.bonigarcia.selenium.remote;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v138.dom.model.Rect;
import org.openqa.selenium.devtools.v138.page.Page;
import org.openqa.selenium.devtools.v138.page.Page.GetLayoutMetricsResponse;
import org.openqa.selenium.devtools.v138.page.model.Viewport;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class RemoteChromeCdpTest {

    WebDriver driver;
    DevTools devTools;

    @BeforeEach
    void setupTest() throws Exception {
        ChromeOptions options = new ChromeOptions();
        WebDriver remoteDriver = new RemoteWebDriver(
                new URL("http://localhost:4444/"), options);

        driver = new Augmenter().augment(remoteDriver);
        devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
    }

    @Test
    void test() throws Exception {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/long-page.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(
                By.className("container"), By.tagName("p")));

        GetLayoutMetricsResponse metrics = devTools
                .send(Page.getLayoutMetrics());
        Rect contentSize = metrics.getContentSize();
        String screenshotBase64 = devTools
                .send(Page.captureScreenshot(Optional.empty(), Optional.empty(),
                        Optional.of(new Viewport(0, 0, contentSize.getWidth(),
                                contentSize.getHeight(), 1)),
                        Optional.empty(), Optional.of(true),
                        Optional.of(false)));
        Path destination = Paths.get("fullpage-screenshot.png");
        Files.write(destination, Base64.getDecoder().decode(screenshotBase64));

        assertThat(destination).exists();
    }

    @AfterEach
    void teardown() {
        if (devTools != null) {
            devTools.close();
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
