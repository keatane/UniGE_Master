package TestCases;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;
import java.time.Duration;
import TestCases.PO.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContentTestCases {

    private WebDriver driver;

    private Map<String, Object> vars;

    private JavascriptExecutor js;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        driver.get("http://localhost:8080/admin");
        driver.manage().window().setSize(new Dimension(1280, 720));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* 
     * NOTE: the tests numbers are in index-1 with respect to the cases in the Gherkin directory
     * This choice has been made to make tests execution in ascending name order 
    */
    @Test
    public void testFeature0() {
        /*
         *  Feature: Content management
            Scenario: Add a new content
        */
        new LoginPage(driver, js, vars).adminLogin("admin", "password");
        MenuPage _MenuPage = new MenuPage(driver, js, vars);
        _MenuPage.selectNewContent();
        EditNewContentPage _EditNewContentPage = new EditNewContentPage(driver, js, vars);
        _EditNewContentPage.editTitleAndSave("Test Content");
        ManageContentPage _ManageContentPage = new ManageContentPage(driver, js, vars);
        assert (_ManageContentPage.testNewContentAddedSuccessfully().size() > 0);
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("alert")));
        }
        _ManageContentPage.logOut();
    }

    @Test
    public void testFeature1() {
        /*
         *  Feature: Content management
            Scenario: Change the URL of a content
         */
        new LoginPage(driver, js, vars).adminLogin("admin", "password");
        MenuPage _MenuPage = new MenuPage(driver, js, vars);
        _MenuPage.selectContent(true, true, true, true, false);
        EditContentPage _EditContentPage = new EditContentPage(driver, js, vars);
        _EditContentPage.changeFriendlyUrl("new-post-url");
        ManageContentPage _ManageContentPage = new ManageContentPage(driver, js, vars);
        assertThat(_ManageContentPage.assertUrlChangedSuccessfully(), is("/new-post-url"));
        _ManageContentPage.logOut();
    }

    @Test
    public void testFeature2() {
        /*
         *  Feature: Content management
            Scenario: Change the position of a content
        */
        new LoginPage(driver, js, vars).adminLogin("admin", "password");
        MenuPage _MenuPage = new MenuPage(driver, js, vars);
        _MenuPage.selectContent(true, true, true, false, true);
        EditContentPage _EditContentPage = new EditContentPage(driver, js, vars);
        _EditContentPage.changePosition("30");
        ManageContentPage _ManageContentPage = new ManageContentPage(driver, js, vars);
        _ManageContentPage.selectPublishedContent();
        _EditContentPage.clickAdvanceSection();
        assertThat(_EditContentPage.set_ID_jsposition_1(), is("30"));
        _EditContentPage.logOut();
    }

    @Test
    public void testFeature3() {
        /*
         *  Feature: Content management
            Scenario: Change the parent of a content
         */
        new LoginPage(driver, js, vars).adminLogin("admin", "password");
        MenuPage _MenuPage = new MenuPage(driver, js, vars);
        _MenuPage.click_LINKTEXT_Content();
        ManageContentPage _ManageContentPage = new ManageContentPage(driver, js, vars);
        _ManageContentPage.selectPublishedContent();
        EditContentPage _EditContentPage = new EditContentPage(driver, js, vars);
        _EditContentPage.setParent("Create your own content");
        _EditContentPage.clickAdvanceSection();
        assertThat(_EditContentPage.set_ID_jsparent(), is("create-your-own-content"));
        _EditContentPage.click_ID_jsparent_1();
    }

    @Test
    public void testFeature4() {
        /*
         *  Feature: Content management
            Scenario: Add a new draft
         */
        new LoginPage(driver, js, vars).adminLogin("admin", "password");
        MenuPage _MenuPage = new MenuPage(driver, js, vars);
        _MenuPage.selectNewContent_1();
        EditNewContentPage _EditNewContentPage = new EditNewContentPage(driver, js, vars);
        _EditNewContentPage.editTitleAndSaveDraft("Draft Content");
        ManageContentPage _ManageContentPage = new ManageContentPage(driver, js, vars);
        assert (_ManageContentPage.testDraftAddedSuccessfully().size() > 0);
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("alert")));
        }
        _ManageContentPage.logOut();
    }

    @Test
    public void testFeature5() {
        /*
         *  Feature: Content management
            Scenario: Set a content as sticky
         */
        new LoginPage(driver, js, vars).adminLogin("admin", "password");
        MenuPage _MenuPage = new MenuPage(driver, js, vars);
        _MenuPage.click_LINKTEXT_Content();
        ManageContentPage _ManageContentPage = new ManageContentPage(driver, js, vars);
        _ManageContentPage.selectSetupNewSite();
        EditContentPage _EditContentPage = new EditContentPage(driver, js, vars);
        _EditContentPage.setStickyStatus("Sticky");
        assert (_ManageContentPage.verifyStickyAddedSuccessfully().size() > 0);
        _ManageContentPage.logOut();
    }

    @Test
    public void testFeature6() {
        /*
         *  Feature: Content management
            Scenario: Deletes content
        */
        new LoginPage(driver, js, vars).adminLogin("admin", "password");
        MenuPage _MenuPage = new MenuPage(driver, js, vars);
        _MenuPage.click_LINKTEXT_Content();
        ManageContentPage _ManageContentPage = new ManageContentPage(driver, js, vars);
        _ManageContentPage.selectFollowBludit();
        EditContentPage _EditContentPage = new EditContentPage(driver, js, vars);
        _EditContentPage.deleteContent();
        assert (_EditContentPage.set_LINKTEXT_FollowBludit().size() == 0);
        MyUtils.WaitForElementLoaded(driver, By.id("alert")); // to be forced, otherwise test fails
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("alert")));
        }
        _ManageContentPage.logOut();
    }
}