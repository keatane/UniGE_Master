package Chrome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class HelloWorld {

    private WebDriver driver;

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
        // if (driver != null)
        driver.quit();
    }

    @Test
    void testTitle() {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        String expectedTitle = "Hands-On Selenium WebDriver with Java";
        String actualTitle = "";

        // launch Chrome to the Base URL and get the title
        driver.get(baseUrl);
        actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void testLoginOK() {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/login-form.html";

        // launch Chrome to the Base URL and get the title
        driver.get(baseUrl);
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("user");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        assertTrue(driver.findElement(By.id("success")).isDisplayed());
        assertEquals(driver.findElement(By.id("success")).getText(), "Login successful");
    }

    @Test
    void testLoginNoOK() {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/login-form.html";

        // launch Chrome to the Base URL and get the title
        driver.get(baseUrl);
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("error");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        assertTrue(driver.findElement(By.id("invalid")).isDisplayed());
    }
}

