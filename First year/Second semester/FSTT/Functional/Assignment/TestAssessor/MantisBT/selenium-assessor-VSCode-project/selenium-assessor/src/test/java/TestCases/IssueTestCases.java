package TestCases;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.By;
import TestCases.PO.BasePagePO;
import TestCases.PO.DashboardPage;
import TestCases.PO.LoginFormPO;
import TestCases.PO.ViewIssuesPage;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IssueTestCases extends DriverLifeCycleSetting{

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
    public void testFeature7() {
        /*
         *  Feature: Issue management
            Scenario: Adds a new issue
         */
        DashboardPage dashboardPage = login();
        ViewIssuesPage viewIssues = dashboardPage.clickReportIssue()
        .addIssue("2", "random", "crash", "immediate", "Summary001", "Description001"); // create new issue
        assertEquals("Category001", viewIssues.find(By.xpath("//*[@id=\"buglist\"]/tbody/tr[4]/td[6]")).getText());
        assertEquals("crash", viewIssues.find(By.xpath("//*[@id=\"buglist\"]/tbody/tr[4]/td[7]/span")).getText());
        assertEquals("Summary001", viewIssues.find(By.xpath("//*[@id=\"buglist\"]/tbody/tr[4]/td[10]")).getText());
        logOut(viewIssues);
    }

    @Test
    public void testFeature8() {
        /*
         *  Feature: Issue management
            Scenario: Assigns an issue to the administrator
         */
        DashboardPage dashboardPage = login();
        ViewIssuesPage viewIssues = dashboardPage.clickViewIssues().selectIssue().assignSelectedIssuetoAdmin();
        assertEquals("assigned (administrator)", viewIssues.find(By.xpath("//*[@id=\"buglist\"]/tbody/tr[4]/td[8]")).getText());
        logOut(viewIssues);
    }

    @Test
    public void testFeature9() {
        /*
         *  Feature: Issue management
            Scenario: Changes the priority of an issue
         */
        DashboardPage dashboardPage = login();
        ViewIssuesPage viewIssues = dashboardPage.clickViewIssues().updatePriority("low");
        assertEquals("low", viewIssues.find(By.xpath("/html/body/table[3]/tbody/tr[7]/td[2]")).getText());
        logOut(viewIssues);
    }
}
