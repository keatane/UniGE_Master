package com.selenium.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.selenium.POs.BasePagePO;
import com.selenium.POs.LoginFormPO;
import com.selenium.POs.LoginSuccessPO;

public class BasicLoginTest extends DriverLifeCycleSetting {
    
    private LoginFormPO loginForm;
    private LoginSuccessPO loginSuccess;

    @Test
    public void testLoginOK() {
        loginForm = new LoginFormPO(driver);
        BasePagePO page = loginForm.with("user", "user");
        assertTrue(page instanceof LoginSuccessPO);
        loginSuccess = (LoginSuccessPO) page;
        assertTrue(loginSuccess.successBoxIsPresent());
    }

    @Test
    public void testLoginNoOK() {
        loginForm = new LoginFormPO(driver);
        loginForm.with("user", "error");
        assertTrue(loginForm.invalidBoxIsPresent());
    }
}
