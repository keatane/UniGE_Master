package TestCases;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.By;
import TestCases.PO.BasePagePO;
import TestCases.PO.DashboardPage;
import TestCases.PO.LoginFormPO;
import TestCases.PO.ManageUsersPage;
import TestCases.PO.UserDetails;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTestCases extends DriverLifeCycleSetting{

    public DashboardPage login() {
        new LoginFormPO(driver).with("administrator", "root");
        return new DashboardPage(driver);
    }

    public void logOut(BasePagePO page){
        page.click(By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[10]"));
    }

    /* 
     * NOTE: the tests numbers are in index-1 with respect to the cases in the Gherkin directory
     * This choice has been made to make tests execution in ascending name order 
    */
    @Test
    public void testFeature0() {
        /*
         *  Feature: User management
            Scenario: Add a new user
         */
        DashboardPage dashboardPage = login();
        UserDetails details = dashboardPage.clickManageLink().clickManageUsersLink().clickCreateNewAccount()
        .fillDetails("username001", "username001", "username@username.it", "updater"); // create new account
        ManageUsersPage page = details.clickManageLink().clickManageUsersLink();
        assertEquals("username001", page.find(By.xpath("/html/body/table[3]/tbody/tr[4]/td[1]/a")).getText());
        assertEquals("username001", page.find(By.xpath("/html/body/table[3]/tbody/tr[4]/td[2]")).getText());
        assertEquals("username@username.it", page.find(By.xpath("/html/body/table[3]/tbody/tr[4]/td[3]")).getText());
        assertEquals("updater", page.find(By.xpath("/html/body/table[3]/tbody/tr[4]/td[4]")).getText());
        logOut(page);
    }

    @Test
    public void testFeature1() {
        /*
         *  Feature: User management
            Scenario: Tries to add an already existing user and fails
         */
        DashboardPage dashboardPage = login();
        UserDetails details = dashboardPage.clickManageLink().clickManageUsersLink().clickCreateNewAccount()
        .fillDetails("username001", "username001", "username@username.it", "updater"); // create new account
        assertEquals("That username is already being used. Please go back and select another one.", details.find(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td/p")).getText());
        logOut(details);
    }

    @Test
    public void testFeature2() {
        /*
         *  Feature: User management
            Scenario: Tries to add an empty user and fails
         */
        DashboardPage dashboardPage = login();
        UserDetails details = dashboardPage.clickManageLink().clickManageUsersLink().clickCreateNewAccount().clickSave(); // create new empty account
        assertEquals("A necessary field \"\" was empty. Please recheck your inputs.", details.find(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td/p")).getText());
        logOut(details);
    }
}
