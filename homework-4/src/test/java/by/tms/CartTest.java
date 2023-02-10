package by.tms;

import by.tms.util.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static by.tms.constants.Credentials.PASSWORD;
import static by.tms.constants.Credentials.STANDARD_USER;
import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {
    private final String PRODUCT_NAME_1 = "Sauce Labs Backpack";
    private final String BUTTON_TEMPLATE = "//div[text()='%s']/ancestor::div[@class='inventory_item']" +
            "//button[text()='%s']";
    private final String PRICE_TEMPLATE = "//div[text()='%s']/ancestor::div[@class='inventory_item']" +
            "//div[@class='inventory_item_price']";
    private final String ADD_BUTTON = "Add to cart";
    private final String REMOVE_BUTTON = "Remove";
    private final String CART_ITEM_TEMPLATE = "//div[text()='%s']/ancestor::div[@class='cart_item']";

    @Test
    public void validateProductDescriptionInTheCart() {
        Steps.submitLogin(webDriver, STANDARD_USER, PASSWORD);
        webDriver.get("https://www.saucedemo.com/inventory.html");

        List<WebElement> addButtonList = webDriver.findElements(By.xpath(String.format(BUTTON_TEMPLATE, PRODUCT_NAME_1, ADD_BUTTON)));
        assertEquals(addButtonList.size(), 1);

        List<WebElement> priceList = webDriver.findElements(By.xpath(String.format(PRICE_TEMPLATE, PRODUCT_NAME_1)));
        assertEquals(priceList.size(), 1);
        String price = priceList.get(0).getText();

        addButtonList.get(0).click();
        List<WebElement> removeButtonList = webDriver.findElements(By.xpath(String.format(BUTTON_TEMPLATE,
                PRODUCT_NAME_1, REMOVE_BUTTON)));
        assertEquals(removeButtonList.size(), 1);

        List<WebElement> cartList = webDriver.findElements(By.id("shopping_cart_container"));
        assertEquals(cartList.size(), 1);
        cartList.get(0).click();

        List<WebElement> cartItemList = webDriver.findElements(By.xpath(String.format(CART_ITEM_TEMPLATE, PRODUCT_NAME_1)));
        assertEquals(cartItemList.size(), 1);

        List<WebElement> itemPriceList = webDriver.findElements(By.xpath(String.format(CART_ITEM_TEMPLATE +
                "//div[@class='inventory_item_price']", PRODUCT_NAME_1)));
        assertEquals(itemPriceList.size(), 1);
        assertEquals(price, itemPriceList.get(0).getText());

        List<WebElement> removeFromCart = webDriver.findElements(By.xpath(String.format(CART_ITEM_TEMPLATE +
                "//button[text()='%s']", PRODUCT_NAME_1, REMOVE_BUTTON)));
        assertEquals(removeFromCart.size(), 1);
        removeFromCart.get(0).click();
    }
}
