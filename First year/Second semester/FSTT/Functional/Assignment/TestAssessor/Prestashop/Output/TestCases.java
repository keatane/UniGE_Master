package TestCases;

import org.junit.BeforeClass;
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
import TestCases.PO.*;

public class TestCases {

    @BeforeClass()
    public static void setup() {
        System.setProperty("webdriver.gecko.driver", "InsertGeckoPathHere");
    }

    private WebDriver driver;

    private Map<String, Object> vars;

    private JavascriptExecutor js;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testFeature3() {
        driver.get("http://localhost:8080/administrator/");
        driver.manage().window().setSize(new Dimension(1280, 720));
        LoginPage _LoginPage = new LoginPage(driver, js, vars);
        _LoginPage.adminLogin("admin@prestashop.com", "password");
        DashboardPage _DashboardPage = new DashboardPage(driver, js, vars);
        _DashboardPage.ClickLocalization();
        StatesPage _StatesPage = new StatesPage(driver, js, vars);
        _StatesPage.addState();
        AddNewStatePage _AddNewStatePage = new AddNewStatePage(driver, js, vars);
        _AddNewStatePage.addDetails("Liguria", "1121", "Europe");
        assert (_StatesPage.verifyStatesAdditionSuccess().size() > 0);
        ProductsPage _ProductsPage = new ProductsPage(driver, js, vars);
        _ProductsPage.logOut();
    }

    @Test
    public void testFeature4() {
        driver.get("http://localhost:8080/administrator/");
        driver.manage().window().setSize(new Dimension(1280, 720));
        LoginPage _LoginPage = new LoginPage(driver, js, vars);
        _LoginPage.adminLogin("admin@prestashop.com", "password");
        DashboardPage _DashboardPage = new DashboardPage(driver, js, vars);
        _DashboardPage.ClickLocalization();
        StatesPage _StatesPage = new StatesPage(driver, js, vars);
        _StatesPage.addState_1();
        AddNewStatePage _AddNewStatePage = new AddNewStatePage(driver, js, vars);
        _AddNewStatePage.verifyRedBox();
        assertThat(_AddNewStatePage.set_CSSSELECTOR_ollinth_child1_1(), is("The iso_code field is required."));
        assertThat(_AddNewStatePage.set_CSSSELECTOR_ollinth_child2(), is("The name field is required."));
        ProductsPage _ProductsPage = new ProductsPage(driver, js, vars);
        _ProductsPage.logOut();
    }

    @Test
    public void testFeature0() {
        driver.get("http://localhost:8080/administrator/");
        driver.manage().window().setSize(new Dimension(1280, 720));
        LoginPage _LoginPage = new LoginPage(driver, js, vars);
        _LoginPage.adminLogin("admin@prestashop.com", "password");
        DashboardPage _DashboardPage = new DashboardPage(driver, js, vars);
        _DashboardPage.ClickCatalog();
        ProductsPage _ProductsPage = new ProductsPage(driver, js, vars);
        _ProductsPage.addProduct();
        AddProductPage _AddProductPage = new AddProductPage(driver, js, vars);
        _AddProductPage.setProductName("Blue Jacket3");
        assert (_ProductsPage.verifyAddingProductSuccessfully().size() > 0);
        _ProductsPage.logOut();
    }

    @Test
    public void testFeature1() {
        driver.get("http://localhost:8080/administrator/");
        driver.manage().window().setSize(new Dimension(1280, 720));
        LoginPage _LoginPage = new LoginPage(driver, js, vars);
        _LoginPage.adminLogin("admin@prestashop.com", "password");
        DashboardPage _DashboardPage = new DashboardPage(driver, js, vars);
        _DashboardPage.ClickCatalog();
        ProductsPage _ProductsPage = new ProductsPage(driver, js, vars);
        _ProductsPage.addProduct_1();
        assertThat(_ProductsPage.set_CSSSELECTOR_ollinth_child1(), is("This link_rewrite field is required at least in English (English)"));
        assertThat(_ProductsPage.set_CSSSELECTOR_ollinth_child2(), is("This name field is required at least in English (English)"));
        _ProductsPage.logOut();
    }

    @Test
    public void testFeature2() {
        driver.get("http://localhost:8080/administrator/");
        driver.manage().window().setSize(new Dimension(1280, 720));
        LoginPage _LoginPage = new LoginPage(driver, js, vars);
        _LoginPage.adminLogin("admin@prestashop.com", "password");
        DashboardPage _DashboardPage = new DashboardPage(driver, js, vars);
        _DashboardPage.ClickCatalog_1();
        EditProductPage _EditProductPage = new EditProductPage(driver, js, vars);
        _EditProductPage.changeProductName("Deep Blue Jacket");
        ProductsPage _ProductsPage = new ProductsPage(driver, js, vars);
        assert (_ProductsPage.verifyAddingProductSuccessfully().size() > 0);
        _ProductsPage.logOut();
    }

    @Test
    public void testFeature5() {
        driver.get("http://localhost:8080/administrator/");
        driver.manage().window().setSize(new Dimension(1280, 720));
        LoginPage _LoginPage = new LoginPage(driver, js, vars);
        _LoginPage.adminLogin("admin@prestashop.com", "password");
        DashboardPage _DashboardPage = new DashboardPage(driver, js, vars);
        _DashboardPage.ClickCatalog();
        ProductsPage _ProductsPage = new ProductsPage(driver, js, vars);
        _ProductsPage.click_ID_page_header_desc_product_new_product();
        AddNewStatePage _AddNewStatePage = new AddNewStatePage(driver, js, vars);
        _AddNewStatePage.selectPrices();
        AddProductPage _AddProductPage = new AddProductPage(driver, js, vars);
        _AddProductPage.setPreTaxRetailPrice("10");
        assertThat(_AddProductPage.set_ID_priceTI(), is("12.20"));
        _ProductsPage.logOut();
    }

    @Test
    public void testFeature6() {
        driver.get("http://localhost:8080/administrator/");
        driver.manage().window().setSize(new Dimension(1280, 720));
        LoginPage _LoginPage = new LoginPage(driver, js, vars);
        _LoginPage.adminLogin("admin@prestashop.com", "password");
        DashboardPage _DashboardPage = new DashboardPage(driver, js, vars);
        _DashboardPage.ClickCatalog();
        ProductsPage _ProductsPage = new ProductsPage(driver, js, vars);
        _ProductsPage.click_ID_page_header_desc_product_new_product();
        AddNewStatePage _AddNewStatePage = new AddNewStatePage(driver, js, vars);
        _AddNewStatePage.selectPrices();
        AddProductPage _AddProductPage = new AddProductPage(driver, js, vars);
        _AddProductPage.setPreTaxRetailPrice_1("10", "IT Reduced Rate (10%)");
        assertThat(_AddProductPage.set_ID_priceTI(), is("11.00"));
        _ProductsPage.logOut();
    }

    @Test
    public void testFeature7() {
        driver.get("http://localhost:8080/administrator/");
        driver.manage().window().setSize(new Dimension(1280, 720));
        LoginPage _LoginPage = new LoginPage(driver, js, vars);
        _LoginPage.adminLogin("admin@prestashop.com", "password");
        DashboardPage _DashboardPage = new DashboardPage(driver, js, vars);
        _DashboardPage.ClickCatalog_2();
        ProductFeaturesPage _ProductFeaturesPage = new ProductFeaturesPage(driver, js, vars);
        _ProductFeaturesPage.addFeature();
        AddNewFeaturePage _AddNewFeaturePage = new AddNewFeaturePage(driver, js, vars);
        _AddNewFeaturePage.setFeatureName("Strong");
        assert (_ProductFeaturesPage.verifySuccessfulCreation().size() > 0);
        ProductsFeaturesPage _ProductsFeaturesPage = new ProductsFeaturesPage(driver, js, vars);
        _ProductsFeaturesPage.logOut();
    }

    @Test
    public void testFeature8() {
        driver.get("http://localhost:8080/administrator/");
        driver.manage().window().setSize(new Dimension(1280, 720));
        LoginPage _LoginPage = new LoginPage(driver, js, vars);
        _LoginPage.adminLogin("admin@prestashop.com", "password");
        DashboardPage _DashboardPage = new DashboardPage(driver, js, vars);
        _DashboardPage.ClickCatalog_2();
        ProductFeaturesPage _ProductFeaturesPage = new ProductFeaturesPage(driver, js, vars);
        _ProductFeaturesPage.addFeature_1();
        assert (_ProductFeaturesPage.set_CSSSELECTOR_alert_danger().size() > 0);
        _ProductFeaturesPage.logOut();
    }

    @Test
    public void testFeature9() {
        driver.get("http://localhost:8080/administrator/");
        driver.manage().window().setSize(new Dimension(1280, 720));
        LoginPage _LoginPage = new LoginPage(driver, js, vars);
        _LoginPage.adminLogin("admin@prestashop.com", "password");
        DashboardPage _DashboardPage = new DashboardPage(driver, js, vars);
        _DashboardPage.ClickCatalog_3();
        ProductAttributesPage _ProductAttributesPage = new ProductAttributesPage(driver, js, vars);
        _ProductAttributesPage.addNewAttribute();
        AddNewAttributePage _AddNewAttributePage = new AddNewAttributePage(driver, js, vars);
        _AddNewAttributePage.addAttributeDetails("Quantity", "Qnt");
        assert (_ProductAttributesPage.verifySuccessfulCreation().size() > 0);
        ProductsPage _ProductsPage = new ProductsPage(driver, js, vars);
        _ProductsPage.logOut();
    }
}