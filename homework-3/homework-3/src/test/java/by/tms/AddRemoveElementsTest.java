package by.tms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class AddRemoveElementsTest extends BaseTest {

    @Test
    public void addRemoveTest() {
        webDriver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        List<WebElement> addButtonElements = webDriver.findElements(By.xpath("//button[text()='Add Element']"));
        assertEquals(addButtonElements.size(), 1, "Only one add button element should be displayed");

        List<WebElement> deleteButtonList = getDeleteButtonList();
        assertEquals(deleteButtonList.size(), 0, "Delete button should not be displayed");

        WebElement addButtonElement = addButtonElements.get(0);
        addButtonElement.click();

        deleteButtonList = getDeleteButtonList();
        assertEquals(deleteButtonList.size(), 1, "Only one delete button should be displayed");

        addButtonElement.click();
        deleteButtonList = getDeleteButtonList();
        assertEquals(deleteButtonList.size(), 2, "Two delete button should be displayed");

        deleteButtonList.get(0).click();
        deleteButtonList = getDeleteButtonList();
        assertEquals(deleteButtonList.size(), 1, "Only one delete button should be displayed");
    }

    private List<WebElement> getDeleteButtonList() {
        return webDriver.findElements(By.xpath("//button[text()='Delete']"));
    }
}
