package framework.driver;

import framework.config.ConfigManager;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    private DriverManager() {}

    public static void initializeDriver() {
        if(webDriver.get() == null) {
            WebDriver driver = DriverFactory.createDriver(ConfigManager.getBrowser(), ConfigManager.isHeadless());
            webDriver.set(driver);
        }
    }

    public static WebDriver getDriver() {
        if (webDriver.get() == null) {
            throw new IllegalStateException(
                    "WebDriver has not been initialized. Call DriverManager.initializeDriver() first."
            );
        }

        return webDriver.get();
    }

    public static void quitDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }
}