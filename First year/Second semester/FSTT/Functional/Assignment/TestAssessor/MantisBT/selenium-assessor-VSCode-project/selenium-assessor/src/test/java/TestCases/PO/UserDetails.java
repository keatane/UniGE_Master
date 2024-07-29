package TestCases.PO;

import org.openqa.selenium.WebDriver;

public class UserDetails extends DashboardPage{

    public UserDetails(WebDriver driver) {
        super(driver);
    }

    public ManagePage clickManageLink(){
        return super.clickManageLink();
    }
}
