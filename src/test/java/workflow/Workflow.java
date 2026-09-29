package workflow;

import framework.config.ConfigManager;
import framework.driver.DriverManager;
import framework.pages.*;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import testdata.CheckoutTestData;
import utils.Utils;

@Getter
public class Workflow {
    private LoginPage loginPage;
    private HomePage homePage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OrderConfirmationPage orderConfirmationPage;

    public Workflow() {
        WebDriver driver = DriverManager.getDriver();
        this.homePage = new HomePage(driver);
        this.productsPage = new ProductsPage(driver);
        this.cartPage = new CartPage(driver);
        this.loginPage = new LoginPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
        this.orderConfirmationPage = new OrderConfirmationPage(driver);
    }

    public void addProductToCart() {
        homePage.openHomePage();
        homePage.clickShopNowButton();
        productsPage.addFirstProductToCart();
    }

    public void goToCart() {
        productsPage.goToCart();
    }

    public void applyCouponCode(String couponCode) {
        cartPage.enterCouponCode(couponCode);
        cartPage.clickApplyButton();
    }

    public void signIn() {
        loginPage.enterEmail(ConfigManager.getUsername());
        loginPage.enterPassword(ConfigManager.getPassword());
        loginPage.clickSignInButton();
    }

    public void proceedToCheckout() {
        cartPage.clickProceedToCheckoutButton();
    }

    public void checkout() {
        CheckoutTestData checkoutTestData = Utils.getCheckoutData();
        checkoutPage.enterAddress(checkoutTestData.getAddress());
        checkoutPage.enterCity(checkoutTestData.getCity());
        checkoutPage.enterPostCode(checkoutTestData.getPostCode());
        checkoutPage.enterCardNumber(checkoutTestData.getCardNumber());
        checkoutPage.enterExpiryDate(checkoutTestData.getExpiryDate());
        checkoutPage.enterCVC(checkoutTestData.getCvc());
        checkoutPage.clickPayButton();
    }
}