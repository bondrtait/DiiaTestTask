package common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.time.Duration;
import java.util.Map;

public class DriverManager {
    private static final String packageName = "ua.gov.diia.app";
    private static AndroidDriver driver;

    private DriverManager() {}

    public static AndroidDriver getInstance() {
        if (driver == null) {
            UiAutomator2Options options = new UiAutomator2Options();
            driver = new AndroidDriver(AppiumManager.getInstance().getUrl(), options);
            driver.executeScript("mobile:clearApp", Map.ofEntries(Map.entry("appId", packageName)));
            driver.manage()
                    .timeouts()
                    .implicitlyWait(Duration.ofSeconds(2));
        }

        return driver;
    }

    public static void suspendApp() {
        driver.runAppInBackground(Duration.ofSeconds(10));
    }

    public static void lockDevice() {
        driver.lockDevice(Duration.ofSeconds(5));
    }

    public static void goOffline() {
        driver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().withDataDisabled().build());
    }

    public static void goOnline() {
        driver.setConnection(new ConnectionStateBuilder().withWiFiEnabled().withDataEnabled().build());
    }

    public static void launchApp() {
        driver.activateApp(packageName);
    }

    public static void terminateApp() {
        driver.terminateApp(packageName);
    }


    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
