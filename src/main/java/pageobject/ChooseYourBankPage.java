package pageobject;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ChooseYourBankPage extends AbstractPage {
    @AndroidFindBy(xpath = "//android.widget.ScrollView")
    private WebElement scrollView;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Оберіть свій банк']")
    private WebElement headline;

    public boolean isChooseYourBankPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOf(headline));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public BankAuthorizationPage clickLastBankEntry() {
        wait.until(ExpectedConditions.visibilityOf(scrollView));

        String textViewLocator = "//android.view.View[last()%s]/android.widget.TextView";
        By secondToLastLocator = By.xpath(String.format(textViewLocator, "-1"));

        WebElement lastBankElement;
        do {
            lastBankElement = scrollView.findElement(secondToLastLocator);
            scrollInsideElement(scrollView);
        } while (!lastBankElement.getText().equals(scrollView.findElement(secondToLastLocator).getText()));

        scrollView.findElement(By.xpath(String.format(textViewLocator, ""))).click();

        return new BankAuthorizationPage();
    }
}
