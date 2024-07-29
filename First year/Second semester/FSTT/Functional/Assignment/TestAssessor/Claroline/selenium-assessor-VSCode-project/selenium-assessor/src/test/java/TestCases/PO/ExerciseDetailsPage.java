package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExerciseDetailsPage extends BasePagePO{

    public ExerciseDetailsPage(WebDriver driver) {
        super(driver);
    }

    public QuestionPage clickNewQuestion() {
        By elem = By.xpath("//*[@id=\"courseRightContent\"]/div[1]/table/tbody/tr/td[2]/ul/li[3]/a");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new QuestionPage(driver);
    }
}
