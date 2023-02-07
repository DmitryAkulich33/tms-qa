package by.tms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class TyposTest extends BaseTest {

    @Test
    public void typosTest() {
        webDriver.get("https://the-internet.herokuapp.com/typos");
        List<WebElement> pElements = webDriver.findElements(By.tagName("p"));
        assertEquals(pElements.size(), 2, "Two p elements should be displayed");

        assertEquals(pElements.get(1).getText(), "Sometimes you'll see a typo, other times you won't.",
                "Typos should be without mistakes");
    }
}
