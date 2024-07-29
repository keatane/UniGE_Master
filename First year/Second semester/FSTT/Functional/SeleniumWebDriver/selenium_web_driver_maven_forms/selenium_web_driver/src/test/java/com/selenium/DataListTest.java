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

public class DataListTest  {
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
    void test_datalist() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(baseUrl);
        WebElement datalist = driver.findElement(By.name("my-datalist"));
        datalist.click();
        Thread.sleep(toWait);
        WebElement option = driver.findElement(By.xpath("//datalist/option[2]"));
        String optionValue = option.getAttribute("value");
        datalist.sendKeys(optionValue);
        Thread.sleep(toWait);
        assertEquals("New York", datalist.getAttribute("value"));
    }
}
