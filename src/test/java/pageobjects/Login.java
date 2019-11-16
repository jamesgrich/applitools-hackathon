package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class Login extends BasePage {

    public Login(WebDriver driver) {
        super(driver);
    }

    private By formImage = By.cssSelector("img[src='img/logo-big.png']");
    private By title = By.cssSelector(".auth-header");
    private By userNameTitle = By.cssSelector(".form-group:nth-of-type(1) label");
    private By userNameIcon = By.cssSelector(".pre-icon.os-icon.os-icon-user-male-circle");
    private By usernameInput = By.id("username");
    private By passwordTitle = By.cssSelector(".form-group:nth-of-type(2) label");
    private By passwordIcon = By.cssSelector(".pre-icon.os-icon.os-icon-fingerprint");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("log-in");
    private By rememberMeCheckbox = By.cssSelector(".form-check-input");
    private By twitterIcon = By.cssSelector("img[src='img/social-icons/twitter.png']");
    private By facebookIcon = By.cssSelector("img[src='img/social-icons/facebook.png']");
    private By linkedInIcon = By.cssSelector("img[src='img/social-icons/linkedin.png']");
    private By loginErrorText = By.cssSelector(".alert.alert-warning");

    public void checkFormElementsAreDisplayed() {
        assertTrue(isDisplayed(formImage), "Form image not displayed");
        assertTrue(isDisplayed(title), "Title not displayed");
        assertTrue(isDisplayed(userNameTitle), "Username title not displayed");
        assertTrue(isDisplayed(userNameIcon), "Username icon not displayed");
        assertTrue(isDisplayed(usernameInput), "Username input not displayed");
        assertTrue(isDisplayed(passwordTitle), "Password title not displayed");
        assertTrue(isDisplayed(passwordIcon), "Password icon not displayed");
        assertTrue(isDisplayed(passwordInput), "Password input not displayed");
        assertTrue(isDisplayed(loginButton), "Login button not displayed");
        assertTrue(isDisplayed(rememberMeCheckbox), "Remember Me checkbox not displayed");
        assertTrue(isDisplayed(twitterIcon), "Twitter icon not displayed");
        assertTrue(isDisplayed(facebookIcon), "Facebook icon not displayed");
        assertTrue(isDisplayed(linkedInIcon), "Linkedin icon not displayed");
    }

    public Boolean checkIfErrorTextIsDisplayed() {
        return isDisplayed(loginErrorText);
    }

    public void clickRememberMeCheckbox() {
        click(rememberMeCheckbox);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public String getLoginHeaderText() {
        return getText(title);
    }

    public String getUsernameTitle() {
        return getText(userNameTitle);
    }

    public String getPasswordTitle() {
        return getText(passwordTitle);
    }

    public String getUserNamePlaceholderText() {
        return getAttribute(usernameInput);
    }

    public String getPasswordPlaceholderText() {
        return getAttribute(passwordInput);
    }

    public String getLoginErrorText() {
        return getText(loginErrorText);
    }

    public void enterUserNameText(String text) {
        enterText(usernameInput, text);
    }

    public void enterPasswordText(String text) {
        enterText(passwordInput, text);
    }
}
