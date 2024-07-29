package TestCases.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AgendaPage extends BasePagePO{

    public AgendaPage(WebDriver driver) {
        super(driver);
    }

    public AgendaPage addEvent(String title, String day, String month, String year, String location) {
        click(By.xpath("//*[@id=\"courseRightContent\"]/div/table/tbody/tr/td[2]/ul/li[2]/a")); // add event button
        type(By.id("title"), "Event001");
        click(By.id("fday"));
        new Select(this.find(By.id("fday"))).selectByValue("31");
        click(By.id("fday"));
        new Select(this.find(By.id("fmonth"))).selectByValue("5");
        click(By.id("fmonth"));
        new Select(this.find(By.id("fyear"))).selectByValue("2023");
        type(By.id("location"), "Genoa");
        return clickSaveEvent();
    }

    public AgendaPage clickSaveEvent() {
        By submitButton = By.name("submitEvent");
        MyUtils.WaitForElementLoaded(driver, submitButton);
        click(submitButton);
        return new AgendaPage(driver);
    }

    
}
