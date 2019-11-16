package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.Dashboard;
import pageobjects.ExpensesChart;
import pageobjects.Login;

import java.util.ArrayList;
import java.util.Collections;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pageobjects.BasePage.URL;

public class TraditionalTests {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyFormElementsAreDisplayed() {
        Login login = new Login(driver);
        login.fetchURL();
        login.checkFormElementsAreDisplayed();

        assertEquals(login.getLoginHeaderText(), "Login Form");
        assertEquals(login.getUsernameTitle(), "Username");
        assertEquals(login.getUserNamePlaceholderText(), "Enter your username");
        assertEquals(login.getPasswordTitle(), "Password");
        assertEquals(login.getPasswordPlaceholderText(), "Enter your password");
    }

    @Test
    public void verifyLoginError_NothingEntered() {
        Login login = new Login(driver);
        login.fetch(URL);
        login.clickLoginButton();

        assertEquals(login.getLoginErrorText(), "Both Username and Password must be present");
    }

    @Test
    public void verifyLoginError_NoUsername() {
        Login login = new Login(driver);
        login.fetchURL();
        login.enterPasswordText("password");
        login.clickLoginButton();

        assertEquals(login.getLoginErrorText(), "Username must be present");
    }

    @Test
    public void verifyLoginError_NoPassword() {
        Login login = new Login(driver);
        login.fetchURL();
        login.enterUserNameText("JohnSmith");
        login.clickLoginButton();
        assertTrue(login.checkIfErrorTextIsDisplayed(), "Error text not displayed");
        assertEquals(login.getLoginErrorText(), "Password must be present");
    }

    @Test
    public void successfulLogin() {
        Login login = new Login(driver);
        login.fetchURL();
        login.enterUserNameText("JohnSmith");
        login.enterPasswordText("password");
        login.clickRememberMeCheckbox();
        login.clickLoginButton();

        Dashboard dashboard = new Dashboard(driver);
        dashboard.waitForRecentTransactionsTable();

    }

    @Test
    public void assertRecentTransactionsIsAscending() {

        // 3. Once logged in (use any username and password to login), view the Recent Transactions table. Your test should click on the "Amounts" header, and verify that the column is in ascending order and that each row’s data stayed in tact after the sorting.
        Login login = new Login(driver);
        login.fetchURL();
        login.enterUserNameText("JohnSmith");
        login.enterPasswordText("password");
        login.clickLoginButton();

        Dashboard dashboard = new Dashboard(driver);
        dashboard.waitForRecentTransactionsTable();
        dashboard.checkingListColumnsAreDisplayed();
        dashboard.clickAmountHeader();

        ArrayList<String> originalList = new ArrayList<>();
        for (int i = 0; i < dashboard.elementsList.size(); i++) {
            originalList.add(dashboard.elementsList.get(i).getText());
        }

        ArrayList<String> copiedList = new ArrayList<>();
        for (int i = 0; i < originalList.size(); i++) {
            copiedList.add(originalList.get(i));
        }

        Collections.sort(copiedList);
        assertTrue(originalList.equals(copiedList));
    }

    @Test
    public void assertDataInCanvasChart() {
        Login login = new Login(driver);
        login.fetchURL();
        login.enterUserNameText("JohnSmith");
        login.enterPasswordText("password");
        login.clickLoginButton();

        Dashboard dashboard = new Dashboard(driver);
        dashboard.clickShowExpensesLink();

        ExpensesChart expensesChart = new ExpensesChart(driver);
        expensesChart.verifyChartIsDisplayed();
        expensesChart.clickAddDataSetButton();
        expensesChart.verifyChartIsDisplayed();
    }

    @Test
    public void verifyDisplayAdsAppear() {
        Login login = new Login(driver);
        login.fetchURL_ADS();
        login.enterUserNameText("JohnSmith");
        login.enterPasswordText("password");
        login.clickLoginButton();

        Dashboard dashboard = new Dashboard(driver);
        dashboard.checkingGifsAreDisplayed();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
