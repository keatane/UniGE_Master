package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ViewIssuesPage extends BasePagePO{

    public ViewIssuesPage(WebDriver driver) {
        super(driver);
    }

    public IssueDetails selectIssue(){
        click(By.xpath("//*[@id=\"buglist\"]/tbody/tr[4]/td[1]/input"));
        return new IssueDetails(driver);
    }

    public ViewIssuesPage updatePriority(String priority){
        click(By.xpath("//*[@id=\"buglist\"]/tbody/tr[4]/td[2]/a/img"));
        click(By.name("priority"));
        new Select(find(By.name("priority"))).selectByVisibleText(priority);
        return clickUpdateInformation();
    } 

    public ViewIssuesPage clickUpdateInformation(){
        By submitButton = By.xpath("/html/body/form/table/tbody/tr[18]/td/input");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
        return new ViewIssuesPage(driver);
    }
}
