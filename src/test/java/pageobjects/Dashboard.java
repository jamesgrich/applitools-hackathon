package pageobjects;

import org.openqa.selenium.*;

import java.util.List;

public class Dashboard extends BasePage {

    public Dashboard(WebDriver driver) {
        super(driver);
    }

    By recentTransactionsTable = By.cssSelector(".element-wrapper:nth-child(2)");
    By recentTransactionsTable_AmountHeader = By.id("amount");
    By statusColumn = By.cssSelector(".nowrap:nth-of-type(1)");
    By tableDate = By.cssSelector("#transactionsTable tbody tr td:nth-of-type(2)");
    By tableTime = By.cssSelector("#transactionsTable tbody tr td:nth-of-type(2) span:nth-of-type(2)");
    By tableDescription = By.cssSelector(".cell-with-media span");
    By tableCategory = By.cssSelector(".text-center a");
    public By tableAmount = By.cssSelector("#transactionsTable tbody tr td:nth-of-type(6)");
    public List<WebElement> elementsList = storeAsList(tableAmount);
    By showExpensesChartLink = By.id("showExpensesChart");
    By firstFlashSaleGif = By.cssSelector("img[src='img/flashSale.gif");
    By secondFlashSaleGif = By.cssSelector("img[src='img/flashSale2.gif");
    By thirdFlashSaleGif = By.cssSelector("img[src='img/flashSale3.gif");

    public Boolean waitForRecentTransactionsTable() {
        try {
            isDisplayed(recentTransactionsTable);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(getText(recentTransactionsTable) + " were not visible");
            return false;
        }
    }

    public Boolean checkingGifsAreDisplayed() {
        try {
            try {
                isDisplayed(firstFlashSaleGif);
                isDisplayed(secondFlashSaleGif);
            } catch (NoSuchElementException e) {
                isDisplayed(thirdFlashSaleGif);
            }
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("None of the display ads were found");
        }
        return false;
    }

    public void clickAmountHeader() {
        click(recentTransactionsTable_AmountHeader);
    }

    public void checkEachElementInColumnIsDisplayed(By locator) {
        List<WebElement> locators = storeValuesAsList(locator);
        for (WebElement e : locators) {
            e.isDisplayed();
        }
    }

    public void checkingListColumnsAreDisplayed() {
        checkEachElementInColumnIsDisplayed(statusColumn);
        checkEachElementInColumnIsDisplayed(tableDate);
        checkEachElementInColumnIsDisplayed(tableTime);
        checkEachElementInColumnIsDisplayed(tableDescription);
        checkEachElementInColumnIsDisplayed(tableCategory);
    }

    public void clickShowExpensesLink() {
        click(showExpensesChartLink);
    }
}
