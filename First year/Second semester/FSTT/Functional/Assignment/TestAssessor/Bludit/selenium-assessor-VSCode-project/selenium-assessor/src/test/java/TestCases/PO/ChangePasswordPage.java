package TestCases.PO;

// Generated by Selenium IDE
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;
import java.time.Duration;

public class ChangePasswordPage {

    WebDriver driver;

    JavascriptExecutor js;

    Map<String, Object> vars;

    public ChangePasswordPage(WebDriver driver, JavascriptExecutor js, Map<String, Object> vars) {
        this.driver = driver;
        this.js = js;
        this.vars = vars;
    }

    public void set_ID_jsnew_password(String key1) {
        By elem = By.id("jsnew_password");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
        driver.findElement(elem).clear();
        driver.findElement(elem).sendKeys(key1);
    }

    public void set_ID_jsconfirm_password(String key2) {
        By elem = By.id("jsconfirm_password");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
        driver.findElement(elem).clear();
        driver.findElement(elem).sendKeys(key2);
    }

    public void click_CSSSELECTOR_uk_button_primary() {
        By elem = By.cssSelector(".uk-button-primary");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            elem = By.id("alert");
            MyUtils.WaitForElementLoaded(driver, elem);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("alert")));
        }
    }

    public void changePasswordAndSave(String key1, String key2) {
        set_ID_jsnew_password(key1);
        set_ID_jsconfirm_password(key2);
        click_CSSSELECTOR_uk_button_primary();
    }
}
