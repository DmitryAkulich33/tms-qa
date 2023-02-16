package by.tms;

import by.tms.page.LoginPage;
import by.tms.page.ProductsPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends BaseTest {

    @Test
    public void checkProductPriceInTheCart() {
        String productName = "Sauce Labs Backpack";
        ProductsPage productsPage = new LoginPage(webDriver)
                .open()
                .loginAsStandardUser()
                .addProductToCart(productName);
        String expectedPrice = productsPage.getProductPrice(productName);
        String actualPrice = productsPage.openCartPage().getProductPrice(productName);

        assertThat(actualPrice).as("Product should be added with correct price").isEqualTo(expectedPrice);
    }
}
