package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import com.salesforce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void initializeLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage(BASE_URL);
    }

    @Test(priority = 1)
    public void verifyLoginWithEmptyCredentials() {
        loginPage.performLogin("", "");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 2)
    public void verifyLoginWithEmptyUsername() {
        loginPage.performLogin("", "SomePassword123");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 3)
    public void verifyLoginWithEmptyPassword() {
        loginPage.performLogin("invalid.user@salesforce.com", "");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 4)
    public void verifyLoginWithInvalidUsername() {
        loginPage.performLogin("invalid.user@invalid.com", "SomePassword123");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 5)
    public void verifyLoginWithInvalidPassword() {
        loginPage.performLogin("valid.user@salesforce.com", "InvalidPassword");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 6)
    public void verifyLoginWithInvalidCredentials() {
        loginPage.performLogin("invalid.user@invalid.com", "InvalidPassword");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 7)
    public void verifyLoginWithSpecialCharactersInUsername() {
        loginPage.performLogin("!@#$%^&*()", "SomePassword123");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 8)
    public void verifyLoginWithSpacesOnlyInUsername() {
        loginPage.performLogin("     ", "SomePassword123");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 9)
    public void verifyLoginWithVeryLongUsername() {
        String longUsername = "a".repeat(256) + "@salesforce.com";
        loginPage.performLogin(longUsername, "SomePassword123");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 10)
    public void verifyLoginWithSqlInjectionAttempt() {
        loginPage.performLogin("' OR '1'='1", "' OR '1'='1");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 11)
    public void verifyErrorMessageContainsText() {
        loginPage.performLogin("invalid@test.com", "wrongpassword");
        String errorText = loginPage.getErrorMessage();
        Assert.assertFalse(errorText.isEmpty());
    }

    @Test(priority = 12)
    public void verifyUserRemainsOnLoginPageAfterInvalidLogin() {
        loginPage.performLogin("invalid@test.com", "wrongpassword");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("login.salesforce.com"));
    }

}
