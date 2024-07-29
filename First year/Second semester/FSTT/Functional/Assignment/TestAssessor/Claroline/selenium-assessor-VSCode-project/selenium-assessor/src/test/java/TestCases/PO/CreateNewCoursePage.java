package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateNewCoursePage extends BasePagePO{

    public CreateNewCoursePage(WebDriver driver) {
        super(driver);
    }

    public CreateNewCoursePage addDetailsPublicCourse(String title, String officialCode, String category, String registration){
        type(By.id("course_title"), "Course001");
        type(By.id("course_officialCode"), "001");
        new Select(this.find(By.id("mslist2"))).selectByVisibleText("Sciences");
        click(By.xpath("//*[@id=\"mandatories\"]/div/dl/dd[3]/table/tbody/tr/td[2]/a[2]/img")); // left arrow
        click(By.xpath("//*[@id=\"access_public\"]"));
        click(By.id("registration_true"));
        return clickSave();
    }

    public CreateNewCoursePage clickSave() {
        By submitButton = By.name("changeProperties");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
        return new CreateNewCoursePage(driver);
    }

    public CreateNewUserPage clickContinue() {
        By submitButton = By.xpath("//*[@id=\"claroBody\"]/p/a");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
        return new CreateNewUserPage(driver);
    }
}
