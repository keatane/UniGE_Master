package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageUsersPage extends BasePagePO{

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewUserPage clickCreateNewAccount(){
        By elem = By.xpath("/html/body/table[3]/tbody/tr[1]/td[1]/form/input[2]"); // manage link
        MyUtils.WaitForElementLoaded(driver, elem);
        driver.findElement(elem).click();
        return new CreateNewUserPage(driver);
    }
}
