package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

public abstract class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;
    public static final String URL = "https://demo.applitools.com/hackathonV2.html";
    public static final String URL_ADS = "https://demo.applitools.com/hackathonV2.html?showAd=true";
    public static final String EYES_API_KEY = "";
    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 768;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }

    public void fetchURL() {
        fetch(URL);
    }

    public void fetchURL_ADS() {
        fetch(URL_ADS);
    }

    public List<WebElement> storeAsList(By locator) {
        List<WebElement> list = driver.findElements(locator);
        return list;
    }

    public Boolean isDisplayed(By locator) {
        try {
            driver.findElement(locator).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(locator + " was not displayed");
            return false;
        }
    }

    public void enterText(By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
    }

    public void fetch(String url) {
        driver.get(url);
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public String getAttribute(By locator) {
        return driver.findElement(locator).getAttribute("placeholder");
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public List<WebElement> storeValuesAsList(By locator) {
        return driver.findElements(locator);
    }
}
