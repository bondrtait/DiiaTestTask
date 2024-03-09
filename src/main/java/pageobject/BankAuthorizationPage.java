package pageobject;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BankAuthorizationPage extends AbstractPage {
    @AndroidFindBy(xpath = "//android.widget.ImageView")
    private WebElement backButton;

    public boolean isBackButtonVisible() {
        try {
            wait
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(backButton));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public ChooseYourBankPage clickBackButton() {
        backButton.click();
        return new ChooseYourBankPage();
    }
}
