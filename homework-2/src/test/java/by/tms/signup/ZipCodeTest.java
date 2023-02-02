package by.tms.signup;

import by.tms.config.ChromeConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ZipCodeTest {
    private WebDriver webDriver;

    @BeforeClass
    public void setUp() {
        ChromeConfig chromeConfig = new ChromeConfig();
        webDriver = chromeConfig.getWebDriver();
    }

    @BeforeMethod
    public void launchBrowser() {
        webDriver.get("https://www.sharelane.com/cgi-bin/register.py");
    }

    @AfterClass
    public void terminateBrowser() {
        webDriver.quit();
    }

    @DataProvider(name = "invalidCodes")
    public static Object[] invalidCodes() {
        return new String[]{
                "1",
                "1234",
                "",
                " ",
                " 12345",
                "12345 ",
                "123 45",
                "123_45",
                "123456",
                "123a45",
                "1234567890123456790",
                "abcde",
                "абвгд",
                "<script>",
                "\"SELECT\""
        };
    }

    @Test(dataProvider = "invalidCodes")
    public void invalidZipCode(String code) {
        submitZipCode(code);
        WebElement errorMessageSpan = webDriver.findElement(By.className("error_message"));

        assertTrue(errorMessageSpan.isDisplayed(), "Error message should be displayed");
        assertEquals(errorMessageSpan.getText(), "Oops, error on page. ZIP code should have 5 digits",
                "Error message isn't correct");
    }

    @Test
    public void validZipCode() {
        String code = "12345";
        submitZipCode(code);
        WebElement registerButton = webDriver.findElement(By.cssSelector("input[value=Register]"));

        assertTrue(registerButton.isDisplayed(), "Register page has not be opened (Register button)");
    }

    private void submitZipCode(String code) {
        webDriver.findElement(By.cssSelector("input[name=zip_code]")).sendKeys(code);
        webDriver.findElement(By.cssSelector("input[value=Continue]")).click();
    }
}
