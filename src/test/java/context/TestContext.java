package context;

import framework.driver.DriverManager;
import framework.pages.*;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class TestContext {
    private LoginPage loginPage;
    private HomePage homePage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    //private OrderConfirmationPage orderConfirmationPage;

    public TestContext() {
        WebDriver driver = DriverManager.getDriver();
        this.homePage = new HomePage(driver);
        this.productsPage = new ProductsPage(driver);
        this.cartPage = new CartPage(driver);
    }
}