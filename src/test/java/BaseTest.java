import common.AppiumManager;
import common.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected AndroidDriver driver;
    protected final String buttonShouldBeVisibleAssertMessage = "%s button should be visible";
    protected final String chooseYourBankPageShouldBeOpenedAssertMessage = "Choose Your Bank page should be opened";

    @BeforeSuite
    public void startServer() {
        AppiumManager.getInstance().start();
    }
    @BeforeClass
    public void setUp() {
        driver = DriverManager.getInstance();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverManager.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void stopServer() {
        AppiumManager.getInstance().stop();
    }
}
