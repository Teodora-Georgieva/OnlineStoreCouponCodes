package tests;

import framework.pages.CartPage;
import framework.pages.OrderConfirmationPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testdata.CouponTestData;
import utils.Utils;

import java.math.BigDecimal;

public class CouponCodesTest extends BaseTest {
    @Test(groups = "positive")
    public void verifyCustomerCanAccessCouponApplicationControls() {
        workflow.addProductToCart();
        workflow.goToCart();
        CartPage cartPage = workflow.getCartPage();
        Assert.assertTrue(cartPage.isCouponCodeLabelDisplayed());
        Assert.assertTrue(cartPage.isCouponCodeInputDisplayed());
        Assert.assertTrue(cartPage.isApplyButtonEnabled());
    }

    @Test(groups = "positive")
    public void verifyDiscountAfterCouponCodeUsage() {
        workflow.addProductToCart();
        workflow.goToCart();
        workflow.applyCouponCode(CouponTestData.VALID_COUPON);
        CartPage cartPage = workflow.getCartPage();
        BigDecimal expectedDiscount = Utils.calculateDiscount(cartPage.getSubtotalAmount());
        BigDecimal actualDiscount = cartPage.getDiscountAmount();
        Assert.assertEquals(actualDiscount, expectedDiscount);
    }

    @Test(groups = "positive")
    public void verifyStandardShippingAppliedToNonEmptyCard() {
        workflow.addProductToCart();
        workflow.goToCart();
        CartPage cartPage = workflow.getCartPage();
        Assert.assertEquals(cartPage.getShippingAmount(), CouponTestData.STANDARD_SHIPPING_AMOUNT);
    }

    @Test(groups = "positive")
    public void verifyTotalAmount() {
        workflow.addProductToCart();
        workflow.goToCart();
        CartPage cartPage = workflow.getCartPage();
        BigDecimal expectedAmount = Utils.calculateTotalAmount(cartPage.getSubtotalAmount(), cartPage.getShippingAmount());
        Assert.assertEquals(cartPage.getTotalAmount(), expectedAmount);
    }

    @DataProvider(name = "invalidCoupons")
    public Object[][] invalidCoupons() {
        return new Object[][] {
                {CouponTestData.INVALID_COUPON},
                {CouponTestData.EMPTY_COUPON}
        };
    }

    @Test(dataProvider = "invalidCoupons", groups = "negative")
    public void verifyInvalidCodeAppliesNoDiscount(String couponCode) {
        workflow.addProductToCart();
        workflow.goToCart();
        workflow.applyCouponCode(couponCode);
        CartPage cartPage = workflow.getCartPage();
        Assert.assertFalse(cartPage.isDiscountDisplayed());
        Assert.assertNotEquals(cartPage.getCouponApplicationInfoMessage(), "Coupon \"WARACLE25\" applied");
    }

    @Test(groups = "positive")
    public void verifyOrderConfirmation() {
        workflow.addProductToCart();
        workflow.goToCart();
        workflow.applyCouponCode(CouponTestData.VALID_COUPON);
        workflow.proceedToCheckout();
        workflow.signIn();
        workflow.checkout();

        OrderConfirmationPage confirmationPage = workflow.getOrderConfirmationPage();
        BigDecimal expectedDiscount = Utils.calculateDiscount(confirmationPage.getSubtotalAmount());
        BigDecimal actualDiscount = confirmationPage.getDiscountAmount();
        Assert.assertEquals(actualDiscount, expectedDiscount);
    }
}