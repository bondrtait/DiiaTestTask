import common.DriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobject.AuthorizationPage;
import pageobject.BankAuthorizationPage;
import pageobject.ChooseYourBankPage;
import pageobject.NoInternetPopUp;

import java.lang.reflect.Method;

public class OfflineTest extends BaseTest {

    private NoInternetPopUp noInternetPopUp;
    private ChooseYourBankPage chooseYourBankPage;

    @BeforeClass
    public void launchApp() {
        DriverManager.goOffline();
        DriverManager.launchApp();
    }

    @Test
    public void verifyLaunchingAppWhileOffline() {
        noInternetPopUp = new NoInternetPopUp();
        assertTryAgainButton();
    }

    @Test(dependsOnMethods = "verifyLaunchingAppWhileOffline")
    public void verifyAuthorizationPageWhileOffline() {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        Assert.assertTrue(authorizationPage.isBankIdButtonVisible(),
                String.format(buttonShouldBeVisibleAssertMessage, "BankId"));
        authorizationPage.clickBankIdButton();
        assertTryAgainButton();
    }

    @Test(dependsOnMethods = "verifyAuthorizationPageWhileOffline")
    public void verifyChooseYourBankPageWhileOffline() {
        chooseYourBankPage = new ChooseYourBankPage();
        Assert.assertTrue(chooseYourBankPage.isChooseYourBankPageOpened(), chooseYourBankPageShouldBeOpenedAssertMessage);
        chooseYourBankPage.clickLastBankEntry();
        assertTryAgainButton();
    }

    @Test(dependsOnMethods = "verifyChooseYourBankPageWhileOffline")
    public void verifyBankAuthorizationPageWhileOffline() {
        BankAuthorizationPage bankAuthorizationPage = new BankAuthorizationPage();
        Assert.assertTrue(bankAuthorizationPage.isBackButtonVisible(),
                String.format(buttonShouldBeVisibleAssertMessage, "Back"));
        bankAuthorizationPage.clickBackButton();
        assertTryAgainButton();
    }

    @Test(dependsOnMethods = "verifyBankAuthorizationPageWhileOffline")
    public void verifyNavigatingBackToChooseYourBankPageWhileOffline() {
        Assert.assertTrue(chooseYourBankPage.isChooseYourBankPageOpened(), chooseYourBankPageShouldBeOpenedAssertMessage);
    }

    @AfterMethod
    public void goOnlineAndTryAgain(Method method) {
        if (!method.getName().equals("verifyNavigatingBackToChooseYourBankPageWhileOffline")) {
            DriverManager.goOnline();
            noInternetPopUp.clickTryAgainButton();
            DriverManager.goOffline();
        }
    }

    @AfterClass(alwaysRun = true)
    public void terminateApp() {
        DriverManager.goOnline();
        DriverManager.terminateApp();
    }

    private void assertTryAgainButton() {
        Assert.assertTrue(noInternetPopUp.isTryAgainButtonVisible(),
                String.format(buttonShouldBeVisibleAssertMessage, "Try again"));
    }
}
