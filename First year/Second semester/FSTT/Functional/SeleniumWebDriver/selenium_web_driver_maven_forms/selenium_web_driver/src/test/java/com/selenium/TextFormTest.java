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
import org.junit.jupiter.api.Disabled;

public class TextFormTest  {
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
    void test_text_input() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(baseUrl);
        WebElement input = driver.findElement(By.id("my-text-id"));
        Thread.sleep(toWait);
        input.sendKeys("ciao");
        assertEquals("ciao", input.getAttribute("value"));
        // the following does not work
        // assertEquals("ciao", input.getText());
        Thread.sleep(toWait);
        input.clear();
        Thread.sleep(toWait);        
    }

    @Test 
    void test_password() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(baseUrl);
        WebElement password = driver.findElement(By.name("my-password"));
        Thread.sleep(toWait);
        password.sendKeys("password");
        assertEquals("password", password.getAttribute("value"));
        Thread.sleep(toWait);
        password.clear();
        Thread.sleep(toWait);
    }

    @Test
    void test_text_area() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(baseUrl);
        WebElement textArea = driver.findElement(By.name("my-textarea"));
        Thread.sleep(toWait);
        textArea.sendKeys("ciao");
        assertEquals("ciao", textArea.getAttribute("value"));
        Thread.sleep(toWait);
        textArea.clear();
        Thread.sleep(toWait);
    }

    @Test
    @Disabled
    void test_disabled_input() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(baseUrl);
        WebElement disabledInput = driver.findElement(By.name("my-disabled"));
        disabledInput.sendKeys("ciao"); // it will fail since it is not interactable
        Thread.sleep(toWait);
    }

    @Test
    void test_readonly_input() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(baseUrl);
        WebElement readOnlyInput = driver.findElement(By.name("my-readonly"));
        readOnlyInput.sendKeys("ciao"); 
        assertNotEquals("ciao", readOnlyInput.getAttribute("value"));// will fail since it is read-only
        Thread.sleep(toWait);
    }
}

