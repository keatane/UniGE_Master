package TestCases;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverLifeCycleSetting {
    protected WebDriver driver;

    @Before
    public void beforeEach() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void afterEach() {
        if (driver != null)
            driver.quit();
    }
}
