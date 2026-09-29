package framework.pages;

import framework.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage(WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigManager.getTimeout())
        );

        PageFactory.initElements(driver, this);
    }

    public void waitForElementToBeClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            log.error("Element was not clickable within timeout: {}", element, e);
            throw e;
        }
    }

    public void waitForElementToBeVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            log.error("Element was not visible within timeout: {}", element, e);
            throw e;
        }
    }

    protected void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void hoverOver(WebElement element) {
        new Actions(driver)
                .moveToElement(element)
                .perform();
    }

    protected void waitForElementsToBeVisible(List<WebElement> elements) {
        wait.until(driver -> !elements.isEmpty()
                && elements.stream().allMatch(WebElement::isDisplayed));
    }
}