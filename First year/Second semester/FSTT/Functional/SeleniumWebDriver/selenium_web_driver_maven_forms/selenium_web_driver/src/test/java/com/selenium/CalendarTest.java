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

public class CalendarTest  {
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
    void test_calendar() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(baseUrl);
        WebElement date = driver.findElement(By.name("my-date"));
        date.click();
        Thread.sleep(toWait);
        WebElement arrowRight = driver.findElement(By.xpath("/html/body/div/div[1]/table/thead/tr[2]/th[3]"));
        arrowRight.click();
        Thread.sleep(toWait);
        WebElement day = driver.findElement(By.xpath("//td[contains(text(),'15')]"));
        day.click();
        Thread.sleep(toWait);
        String optionValue = date.getAttribute("value");
        assertEquals(optionValue, "06/15/2024");
    }
}
