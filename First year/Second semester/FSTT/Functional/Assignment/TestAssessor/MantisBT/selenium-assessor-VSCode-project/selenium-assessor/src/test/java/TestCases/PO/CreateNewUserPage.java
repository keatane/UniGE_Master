package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateNewUserPage extends BasePagePO{

    public CreateNewUserPage(WebDriver driver) {
        super(driver);
    }

    public UserDetails fillDetails(String username, String realname, String email, String access_level){
        type(By.name("username"), username);
        type(By.name("realname"), realname);
        type(By.name("email"), email);
        click(By.name("access_level"));
        Select dropdown = new Select(this.find(By.name("access_level")));
        dropdown.selectByVisibleText(access_level);
        return clickSave();
    }

    public UserDetails clickSave(){
        By submitButton = By.xpath("/html/body/div[3]/form/table/tbody/tr[8]/td/input");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
        return new UserDetails(driver);
    }
}
