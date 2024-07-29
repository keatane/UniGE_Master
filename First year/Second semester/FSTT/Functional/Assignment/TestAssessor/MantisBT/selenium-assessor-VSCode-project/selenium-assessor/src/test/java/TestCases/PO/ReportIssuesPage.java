package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ReportIssuesPage extends BasePagePO{

    public ReportIssuesPage(WebDriver driver) {
        super(driver);
    }

    public ViewIssuesPage addIssue(String category, String reproducibility, String severity, String priority, String summary, String description){
        click(By.name("category_id"));
        new Select(this.find(By.name("category_id"))).selectByValue("2");
        click(By.name("reproducibility"));
        new Select(this.find(By.name("reproducibility"))).selectByVisibleText("random");
        click(By.name("severity"));
        new Select(this.find(By.name("severity"))).selectByVisibleText("crash");
        click(By.name("priority"));
        new Select(this.find(By.name("priority"))).selectByVisibleText("immediate");
        type(By.name("summary"), summary);
        type(By.name("description"), description);
        return clickReport(); 
    }

    public ViewIssuesPage clickReport(){
        By submitButton = By.cssSelector("body > div:nth-child(5) > form > table > tbody > tr:nth-child(16) > td.center > input");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
        return new ViewIssuesPage(driver);
    }
}
