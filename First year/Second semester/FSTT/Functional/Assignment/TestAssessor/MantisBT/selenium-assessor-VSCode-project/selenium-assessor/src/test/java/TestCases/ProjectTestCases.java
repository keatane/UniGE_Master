package TestCases;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.By;
import TestCases.PO.BasePagePO;
import TestCases.PO.DashboardPage;
import TestCases.PO.LoginFormPO;
import TestCases.PO.ManageProjectsPage;
import TestCases.PO.ProjectDetails;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectTestCases extends DriverLifeCycleSetting{

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
    public void testFeature3() {
        /*
         *  Feature: Project management
            Scenario: Adds a new project
         */
        DashboardPage dashboardPage = login();
        ProjectDetails details = dashboardPage.clickManageLink().clickManageProjectLink().clickCreateNewProject()
        .fillDetails("Project001", "release", "public", "Description"); // create new project
        ManageProjectsPage page = details.clickManageLink().clickManageProjectLink();
        assertEquals("Project001", page.find(By.xpath("/html/body/table[3]/tbody/tr[3]/td[1]/a")).getText());
        assertEquals("release", page.find(By.xpath("/html/body/table[3]/tbody/tr[3]/td[2]")).getText());
        assertEquals("public", page.find(By.xpath("/html/body/table[3]/tbody/tr[3]/td[4]")).getText());
        assertEquals("Description", page.find(By.xpath("/html/body/table[3]/tbody/tr[3]/td[5]")).getText());
        logOut(page);
    }

    @Test
    public void testFeature4() {
        /*
         *  Feature: Project management
            Scenario: Tries to add an existing project and fails
         */
        DashboardPage dashboardPage = login();
        ProjectDetails details = dashboardPage.clickManageLink().clickManageProjectLink().clickCreateNewProject()
        .fillDetails("Project001", "release", "public", "Description"); // create new project
        assertEquals("A project with that name already exists. Please go back and enter a different name.", details.find(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td/p")).getText());
        logOut(details);
    }

    @Test
    public void testFeature5() {
        /*
         *  Feature: Project management
            Scenario: Adds a category
         */
        DashboardPage dashboardPage = login();
        ManageProjectsPage manageProjects = dashboardPage.clickManageLink().clickManageProjectLink();
        manageProjects.createNewCategory("Category001"); // create new category
        assertEquals("Category001", manageProjects.find(By.xpath("/html/body/a/div/table/tbody/tr[3]/td[1]")).getText());
        logOut(manageProjects);
    }

    @Test
    public void testFeature6() {
        /*
         *  Feature: Project management
            Scenario: Tries to add an existing category and fails
         */
        DashboardPage dashboardPage = login();
        ManageProjectsPage manageProjects = dashboardPage.clickManageLink().clickManageProjectLink();
        manageProjects.createSameCategory("Category001"); // create same category
        assertEquals("A category with that name already exists.", manageProjects.find(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td/p")).getText());
        logOut(manageProjects);
    }
}
