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
package io.github.bonigarcia.selenium.bidi.action;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.module.Input;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;

class EdgeBidiActionTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        EdgeOptions options = new EdgeOptions();
        options.enableBiDi();
        driver = new EdgeDriver(options);
    }

    @Test
    void test() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement inputText = driver.findElement(By.name("my-text"));

        String textValue = "Hello World!";
        Input input = new Input(driver);
        Actions actions = new Actions(driver);
        Actions sendKeys = actions.sendKeys(inputText, textValue);
        input.perform(driver.getWindowHandle(), sendKeys.getSequences());

        assertThat(inputText.getDomProperty("value")).isEqualTo(textValue);

        inputText.clear();
        assertThat(inputText.getDomProperty("value")).isEmpty();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
