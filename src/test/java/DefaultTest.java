import common.DriverManager;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobject.AuthorizationPage;
import pageobject.BankAuthorizationPage;
import pageobject.ChooseYourBankPage;

public class DefaultTest extends BaseTest {
    private AuthorizationPage authorizationPage;
    private ChooseYourBankPage chooseYourBankPage;
    private BankAuthorizationPage bankAuthorizationPage;

    @BeforeClass
    public void launchApp() {
        DriverManager.launchApp();
    }

    @Test
    public void verifyAuthorizationPage() {
        authorizationPage = new AuthorizationPage();
        Assert.assertTrue(authorizationPage.isBankIdButtonVisible(),
                String.format(buttonShouldBeVisibleAssertMessage, "BankID"));
    }

    @Test(dependsOnMethods = "verifyAuthorizationPage")
    public void verifyChooseYourBankPage() {
        chooseYourBankPage = authorizationPage.clickBankIdButton();
        Assert.assertTrue(chooseYourBankPage.isChooseYourBankPageOpened(),
                chooseYourBankPageShouldBeOpenedAssertMessage);
    }

    @Test(dependsOnMethods = "verifyChooseYourBankPage")
    public void verifyBankAuthorizationPage() {
        bankAuthorizationPage = chooseYourBankPage.clickLastBankEntry();
        Assert.assertTrue(bankAuthorizationPage.isBackButtonVisible(),
                String.format(buttonShouldBeVisibleAssertMessage, "Back"));
    }

    @Test(dependsOnMethods = "verifyBankAuthorizationPage")
    public void verifyNavigatingBackToChooseYourBankPage() {
        chooseYourBankPage = bankAuthorizationPage.clickBackButton();
        Assert.assertTrue(chooseYourBankPage.isChooseYourBankPageOpened(),
                chooseYourBankPageShouldBeOpenedAssertMessage);
    }

    @AfterMethod
    @Parameters("testSuspending")
    public void suspendApp(@Optional("false") boolean testSuspending) {
        if (testSuspending) {
            DriverManager.suspendApp();
        }
    }

    @AfterMethod
    @Parameters("testLocking")
    public void lockUnlockApp(@Optional("false") boolean testLocking) {
        if (testLocking) {
            DriverManager.lockDevice();
        }
    }

    @AfterClass(alwaysRun = true)
    public void terminateApp() {
        DriverManager.terminateApp();
    }
}
