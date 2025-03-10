package TestCases.PO;

// Generated by Selenium IDE
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class LoginPage {

    WebDriver driver;

    JavascriptExecutor js;

    Map<String, Object> vars;

    public LoginPage(WebDriver driver, JavascriptExecutor js, Map<String, Object> vars) {
        this.driver = driver;
        this.js = js;
        this.vars = vars;
    }

    public void set_NAME_username(String key1) {
        By elem = By.name("username");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
        driver.findElement(elem).clear();
        driver.findElement(elem).sendKeys(key1);
    }

    public void set_NAME_password(String key2) {
        By elem = By.name("password");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
        driver.findElement(elem).clear();
        driver.findElement(elem).sendKeys(key2);
    }

    public void click_CSSSELECTOR_uk_button() {
        By elem = By.cssSelector(".uk-button");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void adminLogin(String key1, String key2) {
        set_NAME_username(key1);
        set_NAME_password(key2);
        click_CSSSELECTOR_uk_button();
    }
}
