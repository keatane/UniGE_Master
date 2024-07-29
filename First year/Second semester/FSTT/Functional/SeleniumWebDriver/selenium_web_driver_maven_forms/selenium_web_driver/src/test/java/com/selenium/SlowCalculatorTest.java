package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class SlowCalculatorTest  {
    private WebDriver driver;
    private int toWait = 2000;

    @BeforeAll
    static void setUpClass(){
        // System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
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
    void test_slow_calculator_thread_sleep() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html";
        driver.get(baseUrl);
        Thread.sleep(toWait);
        // 1+3
        driver.findElement(By.xpath("//span[text()='1']")).click(); Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text()='+']")).click(); Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text()='3']")).click(); Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text()='=']")).click(); Thread.sleep(1000);

        Thread.sleep(5000);

        assertEquals("4", driver.findElement(By.className("screen")).getText());
    }

    @Test
    void test_slow_calculator_implicit_wait() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html";
        driver.get(baseUrl);
        // 1+3
        driver.findElement(By.xpath("//span[text()='1']")).click();
        driver.findElement(By.xpath("//span[text()='+']")).click();
        driver.findElement(By.xpath("//span[text()='3']")).click();
        driver.findElement(By.xpath("//span[text()='=']")).click();

        assertEquals("4", driver.findElement(By.xpath("//div[text()='4']")).getText()); // different from button that are spans
    }

    @Test
    void test_slow_calculator_explicit_wait() throws InterruptedException {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html";
        driver.get(baseUrl);
        // 1+3
        driver.findElement(By.xpath("//span[text()='1']")).click();
        driver.findElement(By.xpath("//span[text()='+']")).click();
        driver.findElement(By.xpath("//span[text()='3']")).click();
        driver.findElement(By.xpath("//span[text()='=']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBe((By.className("screen")), "4"));

        assertEquals("4", driver.findElement(By.className("screen")).getText());
    }
}
