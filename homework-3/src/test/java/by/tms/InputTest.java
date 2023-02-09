package by.tms;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class InputTest extends BaseTest {

    @Test
    public void inputTest() throws InterruptedException {
        webDriver.get("https://the-internet.herokuapp.com/inputs");
        List<WebElement> inputs = webDriver.findElements(By.tagName("input"));
        assertEquals(inputs.size(), 1, "Only one input element should be displayed");

        WebElement input = inputs.get(0);
        input.sendKeys("1");
        assertEquals(input.getAttribute("value"), "1", "Input should have only numbers");

        input.clear();
        input.sendKeys("a");
        assertEquals(input.getAttribute("value"), "", "Input should have only numbers");

        input.clear();
        input.sendKeys(Keys.ARROW_UP);
        assertEquals(input.getAttribute("value"), "1", "Input should have number 1");
        input.sendKeys(Keys.ARROW_DOWN);
        assertEquals(input.getAttribute("value"), "0", "Input should have number 0");
    }
}
