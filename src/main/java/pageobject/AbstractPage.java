package pageobject;

import com.google.common.collect.ImmutableMap;
import common.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    protected AbstractPage() {
        driver = DriverManager.getInstance();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(3)), this);
    }

    protected void scrollInsideElement(WebElement element) {
        Rectangle rect = element.getRect();
        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left", rect.x, "top", rect.y, "width", rect.width, "height", rect.height * 0.7,
                "direction", "down",
                "percent", 1.0
        ));
    }
}
