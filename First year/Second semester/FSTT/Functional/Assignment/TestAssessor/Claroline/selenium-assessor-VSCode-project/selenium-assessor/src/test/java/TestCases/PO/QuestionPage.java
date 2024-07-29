package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class QuestionPage extends BasePagePO{

    public QuestionPage(WebDriver driver) {
        super(driver);
    }

    public QuestionPage confirmQuestion() {
        By submitButton = By.name("cmdOk");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
        return new QuestionPage(driver);
    }

    public QuestionPage addQuestionMCUA(String title) {
        type(By.id("title"), title);
        click(By.id("MCUA"));
        clickOk();
        click(By.id("correct_1"));
        type(By.name("grade_1"), "3");
        type(By.name("grade_2"), "-3");
        return confirmQuestion();
    }

    public QuestionPage addQuestionTF(String title) {
        type(By.id("title"), title);
        click(By.id("TF"));
        clickOk();
        click(By.id("trueCorrect"));
        type(By.name("trueGrade"), "3");
        type(By.name("falseGrade"), "-3");
        return confirmQuestion();
    }

    public QuestionPage addQuestionMCMA(String title) {
        type(By.id("title"), title);
        click(By.id("MCMA"));
        clickOk();
        click(By.name("cmdAddAnsw"));
        click(By.id("correct_1"));
        type(By.name("grade_1"), "3");
        type(By.name("grade_2"), "0");
        type(By.name("grade_3"), "-3");
        return confirmQuestion();
    }

    public QuestionPage clickNewQuestion() {
        By elem = By.xpath("//*[@id=\"courseRightContent\"]/div/table/tbody/tr/td[2]/ul/li[3]/a");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new QuestionPage(driver);
    }        

    public void clickOk(){
        By elem = By.xpath("//*[@id=\"courseRightContent\"]/form/div[2]/input");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
    }

    public ExerciseDetailsPage backToExercise() {
        By elem = By.xpath("//*[@id=\"breadcrumbLine\"]/div[1]/ul/li[4]/a");
        MyUtils.WaitForElementLoaded(driver, elem);
        click(elem);
        return new ExerciseDetailsPage(driver);
    }
}
