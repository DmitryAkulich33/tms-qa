package by.tms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class DropDownTest extends BaseTest {

    @Test
    public void dropDownTest() {
        webDriver.get("https://the-internet.herokuapp.com/dropdown");
        List<WebElement> dropdownElements = webDriver.findElements(By.id("dropdown"));
        assertEquals(dropdownElements.size(), 1, "Only one dropDown element should be displayed");

        Select dropDown = new Select(dropdownElements.get(0));
        assertFalse(dropDown.isMultiple(), "Dropdown should not be multiple");

        List<WebElement> options = dropDown.getOptions();
        assertEquals(options.size(), 3, "DropDown should have valid size");

        WebElement defaultOption = options.get(0);
        assertEquals(defaultOption.getText(), "Please select an option", "The option is invalid");
        assertFalse(defaultOption.isEnabled(), "The default option should be disabled");
        assertTrue(defaultOption.isSelected(), "The default option should be selected");
        assertEquals(dropDown.getAllSelectedOptions().size(), 1, "Only one option should be selected");

        WebElement option1 = options.get(1);
        WebElement option2 = options.get(2);
        assertEquals(option1.getText(), "Option 1", "The option 1 is invalid");
        assertEquals(option2.getText(), "Option 2", "The option 2 is invalid");

        dropDown.selectByValue("1");
        assertTrue(option1.isSelected(), "The option 1 should be selected");
        assertEquals(dropDown.getAllSelectedOptions().size(), 1, "Only option 1 should be selected");

        dropDown.selectByValue("2");
        assertTrue(option2.isSelected(), "The option 2 should be selected");
        assertEquals(dropDown.getAllSelectedOptions().size(), 1, "Only option 2 should be selected");
    }
}
