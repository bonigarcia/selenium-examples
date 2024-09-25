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
package io.github.bonigarcia.selenium.bidi.network;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.module.Network;
import org.openqa.selenium.bidi.network.ResponseDetails;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

class FirefoxBidiLogTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.enableBiDi();
        driver = new FirefoxDriver(options);
    }

    @Test
    void test() throws Exception {
        try (Network network = new Network(driver)) {
            CompletableFuture<ResponseDetails> future = new CompletableFuture<>();
            network.onResponseCompleted(future::complete);
            driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

            ResponseDetails response = future.get(5, TimeUnit.SECONDS);

            assertThat(response.getRequest().getMethod()).isEqualTo("GET");
            assertThat(response.getResponseData().getStatus()).isEqualTo(200);
        }
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
