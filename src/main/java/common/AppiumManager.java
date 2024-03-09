package common;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumManager {
    private static AppiumDriverLocalService appiumServiceInstance;

    private AppiumManager() {
    }

    public static AppiumDriverLocalService getInstance() {
        if (appiumServiceInstance == null) {
            appiumServiceInstance = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "warn"));
        }

        return appiumServiceInstance;
    }
}
