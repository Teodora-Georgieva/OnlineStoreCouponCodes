package framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

public class OrderConfirmationPage extends BasePage {
    private static final String THANK_YOU_HEADER_TEXT = "Thank you for your order!";

    @FindBy(xpath = "//h1[normalize-space()='Thank you for your order!']")
    private WebElement thankYouHeader;

    @FindBy(xpath = "//span[normalize-space()='Coupon (WARACLE25)']")
    private WebElement couponLabel;

    @FindBy(xpath = "//span[normalize-space()='Coupon (WARACLE25)']/following-sibling::span[1]")
    private WebElement couponValue;

    @FindBy(xpath = "//span[contains(normalize-space(), 'Waracle Cap')]/following-sibling::span")
    private WebElement subtotal;

    @FindBy(xpath = "//p[normalize-space()='Total Paid']/following-sibling::p[1]")
    private WebElement totalPaid;

    public OrderConfirmationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isPageDisplayed() {
        return thankYouHeader.isDisplayed() && thankYouHeader.getText().equals(THANK_YOU_HEADER_TEXT);
    }

    public BigDecimal getSubtotalAmount() {
        return new BigDecimal(subtotal.getText().replace("£", "").trim());
    }

    public BigDecimal getDiscountAmount() {
        return new BigDecimal(couponValue.getText().replace("–£", "").trim());
    }
}