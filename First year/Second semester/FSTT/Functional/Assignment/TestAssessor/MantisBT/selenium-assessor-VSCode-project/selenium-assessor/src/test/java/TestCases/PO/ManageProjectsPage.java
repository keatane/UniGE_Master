package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageProjectsPage extends BasePagePO{

    public ManageProjectsPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewProjectPage clickCreateNewProject(){
        By elem = By.xpath("/html/body/table[3]/tbody/tr[1]/td/form/input[2]");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new CreateNewProjectPage(driver);
    }

    public void createNewCategory(String name){
        type(By.name("name"), name);
        By submitButton = By.xpath("/html/body/a/div/table/tbody/tr[4]/td/form/input[4]");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
    }

    // To be created since the xpath of the submit button changes after the addition of the first category
    public void createSameCategory(String name){
        type(By.name("name"), name);
        By submitButton = By.xpath("/html/body/a/div/table/tbody/tr[5]/td/form/input[4]");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
    }
}
