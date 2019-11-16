package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class ExpensesChart extends BasePage {

    public ExpensesChart(WebDriver driver) {
        super(driver);
    }

    private By chart = By.id("canvas");
    private By addDataSetButton = By.id("addDataset");

    public void verifyChartIsDisplayed() {
        assertTrue(isDisplayed(chart), "Chart is not displayed");
    }

    public void clickAddDataSetButton() {
        click(addDataSetButton);
    }
}
