package TestCases;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.By;
import TestCases.PO.BasePagePO;
import TestCases.PO.CreateNewUserPage;
import TestCases.PO.DashboardPage;
import TestCases.PO.ListUsersPage;
import TestCases.PO.LoginFormPO;

import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTestCases extends DriverLifeCycleSetting{

    public DashboardPage login() {
        LoginFormPO loginForm = new LoginFormPO(driver);
        loginForm.with("admin", "admin");
        return new DashboardPage(driver);
    }

    public DashboardPage userlogin() {
        LoginFormPO loginForm = new LoginFormPO(driver);
        loginForm.with("user001", "password001");
        return new DashboardPage(driver);
    }

    public void logOut(BasePagePO page) {
        page.click(By.xpath("//*[@id=\"userBannerRight\"]/ul/li[3]/span/a"));
    }

    /* 
     * NOTE: the tests numbers are in index-1 with respect to the cases in the Gherkin directory
     * This choice has been made to make tests execution in ascending name order 
    */
    @Test
    public void testFeature0() {
        /*
         *  Feature: User management
            Scenario: Adds a user to the system
         */
        DashboardPage dashboardPage = login();
        CreateNewUserPage page = dashboardPage.clickPlatformAdministrationLink().clickCreateNewUserLink()
        .addDetails("Name001", "firstname001", "user001", "password001", "password001", "student");
        assertEquals("The new user has been sucessfully created", page.find(By.xpath("//*[@id=\"claroBody\"]/div[2]/div[1]")).getText());
        logOut(page);
    }

    @Test
    public void testFeature1() {
        /*
         *  Feature: User search
            Scenario: Search a user in the system
         */
        DashboardPage dashboardPage = login();
        ListUsersPage page = dashboardPage.clickPlatformAdministrationLink().searchUser("user001");
        assertEquals("Name001", page.find(By.id("L0")).getText());
        assertEquals("firstname001", page.find(By.xpath("//*[@id=\"claroBody\"]/table[2]/tbody/tr/td[3]")).getText());
        assertEquals("User", page.find(By.xpath("//*[@id=\"claroBody\"]/table[2]/tbody/tr/td[6]")).getText());
        logOut(page);
    }

    @Test
    public void testFeature2() {
        /*
         *  Feature: Login
            Scenario: Log in as user "user001"
         */
        DashboardPage page = userlogin();
        assertEquals("firstname001 Name001", page.find(By.xpath("//*[@id=\"userProfileBox\"]/h3/span")).getText());
        logOut(page);
    }
}
