package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateNewProjectPage extends BasePagePO{

    public CreateNewProjectPage(WebDriver driver) {
        super(driver);
    }

    public ProjectDetails fillDetails(String name, String status, String view_state, String description){
        type(By.name("name"), name);
        click(By.name("status"));
        new Select(this.find(By.name("status"))).selectByVisibleText(status);
        click(By.name("view_state"));
        // new Select(this.find(By.name("view_state"))).selectByIndex(0); // select public (selecting by visible text public doesn't work) 
        new Select(this.find(By.name("view_state"))).selectByVisibleText(view_state);
        type(By.name("description"), description);
        return clickSave();
    }

    public ProjectDetails clickSave(){
        By submitButton = By.xpath("/html/body/div[3]/form/table/tbody/tr[8]/td/input");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
        return new ProjectDetails(driver);
    }
}
