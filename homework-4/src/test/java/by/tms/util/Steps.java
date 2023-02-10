package by.tms.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Steps {
    public static void submitLogin(WebDriver webDriver, String login, String password) {
        webDriver.get("https://www.saucedemo.com/");
        webDriver.findElement(By.id("user-name")).sendKeys(login);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("login-button")).submit();
    }
}
