package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import com.salesforce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ValidLoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void initializeLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage(BASE_URL);
    }

    @Test(priority = 1)
    public void verifyLoginPageIsDisplayed() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
    }

    @Test(priority = 2)
    public void verifyUsernameFieldIsDisplayed() {
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed());
    }

    @Test(priority = 3)
    public void verifyPasswordFieldIsDisplayed() {
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed());
    }

    @Test(priority = 4)
    public void verifyLoginButtonIsDisplayed() {
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test(priority = 5)
    public void verifyRememberMeCheckboxIsDisplayed() {
        Assert.assertTrue(loginPage.isRememberMeCheckboxDisplayed());
    }

    @Test(priority = 6)
    public void verifyLoginPageTitle() {
        String expectedTitle = "Login | Salesforce";
        Assert.assertEquals(loginPage.getPageTitle(), expectedTitle);
    }

    @Test(priority = 7)
    public void verifyLoginPageUrl() {
        Assert.assertTrue(loginPage.getCurrentUrl().contains("login.salesforce.com"));
    }

    @Test(priority = 8)
    public void verifyValidLoginWithCredentials() {
        String validUsername = "valid.user@salesforce.com";
        String validPassword = "ValidPassword123";
        loginPage.performLogin(validUsername, validPassword);
        Assert.assertFalse(loginPage.getCurrentUrl().contains("login.salesforce.com"));
    }

    @Test(priority = 9)
    public void verifyValidLoginWithRememberMe() {
        String validUsername = "valid.user@salesforce.com";
        String validPassword = "ValidPassword123";
        loginPage.performLoginWithRememberMe(validUsername, validPassword);
        Assert.assertFalse(loginPage.getCurrentUrl().contains("login.salesforce.com"));
    }

}
