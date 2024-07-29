package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPO extends BasePagePO{

    private By usernameInput = By.name("login");
    private By passwordInput = By.name("password");
    private By submitButton = By.xpath("//*[@id=\"loginBox\"]/form/fieldset/button");

    public LoginFormPO(WebDriver driver) {
        super(driver);
        visit("http://localhost:3000/claroline11110/index.php");
    }

    public BasePagePO with(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(submitButton);

        if (getUrl().equals("http://localhost:3000/claroline11110/index.php"))
            return this;
        else return new LoginFormPO(driver);
    }
}
