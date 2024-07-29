package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CourseDetail extends BasePagePO{

    public CourseDetail(WebDriver driver) {
        super(driver);
    }

    public AgendaPage clickAgendaLink() {
        By elem = By.xpath("//*[@id=\"CLCAL\"]/img");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new AgendaPage(driver);
    }

    public ExercisePage clickExerciseLink() {
        By elem = By.xpath("//*[@id=\"CLQWZ\"]/img");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new ExercisePage(driver);
    }
}
