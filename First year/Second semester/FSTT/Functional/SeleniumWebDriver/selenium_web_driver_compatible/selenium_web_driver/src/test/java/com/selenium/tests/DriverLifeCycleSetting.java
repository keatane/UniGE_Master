package com.selenium.tests;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

public class DriverLifeCycleSetting {
    protected WebDriver driver;

    @AfterEach
    public void afterEach() {
        if (driver != null)
            driver.quit();
    }
}
