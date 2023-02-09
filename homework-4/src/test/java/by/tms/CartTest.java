package by.tms;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void validateProductDescriptionInTheCart() {
        webDriver.get("https://www.saucedemo.com/inventory.html");
        String productName = "Sauce Labs Backpack"; // todo private final
        String addToCartButtonPattern = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']" +
                "//button[text()='Add to cart']", productName);
        webDriver.findElement(By.xpath(addToCartButtonPattern)).click(); // провалидировать что кнопка стала ремув
        webDriver.findElement(By.id("shopping_cart_container")).click();

        String productInTheCart = String.format("//div[text()='%s']/ancestor::div[@class='cart_item']", productName);

        Assert.assertTrue(webDriver.findElement(By.xpath(productInTheCart)).isDisplayed(), "Product has not been added to the cart");
    }
}
