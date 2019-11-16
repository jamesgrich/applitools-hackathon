package tests;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.Dashboard;
import pageobjects.ExpensesChart;
import pageobjects.Login;

import static pageobjects.BasePage.*;

public class VisualAITests {

    private WebDriver driver;
    private static Eyes eyes;
    private static EyesRunner runner;
    private static BatchInfo batch;

    @BeforeClass
    public static void setBatch() {
        batch = new BatchInfo("Hackathon");
    }

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        runner = new ClassicRunner();
        driver = new ChromeDriver();
        eyes = new Eyes();
        eyes.setApiKey(EYES_API_KEY);
        eyes.setBatch(batch);
        eyes.setForceFullPageScreenshot(true);
    }

    @Test
    public void verifyFormElementsAreDisplayed() {
        Login login = new Login(driver);
        eyes.open(driver, "Applitools Hackathon", "1. Checking the login window", new RectangleSize(WINDOW_WIDTH, WINDOW_HEIGHT));
        login.fetchURL();
        eyes.checkWindow("Login window");
        eyes.closeAsync();
    }

    @Test
    public void verifyLoginError_NothingEntered() {
        Login login = new Login(driver);
        eyes.open(driver, "Applitools Hackathon", "2a. Checking the username and password errors", new RectangleSize(WINDOW_WIDTH, WINDOW_HEIGHT));
        login.fetchURL();
        login.clickLoginButton();
        eyes.checkWindow("Login window with username and password error");
        eyes.closeAsync();
    }

    @Test
    public void verifyLoginError_NoUsername() {
        Login login = new Login(driver);
        eyes.open(driver, "Applitools Hackathon", "2b. Checking the no username error", new RectangleSize(WINDOW_WIDTH, WINDOW_HEIGHT));
        login.fetchURL();
        login.enterPasswordText("password");
        login.clickLoginButton();
        eyes.checkWindow("Login window with the no username error");
        eyes.closeAsync();
    }

    @Test
    public void verifyLoginError_NoPassword() {
        Login login = new Login(driver);
        eyes.open(driver, "Applitools Hackathon", "2c. Checking the no password error", new RectangleSize(WINDOW_WIDTH, WINDOW_HEIGHT));
        login.fetchURL();
        login.enterUserNameText("JohnSmith");
        login.clickLoginButton();
        eyes.checkWindow("Login window with the no password error");
        eyes.closeAsync();
    }

    @Test
    public void successfulLogin() {
        Login login = new Login(driver);
        eyes.open(driver, "Applitools Hackathon", "2d. Checking the successful login functionality", new RectangleSize(WINDOW_WIDTH, WINDOW_HEIGHT));
        login.fetchURL();
        login.enterUserNameText("JohnSmith");
        login.enterPasswordText("password");
        login.clickRememberMeCheckbox();
        login.clickLoginButton();

        Dashboard dashboard = new Dashboard(driver);
        dashboard.waitForRecentTransactionsTable();
        eyes.checkWindow("Dashboard window");
        eyes.closeAsync();
    }

    @Test
    public void assertRecentTransactionsIsAscending() {
        Login login = new Login(driver);
        eyes.open(driver, "Applitools Hackathon", "3. Checking the data under the Amounts header is in ascending order when clicked", new RectangleSize(WINDOW_WIDTH, WINDOW_HEIGHT));
        login.fetchURL();
        login.enterUserNameText("JohnSmith");
        login.enterPasswordText("password");
        login.clickRememberMeCheckbox();
        login.clickLoginButton();

        Dashboard dashboard = new Dashboard(driver);
        dashboard.waitForRecentTransactionsTable();
        dashboard.clickAmountHeader();
        eyes.checkWindow("Amounts column with ascending data");
        eyes.closeAsync();
    }

    @Test
    public void assertDataInCanvasChart() {
        Login login = new Login(driver);
        eyes.open(driver, "Applitools Hackathon", "4. Checking the Compare Expenses bar chart with the addition of 2019 data", new RectangleSize(WINDOW_WIDTH, WINDOW_HEIGHT));
        login.fetchURL();
        login.enterUserNameText("JohnSmith");
        login.enterPasswordText("password");
        login.clickRememberMeCheckbox();
        login.clickLoginButton();

        Dashboard dashboard = new Dashboard(driver);
        dashboard.clickShowExpensesLink();

        ExpensesChart expensesChart = new ExpensesChart(driver);
        eyes.checkWindow("Bar chart comparing 2017 and 2018");
        expensesChart.clickAddDataSetButton();
        eyes.checkWindow("Bar chart comparing 2017, 2018 and 2019");
        eyes.closeAsync();
    }

    @Test
    public void verifyDisplayAdsAppear() {
        Login login = new Login(driver);
        eyes.open(driver, "Applitools Hackathon", "5. Checking for the existence of display ads", new RectangleSize(WINDOW_WIDTH, WINDOW_HEIGHT));
        login.fetchURL_ADS();
        login.enterUserNameText("JohnSmith");
        login.enterPasswordText("password");
        login.clickRememberMeCheckbox();
        login.clickLoginButton();

        Dashboard dashboard = new Dashboard(driver);
        dashboard.waitForRecentTransactionsTable();
        eyes.checkWindow("Display ad/s");
        eyes.closeAsync();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        eyes.abortIfNotClosed();
    }
}
