package TestCases.PO;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class EditUserDetailsPage {

    WebDriver driver;

    JavascriptExecutor js;

    Map<String, Object> vars;

    public EditUserDetailsPage(WebDriver driver, JavascriptExecutor js, Map<String, Object> vars) {
        this.driver = driver;
        this.js = js;
        this.vars = vars;
    }

    public void clickChangePassword() {
        By elem = By.linkText("Change password");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void set_ID_jsfacebook(String key1) {
        By elem = By.id("jsfacebook");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
        driver.findElement(elem).clear();
        driver.findElement(elem).sendKeys(key1);
    }

    public void set_ID_jsinstagram(String key2) {
        By elem = By.id("jsinstagram");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
        driver.findElement(elem).clear();
        driver.findElement(elem).sendKeys(key2);
    }

    public void click_CSSSELECTOR_uk_button_primary() {
        By elem = By.cssSelector(".uk-button-primary");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void selectUsers() {
        By elem = By.linkText("Users");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void editFacebookAndInstagram(String key1, String key2) {
        set_ID_jsfacebook(key1);
        set_ID_jsinstagram(key2);
        click_CSSSELECTOR_uk_button_primary();
        selectUsers();
    }

    public String verifyFacebookAdded() {
        String value = driver.findElement(elem).getAttribute("value");
        return value;
    }

    public void logOut() {
        By elem = By.linkText("Log out");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }
}
