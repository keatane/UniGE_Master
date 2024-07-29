package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class DropDownTest  {
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
    void test_dropdown_with_visible() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(baseUrl);
        Select dropdown = new Select(driver.findElement(By.name("my-select")));
        Thread.sleep(toWait);
        dropdown.selectByVisibleText("Three");
        Thread.sleep(toWait);
        assertEquals("Three", dropdown.getFirstSelectedOption().getText());
    }

    @Test
    void test_dropdown_with_index() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(baseUrl);
        Select dropdown = new Select(driver.findElement(By.name("my-select")));
        Thread.sleep(toWait);
        dropdown.selectByIndex(2);
        Thread.sleep(toWait);
        assertEquals("Two", dropdown.getFirstSelectedOption().getText());
    }

    @Test
    void test_dropdown_with_value() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(baseUrl);
        Select dropdown = new Select(driver.findElement(By.name("my-select")));
        Thread.sleep(toWait);
        dropdown.selectByValue("1");
        Thread.sleep(toWait);
        assertEquals("One", dropdown.getFirstSelectedOption().getText());
    }

}

