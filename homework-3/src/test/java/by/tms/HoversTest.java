package by.tms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class HoversTest extends BaseTest{

    @Test
    public void hoversTest() {
        webDriver.get("https://the-internet.herokuapp.com/hovers");
        List<WebElement> figureElements = webDriver.findElements(By.className("figure"));
        assertEquals(figureElements.size(), 3, "Three figure elements should be displayed");

        WebElement figure1 = figureElements.get(0);
        Actions action = new Actions(webDriver);
        action.moveToElement(figure1).perform();

        List<WebElement> figureHoverList = webDriver.findElements(By.cssSelector(".figure:hover"));
        assertEquals(figureHoverList.size(), 1, "Only one figure element should be with hover");
        assertTrue(figure1.isDisplayed(), "Figure 1 should be with hover");

        List<WebElement> figcaptionList = webDriver.findElements(By.className("figcaption"));
        assertEquals(figcaptionList.size(), 3);

        assertTrue(figcaptionList.get(0).isDisplayed());
        assertFalse(figcaptionList.get(1).isDisplayed());
        assertFalse(figcaptionList.get(2).isDisplayed());

        List<WebElement> userLinks = webDriver.findElements(By.xpath("//a[@href=\"/users/1\"]"));
        assertEquals(userLinks.size(), 1, "Only one link has url related to user1");
        userLinks.get(0).click();
        assertTrue(webDriver.getPageSource().contains("Not Found"), "Page should contains Not Found exception");
    }
}
