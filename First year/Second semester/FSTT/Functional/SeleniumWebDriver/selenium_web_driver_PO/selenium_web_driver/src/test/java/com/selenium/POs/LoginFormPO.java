package com.selenium.POs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPO extends BasePagePO{

    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By submitButton = By.cssSelector("button");
    private By invalidBox = By.id("invalid");

    public LoginFormPO(WebDriver driver) {
        super(driver);
        visit("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
    }

    public BasePagePO with(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(submitButton);

        if (getUrl().equals("https://bonigarcia.dev/selenium-webdriver-java/login-form.html"))
            return this;
        else return new LoginSuccessPO(driver);
    }

    public boolean invalidBoxIsPresent() {
        return isIn(invalidBox);
    }
}
