package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EnrolCoursePage extends BasePagePO{

    public EnrolCoursePage(WebDriver driver) {
        super(driver);
    }

    public EnrolCoursePage selectCourse (String course) {
        type(By.id("coursesearchbox_keyword"), course);
        click(By.xpath("//*[@id=\"claroBody\"]/form/button"));
        click(By.xpath("//*[@id=\"claroBody\"]/dl/dt/a[1]/img"));
        return new EnrolCoursePage(driver);
    }

    
}
