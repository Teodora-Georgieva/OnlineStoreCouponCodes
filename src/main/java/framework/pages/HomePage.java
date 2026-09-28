package framework.pages;

import framework.config.ConfigManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class HomePage extends BasePage {
    @FindBy(xpath = "//a[normalize-space()='Shop Now']")
    private WebElement shopNowButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openHomePage() {
        log.info("BASE URL: " + ConfigManager.getBaseUrl());
        driver.get(ConfigManager.getBaseUrl());
    }

    public void clickShopNowButton() {
        waitForElementToBeClickable(shopNowButton);
        shopNowButton.click();
    }
}