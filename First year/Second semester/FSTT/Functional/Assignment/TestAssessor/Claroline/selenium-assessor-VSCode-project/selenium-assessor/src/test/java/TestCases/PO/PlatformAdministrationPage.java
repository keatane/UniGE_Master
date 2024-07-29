package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlatformAdministrationPage extends BasePagePO{

    public PlatformAdministrationPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewUserPage clickCreateNewUserLink(){
        By elem = By.xpath("//*[@id=\"claroBody\"]/ul/li[1]/ul/li[4]/a");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new CreateNewUserPage(driver);
    }

    public CreateNewCoursePage clickCreateNewCourseLink(){
        By elem = By.xpath("//*[@id=\"claroBody\"]/ul/li[2]/ul/li[3]/a");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new CreateNewCoursePage(driver);
    }

    public ListUsersPage searchUser(String user){
        type(By.id("search_user"), user);
        click(By.xpath("//*[@id=\"claroBody\"]/ul/li[1]/ul/li[1]/form/input[2]")); // search button
        return new ListUsersPage(driver);
    }
    
    public ListCoursesPage searchCourse(String course){
        type(By.id("search_course"), course);
        click(By.xpath("//*[@id=\"claroBody\"]/ul/li[2]/ul/li[1]/form/input[2]")); // search button
        return new ListCoursesPage(driver);
    }

    
}
