package pageobject;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class NoInternetPopUp extends AbstractPage {

    @AndroidFindBy(xpath = "(//android.widget.Button)[1]")
    private WebElement tryAgainButton;

    public boolean isTryAgainButtonVisible() {
        try {
            wait
                    .withTimeout(Duration.ofSeconds(35))
                    .pollingEvery(Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.Button)[1]")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickTryAgainButton() {
        tryAgainButton.click();
    }
}
