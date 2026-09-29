package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage {
    @FindBy(css = "a[href^='/products/']")
    private List<WebElement> productCards;

    @FindBy(css = "a[aria-label='Cart']")
    private WebElement cartIcon;

    public ProductsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void addFirstProductToCart() {
        waitForElementsToBeVisible(productCards);
        WebElement firstProduct = productCards.get(0);
        hoverOver(firstProduct);
        WebElement addToCartButton = firstProduct.findElement(
                By.xpath(".//button[normalize-space()='Add to cart']")
        );
        waitForElementToBeClickable(addToCartButton);
        addToCartButton.click();
    }

    public void goToCart() {
        waitForElementToBeClickable(cartIcon);
        cartIcon.click();
    }
}