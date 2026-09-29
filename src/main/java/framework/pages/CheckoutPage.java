package framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    @FindBy(xpath = "//label[normalize-space()='Address']/following-sibling::input")
    private WebElement addressField;

    @FindBy(xpath = "//label[normalize-space()='City']/following-sibling::input")
    private WebElement cityField;

    @FindBy(xpath = "//label[normalize-space()='Postcode']/following-sibling::input")
    private WebElement postcodeField;

    @FindBy(xpath = "//label[normalize-space()='Card Number']/following-sibling::div//input")
    private WebElement cardNumberField;

    @FindBy(xpath = "//label[normalize-space()='Expiry Date']/following-sibling::input")
    private WebElement expiryDateField;

    @FindBy(xpath = "//label[normalize-space()='CVC']/following-sibling::input")
    private WebElement cvcField;

    @FindBy(xpath = "//button[contains(normalize-space(), 'Pay')]")
    private WebElement payButton;

    public CheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterAddress(String address) {
        waitForElementToBeVisible(addressField);
        addressField.sendKeys(address);
    }

    public void enterCity(String city) {
        cityField.sendKeys(city);
    }

    public void enterPostCode(String postCode) {
        postcodeField.sendKeys(postCode);
    }

    public void enterCardNumber(String cardNumber) {
        cardNumberField.sendKeys(cardNumber);
    }

    public void enterExpiryDate(String expiryDate) {
        expiryDateField.sendKeys(expiryDate);
    }

    public void enterCVC(String CVC) {
        cvcField.sendKeys(CVC);
    }

    public void clickPayButton() {
        payButton.click();
    }
}