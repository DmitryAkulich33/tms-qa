package by.tms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class CheckboxesTest extends BaseTest{

    @Test
    public void checkBoxesTest() {
        webDriver.get("https://the-internet.herokuapp.com/checkboxes");
        List<WebElement> checkboxes = webDriver.findElements(By.cssSelector("[type=checkbox]"));
        assertEquals(checkboxes.size(), 2, "Checkboxes should have valid size");

        WebElement checkbox1 = checkboxes.get(0);
        WebElement checkbox2 = checkboxes.get(1);
        assertFalse(checkbox1.isSelected(), "CheckBox 1 should be unchecked");
        assertTrue(checkbox2.isSelected(), "CheckBox 2 should be checked");

        checkbox1.click();
        assertTrue(checkbox1.isSelected(), "CheckBox 1 should be checked");
        assertTrue(checkbox2.isSelected(), "CheckBox 2 should be checked");

        checkbox2.click();
        assertTrue(checkbox1.isSelected(), "CheckBox 1 should be checked");
        assertFalse(checkbox2.isSelected(), "CheckBox 2 should be unchecked");
    }
}
