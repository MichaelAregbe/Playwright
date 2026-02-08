package com.salesforce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessage;

    @FindBy(xpath = "//span[@id='idcard-identity']")
    private WebElement loggedInUserIdentity;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(usernameField));
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public void selectRememberMe() {
        wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }
    }

    public void deselectRememberMe() {
        wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        if (rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }
    }

    public void performLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public void performLoginWithRememberMe(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        selectRememberMe();
        clickLoginButton();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginPageDisplayed() {
        try {
            return usernameField.isDisplayed() && passwordField.isDisplayed() && loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUsernameFieldDisplayed() {
        return usernameField.isDisplayed();
    }

    public boolean isPasswordFieldDisplayed() {
        return passwordField.isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    public boolean isRememberMeCheckboxDisplayed() {
        return rememberMeCheckbox.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

}
