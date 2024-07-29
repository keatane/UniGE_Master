package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePagePO{

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public ManagePage clickManageLink(){
        By elem = By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[8]");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new ManagePage(driver);
    }

    public ReportIssuesPage clickReportIssue(){
        By elem = By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[4]");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new ReportIssuesPage(driver);
    }

    public ViewIssuesPage clickViewIssues(){
        By elem = By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[3]");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new ViewIssuesPage(driver);
    }

    
}
