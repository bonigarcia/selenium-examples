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
package io.github.bonigarcia.selenium.wdm.docker;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class DockerChromeVncTest {

    WebDriver driver;
    WebDriverManager wdm;

    @BeforeEach
    void setupTest() {
        wdm = WebDriverManager.chromedriver().browserInDocker().enableVnc();
        driver = wdm.create();
    }

    @Test
    void testDockerChromeVnc() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(30).toMillis());

        wdm.quit();
    }

}
