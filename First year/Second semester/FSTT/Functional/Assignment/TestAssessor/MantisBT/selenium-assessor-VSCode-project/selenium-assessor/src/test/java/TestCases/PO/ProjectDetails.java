package TestCases.PO;

import org.openqa.selenium.WebDriver;

public class ProjectDetails extends DashboardPage{

    public ProjectDetails(WebDriver driver) {
        super(driver);
    }

    public ManagePage clickManageLink(){
        return super.clickManageLink();
    }
}
