package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManagePage extends BasePagePO{

    public ManagePage(WebDriver driver) {
        super(driver);
    }

    public ManageUsersPage clickManageUsersLink(){
        By elem = By.xpath("/html/body/div[2]/p/span[1]/a");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new ManageUsersPage(driver);
    }

    public ManageProjectsPage clickManageProjectLink(){
        By elem = By.xpath("/html/body/div[2]/p/span[2]/a");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new ManageProjectsPage(driver);
    }
}
