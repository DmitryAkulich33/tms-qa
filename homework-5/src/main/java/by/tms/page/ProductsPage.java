package by.tms.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {
    private final By PRODUCTS_TITLE = By.xpath("//span[text()='Products']");
    private final By CART = By.id("shopping_cart_container");
    private final By ALL_PRODUCTS = By.xpath("//div[@class='inventory_item']");

    private final String PRODUCT_CARD_LOCATOR = "//div[text()='%s']/ancestor::div[@class='inventory_item']";
    private final String PRODUCT_PRICE_LOCATOR = PRODUCT_CARD_LOCATOR + "//div[@class='inventory_item_price']";

    public ProductsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isPageOpened() {
        return webDriver.findElement(PRODUCTS_TITLE).isDisplayed();
    }

    public List<WebElement> getProducts() {
        return webDriver.findElements(ALL_PRODUCTS);
    }
}
