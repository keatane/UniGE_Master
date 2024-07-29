package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class IssueDetails extends DashboardPage{

    public IssueDetails(WebDriver driver) {
        super(driver);
    }

    public ViewIssuesPage assignSelectedIssuetoAdmin(){
        click(By.name("action"));
        Select action = new Select(this.find(By.name("action")));
        action.selectByValue("ASSIGN");
        return clickOk();
    }

    public ViewIssuesPage clickOk(){
        By submitButton = By.xpath("//*[@id=\"buglist\"]/tbody/tr[5]/td/span[1]/input[2]");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
        return clickAssign();
    }

    public ViewIssuesPage clickAssign(){
        By submitButton = By.xpath("/html/body/div[2]/form/table/tbody/tr[2]/td/input");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
        return new ViewIssuesPage(driver);
    }
}
