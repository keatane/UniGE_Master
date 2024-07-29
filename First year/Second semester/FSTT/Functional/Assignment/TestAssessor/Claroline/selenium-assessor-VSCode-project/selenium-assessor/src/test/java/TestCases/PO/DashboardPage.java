package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePagePO{

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public PlatformAdministrationPage clickPlatformAdministrationLink(){
        By elem = By.xpath("//*[@id=\"userBannerLeft\"]/ul/li[3]/span/a");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new PlatformAdministrationPage(driver);
    }

    public EnrolCoursePage clickEnrolCourseLink() {
        By elem = By.xpath("//*[@id=\"dekstopLeftSidebar\"]/div/div/div[1]/ul/li[1]/a");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new EnrolCoursePage(driver);
    }

    public CourseDetail selectOnlyVisibleCourse() {
        By elem = By.xpath("//*[@id=\"dekstopLeftSidebar\"]/div/div/div[2]/dl/dt/span/a");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new CourseDetail(driver);
    }

    
}
