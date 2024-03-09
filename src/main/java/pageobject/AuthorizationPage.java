package pageobject;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthorizationPage extends AbstractPage{
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='BankID']")
    private WebElement bankIdButton;

    public boolean isBankIdButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(bankIdButton));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public ChooseYourBankPage clickBankIdButton() {
        bankIdButton.click();
        return new ChooseYourBankPage();
    }
}
