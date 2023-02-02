package by.tms.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeConfig {
    private WebDriver webDriver;

    public ChromeConfig() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        webDriver = new ChromeDriver(options);
    }

    static {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
