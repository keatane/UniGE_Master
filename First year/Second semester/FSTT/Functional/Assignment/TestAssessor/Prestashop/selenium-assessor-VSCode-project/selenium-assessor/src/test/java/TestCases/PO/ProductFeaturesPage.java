package TestCases.PO;

// Generated by Selenium IDE
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class ProductFeaturesPage {

    WebDriver driver;

    JavascriptExecutor js;

    Map<String, Object> vars;

    public ProductFeaturesPage(WebDriver driver, JavascriptExecutor js, Map<String, Object> vars) {
        this.driver = driver;
        this.js = js;
        this.vars = vars;
    }

    public void addFeature() {
        By elem = By.cssSelector("#page-header-desc-feature-new_feature > .process-icon-new");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public List<WebElement> verifySuccessfulCreation() {
        By elem = By.cssSelector(".alert-success:nth-child(1)");
        MyUtils.WaitForElementLoaded(driver, elem);
        List<WebElement> elements = driver.findElements(elem);
        return elements;
    }

    public void click_CSSSELECTOR_process_icon_save() {
        By elem = By.cssSelector(".process-icon-save");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void addFeature_1() {
        addFeature();
        click_CSSSELECTOR_process_icon_save();
    }

    public List<WebElement> set_CSSSELECTOR_alert_danger() {
        By elem = By.cssSelector(".alert-danger");
        MyUtils.WaitForElementLoaded(driver, elem);
        List<WebElement> elements = driver.findElements(elem);
        return elements;
    }

    public void click_CSSSELECTOR_employee_namestring_long() {
        By elem = By.cssSelector(".employee_name > .string-long");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void click_ID_header_logout() {
        By elem = By.id("header_logout");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void logOut() {
        click_CSSSELECTOR_employee_namestring_long();
        click_ID_header_logout();
    }
}
