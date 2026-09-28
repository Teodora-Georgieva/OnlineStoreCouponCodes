package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;

public class CartPage extends BasePage {
    @FindBy(xpath = "//span[normalize-space()='Coupon code']")
    private WebElement couponCodeLabel;

    @FindBy(css = "input[placeholder='e.g. WARACLE25']")
    private WebElement couponCodeInput;

    @FindBy(xpath = "//button[normalize-space()='Apply']")
    private WebElement applyButton;

    @FindBy(xpath = "//span[normalize-space()='Subtotal']/following-sibling::span")
    private WebElement subtotal;

//    @FindBy(xpath = "//span[contains(normalize-space(), 'Coupon')]/following-sibling::span")
//    private WebElement discount;

    @FindBy(xpath = "//span[normalize-space()='Shipping']/following-sibling::span")
    private WebElement shipping;

    @FindBy(xpath = "//span[normalize-space()='Total']/following-sibling::span")
    private WebElement total;

    @FindBy(xpath = "//button[normalize-space()='Apply']/parent::div/following-sibling::p")
    private WebElement couponApplicationInfoMessage;

    @FindBy(xpath = "//button[normalize-space()='Proceed to Checkout']")
    private WebElement proceedToCheckoutButton;

    private final By discountXpath = By.xpath("//span[contains(normalize-space(), 'Coupon')]/following-sibling::span");

    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isCouponCodeLabelDisplayed() {
        return couponCodeLabel.isDisplayed();
    }

    public boolean isCouponCodeInputDisplayed() {
        return couponCodeInput.isDisplayed();
    }

    public boolean isApplyButtonEnabled() {
        return applyButton.isEnabled();
    }

    public void enterCouponCode(String couponCode) {
        waitForElementToBeVisible(couponCodeInput);
        couponCodeInput.sendKeys(couponCode);
    }

    public void clickApplyButton() {
        waitForElementToBeClickable(applyButton);
        applyButton.click();
    }

    public BigDecimal getSubtotalAmount() {
        return new BigDecimal(subtotal.getText().replace("£", "").trim());
    }

    public BigDecimal getDiscountAmount() {
        waitForElementToBeVisible(discountXpath);
        return new BigDecimal(
                driver.findElement(discountXpath).getText().replace("–£", "").trim());
    }

    public BigDecimal getTotalAmount() {
        return new BigDecimal(total.getText().replace("£", "").trim());
    }

    public BigDecimal getShippingAmount() {
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElement(shipping, "£0.00")
        ));

        return new BigDecimal(shipping.getText().replace("£", "").trim());
    }

    public boolean isDiscountDisplayed() {
        return !driver.findElements(discountXpath).isEmpty();
    }

    public String getCouponApplicationInfoMessage() {
        return couponApplicationInfoMessage.getText();
    }

    public void clickProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
    }
}