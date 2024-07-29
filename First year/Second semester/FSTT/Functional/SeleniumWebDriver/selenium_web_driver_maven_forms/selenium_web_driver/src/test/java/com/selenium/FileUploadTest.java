package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class FileUploadTest  {
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
    void test_file_upload() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(baseUrl);
        WebElement inputFile = driver.findElement(By.name("my-file"));
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("tempfile", ".tmp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (tempFile == null) {
            fail("Temp file not created");
        }
        String filename = tempFile.toAbsolutePath().toString();
        System.out.println("filename: " + filename);
        Thread.sleep(toWait);
        inputFile.sendKeys(filename);
        Thread.sleep(toWait);
        driver.findElement(By.tagName("form")).submit();
        Thread.sleep(toWait);
        assertNotEquals(driver.getCurrentUrl(), "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
    }
}
