package tests;

import framework.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testdata.TestData;
import utils.Utils;

import java.math.BigDecimal;

public class CouponCodesTest extends BaseTest {
    @Test
    public void verifyCustomerCanAccessCouponApplicationControls() {
        workflow.addProductToCart();
        workflow.goToCart();
        CartPage cartPage = workflow.getContext().getCartPage();
        Assert.assertTrue(cartPage.isCouponCodeLabelDisplayed());
        Assert.assertTrue(cartPage.isCouponCodeInputDisplayed());
        Assert.assertTrue(cartPage.isApplyButtonEnabled());
        //TODO ADD assert that when the code is entered under the field there's msg Coupon “WARACLE25” applied
         try {
             Thread.sleep(5000);
         } catch (Exception e) {}
    }

    @Test
    public void verifyDiscountAfterCouponCodeUsage() {
        workflow.addProductToCart();
        workflow.goToCart();
        workflow.applyCouponCode(TestData.VALID_COUPON);
        CartPage cartPage = workflow.getContext().getCartPage();
        BigDecimal expectedDiscount = Utils.calculateDiscount(cartPage.getSubtotalAmount());
        BigDecimal actualDiscount = cartPage.getDiscountAmount();
        Assert.assertEquals(actualDiscount, expectedDiscount);
    }

    @Test
    public void verifyStandardShippingAppliedToNonEmptyCard() {
        workflow.addProductToCart();
        workflow.goToCart();
        CartPage cartPage = workflow.getContext().getCartPage();
        Assert.assertEquals(cartPage.getShippingAmount(), TestData.STANDARD_SHIPPING_AMOUNT);
    }

    @Test
    public void verifyTotalAmount() {
        workflow.addProductToCart();
        workflow.goToCart();
        CartPage cartPage = workflow.getContext().getCartPage();
        BigDecimal expectedAmount = Utils.calculateTotalAmount(cartPage.getSubtotalAmount(), cartPage.getShippingAmount());
        Assert.assertEquals(cartPage.getTotalAmount(), expectedAmount);
    }

    @DataProvider(name = "invalidCoupons")
    public Object[][] invalidCoupons() {
        return new Object[][] {
                {TestData.INVALID_COUPON},
                {TestData.EMPTY_COUPON}
        };
    }

    @Test(dataProvider = "invalidCoupons")
    public void verifyInvalidCodeAppliesNoDiscount(String couponCode) {
        //todo
        //TODO assert that when invalid code is entered then
        //the msg under the input field is different than     Coupon “WARACLE25” applied

        workflow.addProductToCart();
        workflow.goToCart();
        workflow.applyCouponCode(couponCode);
        CartPage cartPage = workflow.getContext().getCartPage();
        Assert.assertFalse(cartPage.isDiscountDisplayed());
        Assert.assertNotEquals(cartPage.getCouponApplicationInfoMessage(), "Coupon \"WARACLE25\" applied");
    }

    @Test
    public void verifyOrderConfirmation() {
        //todo
    }
}