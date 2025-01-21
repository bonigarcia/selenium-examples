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
package io.github.bonigarcia.selenium.bidi.browsing;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.browsingcontext.NavigationInfo;
import org.openqa.selenium.bidi.browsingcontext.ReadinessState;
import org.openqa.selenium.bidi.module.BrowsingContextInspector;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

class RemoteBidiBrowsingTest {

    WebDriver driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.enableBiDi();
        driver = new Augmenter().augment(
                new RemoteWebDriver(new URL("http://localhost:4444"), options));
    }

    @Test
    void test() throws Exception {
        try (BrowsingContextInspector inspector = new BrowsingContextInspector(
                driver)) {
            CompletableFuture<NavigationInfo> future = new CompletableFuture<>();
            inspector.onBrowsingContextLoaded(future::complete);

            BrowsingContext context = new BrowsingContext(driver,
                    driver.getWindowHandle());
            context.navigate("https://bonigarcia.dev/selenium-webdriver-java/",
                    ReadinessState.COMPLETE);

            NavigationInfo navigationInfo = future.get(5, TimeUnit.SECONDS);
            assertThat(navigationInfo.getUrl())
                    .contains("selenium-webdriver-java");
        }
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
