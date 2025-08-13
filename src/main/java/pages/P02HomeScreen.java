package pages;

import base.WebPageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02HomeScreen extends WebPageHelper {
    private final By side_icon_arrow = By.xpath("//*[@class='oxd-icon-button oxd-main-menu-button' and @type='button']");
    private final By orange_logo = By.xpath("//*[@alt='client brand banner']");
    private final By admin_text = By.xpath("//*[normalize-space()='Admin']");
    private final By admin_icon = By.xpath("(//*[@role='presentation'])[2]");
    private final By username = By.xpath("(//*[@class=\"oxd-input oxd-input--active\"])[2]");
    private final By useRole = By.xpath("(//*[@class=\"oxd-select-text-input\"])[1]");
    private final By Admin = By.xpath("(//*[normalize-space()= 'Admin'])[7]");
    private final By EmployeeName = By.xpath("//*[@placeholder=\"Type for hints...\"]");
    private final By EmployeeNameOptions = By.xpath("//*[normalize-space()='Ravi M B']");
    private final By status = By.xpath("(//*[@class=\"oxd-select-text oxd-select-text--active\"])[2]");
    private final By Enabled = By.xpath("(//*[normalize-space()= 'Enabled'])[1]");
    private final By addbutton = By.xpath("(//*[@class=\"oxd-icon bi-plus oxd-button-icon\"])[1]");
    private final By savebutton = By.xpath("(//*[normalize-space()='Save'])[1]");
    private final By password = By.xpath("(//*[@type=\"password\"])[1]");
    private final By confirmpassword = By.xpath("(//*[@type=\"password\"])[2]");
    private final By success_message = By.xpath("//*[@id='oxd-toaster_1']");

    public P02HomeScreen(WebDriver driver) {
        super(driver);
    }

    public void checkSideBar() {
        elementsValidator(orange_logo, admin_text, admin_icon);
    }

    public void navigateToAdmin() {
        clickOnElement(admin_text);
        waitForVisibilityOfElement(users_system_text);
    }

    public void writeUserName() {
        String randomUsername = generateRandomUsername();
        sendTextToInputField(randomUsername, username);
    }

    public void chooseUserole() {
        clickOnElement(useRole);
        hoover(Admin);
    }

    public void EmployeeName() {
        sendTextToInputField("R", EmployeeName);
        hoover(EmployeeNameOptions);
    }

    public void chooseStatus() {
        clickOnElement(status);
        hoover(Enabled);
    }

    public void clickAddButton() {
        clickOnElement(addbutton);
    }

    public void setPassword() {
        sendTextToInputField("o1234567", password);
    }

    public void confirmPassword() {
        sendTextToInputField("o1234567", confirmpassword);
    }

    public void clickSaveButton() {
        clickOnElement(savebutton);
    }

    public void checkAccountSaved() {
        checkContainsText(success_message, "Success");

    }


}
