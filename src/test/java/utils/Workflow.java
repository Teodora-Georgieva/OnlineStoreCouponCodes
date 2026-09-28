package utils;

import context.TestContext;
import framework.pages.CheckoutPage;
import lombok.Getter;

import java.util.Map;

@Getter
public class Workflow {
    private final TestContext context;

    public Workflow() {
        this.context = new TestContext();
    }

    public void addProductToCart() {
        context.getHomePage().openHomePage();
        context.getHomePage().clickShopNowButton();
        context.getProductsPage().addFirstProductToCart();
    }

    public void goToCart() {
        context.getProductsPage().goToCart();
    }

    public void applyCouponCode(String couponCode) {
        context.getCartPage().enterCouponCode(couponCode);
        context.getCartPage().clickApplyButton();
    }

    public void checkout(Map<String, String> checkoutDetails) {
        context.getCartPage().clickProceedToCheckoutButton();
        //perform login
        //enter checkout details and continue to confirmation page

        //!!! TODO ADD README FILE AND UNDERSTAND HOW TO RUN FROM CMD
    }
}