package by.tms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;

import static by.tms.constants.Credentials.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    private final String ERROR_TEMPLATE = "//h3[text()='Epic sadface: %s']";
    private final String CREDENTIALS_DO_NOT_MATCHED = "Username and password do not match any user in this service";
    private final String USERNAME_REQUIRED = "Username is required";
    private final String LOGIN_URL = "https://www.saucedemo.com/";
    private final String INVENTORY_URL = "https://www.saucedemo.com/inventory.html";

    @DataProvider(name = "validCredentials")
    public static Object[][] validCredentials() {
        return new String[][]{
                {STANDARD_USER, PASSWORD},
                {PROBLEM_USER, PASSWORD},
                {PERFORMANCE_GLITCH_USER, PASSWORD}
        };
    }

    @DataProvider(name = "inValidCredentials")
    public static Object[][] inValidCredentials() {
        return new String[][]{
                {"login", PASSWORD},
                {"login", "password"}
        };
    }

    @Test(dataProvider = "validCredentials")
    public void testLoginWithValidCredentials(String login, String password) {
        submitLogin(login, password);

        assertEquals(webDriver.getCurrentUrl(), INVENTORY_URL);
        assertEquals(webDriver.findElements(By.id("shopping_cart_container")).size(), 1);
        assertEquals(webDriver.findElements(By.xpath(String.format(ERROR_TEMPLATE, CREDENTIALS_DO_NOT_MATCHED))).size(), 0);
    }

    @Test(dataProvider = "inValidCredentials")
    public void testLoginWithInValidCredentials(String login, String password) {
        submitLogin(login, password);

        assertEquals(webDriver.getCurrentUrl(), LOGIN_URL);
        assertEquals(webDriver.findElements(By.cssSelector(".error-message-container.error")).size(), 1);
        assertEquals(webDriver.findElements(By.xpath(String.format(ERROR_TEMPLATE, CREDENTIALS_DO_NOT_MATCHED))).size(), 1);
    }

    @Test
    public void testLoginWhenPasswordAndLoginEmpty() {
        submitLogin(null, null);

        assertEquals(webDriver.findElements(By.cssSelector(".error-message-container.error")).size(), 1);
        assertEquals(webDriver.findElements(By.xpath(String.format(ERROR_TEMPLATE, USERNAME_REQUIRED))).size(), 1);
    }

    @Test
    public void testLoginWhenOnlyLoginEmpty() {
        submitLogin(null, PASSWORD);

        assertEquals(webDriver.findElements(By.cssSelector(".error-message-container.error")).size(), 1);
        assertEquals(webDriver.findElements(By.xpath(String.format(ERROR_TEMPLATE, USERNAME_REQUIRED))).size(), 1);
    }

    @Test
    public void testLoginWhenOnlyPasswordEmpty() {
        submitLogin(STANDARD_USER, null);

        assertEquals(webDriver.findElements(By.cssSelector(".error-message-container.error")).size(), 1);
        assertEquals(webDriver.findElements(By.xpath(String.format(ERROR_TEMPLATE,
                "Password is required"))).size(), 1);
    }

    @Test
    public void testLoginLockedUser() {
        submitLogin(LOCKED_OUT_USER, PASSWORD);

        assertEquals(webDriver.getCurrentUrl(), LOGIN_URL);
        assertEquals(webDriver.findElements(By.cssSelector(".error-message-container.error")).size(), 1);
        assertEquals(webDriver.findElements(By.xpath(String.format(ERROR_TEMPLATE,
                "Sorry, this user has been locked out."))).size(), 1);
    }

    @Test
    public void testPerformanceGlitchUser() {
        submitLogin(PERFORMANCE_GLITCH_USER, PASSWORD);

        assertEquals(webDriver.getCurrentUrl(), INVENTORY_URL);
        assertEquals(webDriver.findElements(By.id("shopping_cart_container")).size(), 1);
        int start = LocalDateTime.now().getSecond();
        assertEquals(webDriver.findElements(By.xpath(String.format(ERROR_TEMPLATE, CREDENTIALS_DO_NOT_MATCHED))).size(), 0);
        int end = LocalDateTime.now().getSecond();
        assertTrue(end - start >= 9);
    }

    private void submitLogin(String login, String password) {
        webDriver.get(LOGIN_URL);
        List<WebElement> loginList = webDriver.findElements(By.id("user-name"));
        assertEquals(loginList.size(), 1);

        List<WebElement> passwordList = webDriver.findElements(By.id("password"));
        assertEquals(passwordList.size(), 1);

        List<WebElement> buttonList = webDriver.findElements(By.id("login-button"));
        assertEquals(buttonList.size(), 1);

        if (login != null) {
            loginList.get(0).sendKeys(login);
        }

        if (password != null) {
            passwordList.get(0).sendKeys(password);
        }

        buttonList.get(0).submit();
    }
}
