package Edge;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class HelloWorld {

    private WebDriver driver;

    @BeforeAll
    static void setUpClass(){
        System.setProperty("webdriver.edge.driver", "drivers\\msedgedriver.exe");
    }
    
    @BeforeEach
    void setup() {
        driver = new EdgeDriver();
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
}

