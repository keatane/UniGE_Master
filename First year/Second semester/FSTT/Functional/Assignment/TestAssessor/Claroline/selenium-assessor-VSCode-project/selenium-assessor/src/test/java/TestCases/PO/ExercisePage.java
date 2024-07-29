package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExercisePage extends BasePagePO{

    public ExercisePage(WebDriver driver) {
        super(driver);
    }

    public ExercisePage addExercise(String title) {
        click(By.xpath("//*[@id=\"courseRightContent\"]/div[1]/table/tbody/tr/td[2]/ul/li[2]/a")); // add exercise button
        type(By.id("title"), title);
        return clickSaveExercise();
    }

    public ExercisePage clickSaveExercise() {
        By submitButton = By.xpath("//*[@id=\"courseRightContent\"]/form/div[2]/input");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
        return new ExercisePage(driver);
    }

    public ExercisePage clickVisibilityOfTheOnlyExercise() {
        By visiblity = By.xpath("//*[@id=\"courseRightContent\"]/table/tbody/tr[2]/td[4]/a/img");
        MyUtils.WaitForElementLoaded(driver, visiblity);
        click(visiblity);
        return this; // no change of page
    }

    public ExerciseDetailsPage modifyExercise() {
        By exercise = By.xpath("//*[@id=\"courseRightContent\"]/table/tbody/tr[2]/td[2]/a/img");
        MyUtils.WaitForElementLoaded(driver, exercise);
        click(exercise);
        return new ExerciseDetailsPage(driver);
    }
    
}
