package pages;

import base.WebPageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class P03AdminScreen extends WebPageHelper {
    private final By Admin_input = By.xpath("(//*[@class='oxd-input oxd-input--active'])[2]");
    private final By submit_search = By.xpath("//*[normalize-space()='Search' and @type='submit']");
    private final By results_title = By.xpath("(//*[@class='oxd-text oxd-text--span'])[1]");
    private final By delete_icon = By.xpath("//*[@class='oxd-icon bi-trash']");
    private final By error_message = By.xpath("//*[@id='oxd-toaster_1']");

    public P03AdminScreen(WebDriver driver) {
        super(driver);
    }

    public void SearchAdmin(String admin) {
        sendTextToInputField(admin, Admin_input);
        clickOnElement(submit_search);
        try {
            waitForVisibilityOfElement(results_title);
        } catch (Exception e) {
            scrollToElement(results_title);
        }
        checkContainsText(results_title, "(1) Record Found");

    }

    public void checkAdminDelete() {
        scrollToElement(delete_icon);
        Assert.assertEquals(driver.findElements(delete_icon).size(), 1);
        clickOnElement(delete_icon);
        waitForVisibilityOfElement(error_message);
        checkContainsText(error_message, "Cannot be deleted");
    }
}
