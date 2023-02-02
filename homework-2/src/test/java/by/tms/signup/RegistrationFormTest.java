package by.tms.signup;

import by.tms.config.ChromeConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RegistrationFormTest {
    private WebDriver webDriver;
    private final String VALID_NAME = "Name";
    private final String VALID_LAST_NAME = "Surname";
    private final String VALID_EMAIL = "test@test.com";
    private final String VALID_PASSWORD1 = "12345";
    private final String VALID_PASSWORD2 = "1234";

    @BeforeClass
    public void setUp() {
        ChromeConfig chromeConfig = new ChromeConfig();
        webDriver = chromeConfig.getWebDriver();
    }

    @BeforeMethod
    public void launchBrowser() {
        webDriver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
    }

    @AfterClass
    public void terminateBrowser() {
        webDriver.quit();
    }

    @DataProvider(name = "invalidNames")
    public static Object[] invalidNames() {
        return new String[]{
                "1",
                "Na2me",
                "NAme",
                "",
                " ",
                " Name",
                "Name ",
                "Nam e",
                "Namenamenamenamenamenamename",
                "name",
                "имя",
                "<script>",
                "\"SELECT\""
        };
    }

    @DataProvider(name = "invalidEmails")
    public static Object[] invalidEmails() {
        return new String[]{
                "",
                " ",
                " test@test.com",
                "test@test.com ",
                "test@test.",
                "test@test",
                "@test.com",
                "test",
                "тест@test.com",
                "тест",
                "<script>",
                "\"SELECT\""
        };
    }

    @DataProvider(name = "invalidPasswords")
    public static Object[] invalidPasswords() {
        return new String[]{
                "",
                " ",
                " pass",
                "pass ",
                "пароLь",
                "пароль",
                "<script>",
                "\"SELECT\""
        };
    }

    @Test
    public void validRegistrationForm() {
        submitRegistrationForm(VALID_NAME, VALID_LAST_NAME, VALID_EMAIL, VALID_PASSWORD1, VALID_PASSWORD1);
        verifyConfirmationMessage();
    }

    @Test(dataProvider = "invalidNames")
    public void invalidFirstNameValue(String name) {
        submitRegistrationForm(name, VALID_LAST_NAME, VALID_EMAIL, VALID_PASSWORD1, VALID_PASSWORD1);
        verifyValidationErrorMessage();
    }

    @Test(dataProvider = "invalidNames")
    public void invalidLastNameValue(String lastName) {
        submitRegistrationForm(VALID_NAME, lastName, VALID_EMAIL, VALID_PASSWORD1, VALID_PASSWORD1);
        verifyValidationErrorMessage();
    }

    @Test(dataProvider = "invalidEmails")
    public void invalidEmail(String email) {
        submitRegistrationForm(VALID_NAME, VALID_LAST_NAME, email, VALID_PASSWORD1, VALID_PASSWORD1);
        verifyValidationErrorMessage();
    }

    @Test(dataProvider = "invalidPasswords")
    public void invalidPasswordValue(String password) {
        submitRegistrationForm(VALID_NAME, VALID_LAST_NAME, VALID_EMAIL, password, password);
        verifyValidationErrorMessage();
    }

    @Test
    public void confirmationPasswordEqualsUserPassword() {
        submitRegistrationForm(VALID_NAME, VALID_LAST_NAME, VALID_EMAIL, VALID_PASSWORD1, VALID_PASSWORD1);
        verifyConfirmationMessage();
    }

    @Test
    public void confirmationPasswordNotEqualsUserPassword() {
        submitRegistrationForm(VALID_NAME, VALID_LAST_NAME, VALID_EMAIL, VALID_PASSWORD1, VALID_PASSWORD2);
        verifyValidationErrorMessage();
    }

    private void submitRegistrationForm(String firstName, String lastName, String email, String password1, String password2) {
        webDriver.findElement(By.name("first_name")).sendKeys(firstName);
        webDriver.findElement(By.name("last_name")).sendKeys(lastName);
        webDriver.findElement(By.name("email")).sendKeys(email);
        webDriver.findElement(By.name("password1")).sendKeys(password1);
        webDriver.findElement(By.name("password2")).sendKeys(password2);
        webDriver.findElement(By.cssSelector("input[value='Register']")).click();
    }

    private void verifyValidationErrorMessage() {
        WebElement errorMessageSpan = webDriver.findElement(By.className("error_message"));

        assertTrue(errorMessageSpan.isDisplayed(), "Error message should be displayed");
        assertEquals(errorMessageSpan.getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used",
                "Error message isn't correct");
    }

    private void verifyConfirmationMessage() {
        WebElement confirmationMessageSpan = webDriver.findElement(By.className("confirmation_message"));

        assertTrue(confirmationMessageSpan.isDisplayed(), "Registration data is invalid");
        assertEquals(confirmationMessageSpan.getText(), "Account is created!", "Confirmation message is not correct");
    }
}
