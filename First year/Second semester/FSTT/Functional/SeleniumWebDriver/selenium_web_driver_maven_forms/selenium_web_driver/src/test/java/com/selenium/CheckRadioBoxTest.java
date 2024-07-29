package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class CheckRadioBoxTest  {
    private WebDriver driver;
    private int toWait = 2000;

    @BeforeAll
    static void setUpClass(){
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
    }
    
    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        // if (driver != null) // for test coverage
        driver.quit();
    }

    @Test
    void test_check_and_radio() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(baseUrl);
        
        WebElement checkbox = driver.findElement(By.id("my-check-2"));
        Thread.sleep(toWait);
        checkbox.click();
        assertTrue(checkbox.isSelected());
        Thread.sleep(toWait);
        checkbox.click();
        assertFalse(checkbox.isSelected());
        Thread.sleep(toWait);

        WebElement radio_1 = driver.findElement(By.id("my-radio-1"));
        WebElement radio_2 = driver.findElement(By.id("my-radio-2"));
        Thread.sleep(toWait);
        radio_1.click();
        assertTrue(radio_1.isSelected());
        Thread.sleep(toWait);
        radio_2.click();
        assertTrue(radio_2.isSelected());
        assertFalse(radio_1.isSelected());
    }
}
