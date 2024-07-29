package com.selenium.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.selenium.POs.BasePagePO;
import com.selenium.POs.LoginFormPO;
import com.selenium.POs.LoginSuccessPO;

public class HelloWorldCrossBrowser extends DriverLifeCycleSetting {

    @ParameterizedTest
    @EnumSource(Browser.class)
    public void testA(Browser browser) throws InterruptedException {
            driver = DriverFactory.getNewDriverInstance(browser);
            LoginFormPO loginForm = new LoginFormPO(driver);
            BasePagePO page = loginForm.with("user", "user");
            assertTrue(page instanceof LoginSuccessPO);
            LoginSuccessPO loginSuccess = (LoginSuccessPO) page;
            assertTrue(loginSuccess.successBoxIsPresent());
    }
}
