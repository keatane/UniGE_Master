package TestCases.PO;

// Generated by Selenium IDE
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class StatesPage {

    WebDriver driver;

    JavascriptExecutor js;

    Map<String, Object> vars;

    public StatesPage(WebDriver driver, JavascriptExecutor js, Map<String, Object> vars) {
        this.driver = driver;
        this.js = js;
        this.vars = vars;
    }

    public void addState() {
        By elem = By.id("page-header-desc-state-new_state");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public List<WebElement> verifyStatesAdditionSuccess() {
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

    public void addState_1() {
        addState();
        click_CSSSELECTOR_process_icon_save();
    }
}
