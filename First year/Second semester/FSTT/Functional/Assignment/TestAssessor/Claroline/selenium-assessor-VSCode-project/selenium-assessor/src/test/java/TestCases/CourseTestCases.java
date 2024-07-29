package TestCases;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.By;

import TestCases.PO.AgendaPage;
import TestCases.PO.BasePagePO;
import TestCases.PO.CreateNewCoursePage;
import TestCases.PO.DashboardPage;
import TestCases.PO.EnrolCoursePage;
import TestCases.PO.ExerciseDetailsPage;
import TestCases.PO.ExercisePage;
import TestCases.PO.ListCoursesPage;
import TestCases.PO.LoginFormPO;
import TestCases.PO.QuestionPage;

import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseTestCases extends DriverLifeCycleSetting{

    public DashboardPage login() {
        LoginFormPO loginForm = new LoginFormPO(driver);
        loginForm.with("admin", "admin");
        return new DashboardPage(driver);
    }

    public DashboardPage userlogin() {
        LoginFormPO loginForm = new LoginFormPO(driver);
        loginForm.with("user001", "password001");
        return new DashboardPage(driver);
    }

    public void logOut(BasePagePO page) {
        page.click(By.xpath("//*[@id=\"userBannerRight\"]/ul/li[3]/span/a"));
    }

    /* 
     * NOTE: the tests numbers are in index-1 with respect to the cases in the Gherkin directory
     * This choice has been made to make tests execution in ascending name order 
    */
    @Test
    public void testFeature3() {
        /*
         *  Feature: Course management
            Scenario: Adds a course
         */
        DashboardPage dashboardPage = login();
        CreateNewCoursePage createNewCoursePage = dashboardPage.clickPlatformAdministrationLink().clickCreateNewCourseLink()
        .addDetailsPublicCourse("Course001", "001", "Sciences", "registration_true");
        assertEquals("You have just created the course website : 001", createNewCoursePage.find(By.xpath("//*[@id=\"claroBody\"]/div[2]/div")).getText());
        createNewCoursePage.clickContinue();
        logOut(createNewCoursePage);
    }

    @Test
    public void testFeature4() {
        /*
         *  Feature: Course search
            Scenario: Searches a course
         */
        DashboardPage dashboardPage = login();
        ListCoursesPage courses = dashboardPage.clickPlatformAdministrationLink().searchCourse("Course001");
        assertEquals("Course001", courses.find(By.xpath("//*[@id=\"claroBody\"]/table[2]/tbody/tr/td[2]/a")).getText());
        assertEquals("001", courses.find(By.id("L0")).getText());
        logOut(courses);
    }

    @Test
    public void testFeature5() {
        /*
         *  Feature: Course enrolment
            Scenario: Enrols a user to a course
         */
        DashboardPage dashboardPage = userlogin();
        EnrolCoursePage enrolPage = dashboardPage.clickEnrolCourseLink().selectCourse("Course001");
        assertEquals("You've been enrolled on the course", enrolPage.find(By.xpath("//*[@id=\"claroBody\"]/div[2]/div")).getText());
        logOut(enrolPage);
    }

    @Test
    public void testFeature6() {
        /*
         *  Feature: Course management
            Scenario: Adds an event to a course
         */
        DashboardPage dashboardPage = login();
        AgendaPage agenda = dashboardPage.selectOnlyVisibleCourse().clickAgendaLink().addEvent("Event001", "31", "5", "2023", "Genoa");
        assertEquals("Event added to the agenda.", agenda.find(By.xpath("//*[@id=\"courseRightContent\"]/div[2]/div")).getText());
        logOut(agenda);
    }

    @Test
    public void testFeature7() {
        /*
         *  Feature: Course management
            Scenario: Adds an exercise to a course
         */
        DashboardPage dashboardPage = login();
        ExercisePage exercisePage = dashboardPage.selectOnlyVisibleCourse().clickExerciseLink().addExercise("Exercise001");
        assertEquals("Exercise added", exercisePage.find(By.xpath("//*[@id=\"courseRightContent\"]/div[2]/div")).getText());
        logOut(exercisePage);
    }

    @Test
    public void testFeature8() {
        /*
         *  Feature: Course management
            Scenario: Makes an exercise visible
         */
        DashboardPage dashboardPage = login();
        ExercisePage exercisePage = dashboardPage.selectOnlyVisibleCourse().clickExerciseLink();
        assertEquals("Make visible", exercisePage.find(By.xpath("//*[@id=\"courseRightContent\"]/table/tbody/tr[2]/td[4]/a/img")).getAccessibleName());
        exercisePage.clickVisibilityOfTheOnlyExercise();
        assertEquals("Make invisible", exercisePage.find(By.xpath("//*[@id=\"courseRightContent\"]/table/tbody/tr[2]/td[4]/a/img")).getAccessibleName());
        logOut(exercisePage);
    }

    @Test
    public void testFeature9() {
        /*
         *  Feature: Course management
            Scenario: Adds questions to an exercise
         */
        DashboardPage dashboardPage = login();
        ExercisePage exercisePage = dashboardPage.selectOnlyVisibleCourse().clickExerciseLink();
        QuestionPage questionPage = exercisePage.modifyExercise().clickNewQuestion().addQuestionMCUA("Question 1");
        questionPage.clickNewQuestion().addQuestionTF("Question 2");
        questionPage.clickNewQuestion().addQuestionMCMA("Question 3");
        ExerciseDetailsPage detailsPage = questionPage.backToExercise();
        assertEquals("Question 1", detailsPage.find(By.xpath("//*[@id=\"courseRightContent\"]/table/tbody/tr[1]/td[2]")).getText());
        assertEquals("Multiple choice (Unique answer)", detailsPage.find(By.xpath("//*[@id=\"courseRightContent\"]/table/tbody/tr[1]/td[4]/small")).getText());
        assertEquals("Question 2", detailsPage.find(By.xpath("//*[@id=\"courseRightContent\"]/table/tbody/tr[2]/td[2]")).getText());
        assertEquals("True/False", detailsPage.find(By.xpath("//*[@id=\"courseRightContent\"]/table/tbody/tr[2]/td[4]/small")).getText());
        assertEquals("Question 3", detailsPage.find(By.xpath("//*[@id=\"courseRightContent\"]/table/tbody/tr[3]/td[2]")).getText());
        assertEquals("Multiple choice (Multiple answers)", detailsPage.find(By.xpath("//*[@id=\"courseRightContent\"]/table/tbody/tr[3]/td[4]/small")).getText());
        logOut(detailsPage);
    }
}
