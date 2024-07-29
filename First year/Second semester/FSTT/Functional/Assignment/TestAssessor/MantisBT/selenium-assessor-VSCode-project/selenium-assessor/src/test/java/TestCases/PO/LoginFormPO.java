package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPO extends BasePagePO{

    private By usernameInput = By.name("username");
    private By passwordInput = By.name("password");
    private By submitButton = By.xpath("/html/body/div[3]/form/table/tbody/tr[6]/td/input");
    private String url = "http://localhost:3000/mantisbt/";

    public LoginFormPO(WebDriver driver) {
        super(driver);
        visit(url);
    }

    public BasePagePO with(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(submitButton);

        if (getUrl().equals(url))
            return this;
        else return new LoginFormPO(driver);
    }
}
