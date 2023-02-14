package by.tms;

import by.tms.page.LoginPage;
import by.tms.page.ProductsPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsTest extends BaseTest {

    @Test
    public void checkProductsCount() {
        ProductsPage productsPage = new LoginPage(webDriver).open().loginAsStandardUser();
        assertThat(productsPage.getProducts())
                .isNotNull()
                .hasSize(6)
                .as("Products page should have 6 products");
    }
}
