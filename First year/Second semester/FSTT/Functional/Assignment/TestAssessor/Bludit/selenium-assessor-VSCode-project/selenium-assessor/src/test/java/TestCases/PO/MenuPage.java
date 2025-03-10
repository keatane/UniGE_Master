package TestCases.PO;

// Generated by Selenium IDE
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class MenuPage {

    WebDriver driver;

    JavascriptExecutor js;

    Map<String, Object> vars;

    public MenuPage(WebDriver driver, JavascriptExecutor js, Map<String, Object> vars) {
        this.driver = driver;
        this.js = js;
        this.vars = vars;
    }

    public void selectNewContent() {
        By elem = By.linkText("New content");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void click_LINKTEXT_Content() {
        By elem = By.linkText("Content");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void click_LINKTEXT_TestContent() {
        By elem = By.linkText("Test Content");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void click_CSSSELECTOR_linth_child7sidebar_button() {
        By elem = By.cssSelector("li:nth-child(7) > .sidebar-button");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void click_ID_jsslug() {
        By elem = By.id("jsslug");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void selectContent(boolean key1, boolean key2, boolean key3, boolean key4, boolean key5) {
        if (key1 != false)
            click_LINKTEXT_Content();
        if (key2 != false)
            click_LINKTEXT_TestContent();
        if (key3 != false)
            click_CSSSELECTOR_linth_child7sidebar_button();
        if (key4 != false)
            click_ID_jsslug();
        if (key5 != false)
            click_CSSSELECTOR_bl_publish_view();
    }

    public void click_CSSSELECTOR_bl_publish_view() {
        By elem = By.cssSelector(".bl-publish-view");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void selectNewContent_1() {
        By elem = By.cssSelector(".uk-row-first > .uk-panel:nth-child(1) a");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }

    public void selectUsers() {
        By elem = By.linkText("Users");
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
    }
}
