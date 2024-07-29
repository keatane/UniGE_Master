package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateNewUserPage extends BasePagePO{

    public CreateNewUserPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewUserPage addDetails(String lastname, String firstname, String username, String password, String password_conf, String radio_button){
        type(By.id("lastname"), "Name001");
        type(By.id("firstname"), "firstname001");
        type(By.id("username"), "user001");
        type(By.id("password"), "password001");
        type(By.id("password_conf"), "password001");
        click(By.id(radio_button)); // radio button
        return clickSave();
    }

    public CreateNewUserPage clickSave() {
        By submitButton = By.name("applyChange");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
        return new CreateNewUserPage(driver);
    }

}
