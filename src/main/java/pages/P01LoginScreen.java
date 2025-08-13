package pages;

import base.WebPageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01LoginScreen extends WebPageHelper {
    private final By username_input = By.xpath("//*[@name='username' and @placeholder='Username']");
    private final By password_input = By.xpath("//*[@name='password' and @placeholder='Password']");
    private final By submit_login_CTA = By.xpath("//*[@type='submit']");
    private final By dashboardTitle = By.xpath("//*[normalize-space()='Dashboard']");
    private final By userbutton = By.xpath("//*[@class='oxd-userdropdown-tab']");
    private final By logoutbutton = By.linkText("Logout");
    private final By invalidmessge = By.xpath("//*[normalize-space()= 'Invalid credentials']");

    public P01LoginScreen(WebDriver driver) {
        super(driver);
    }

    public void checkHomeScreen() {
        waitForVisibilityOfElement(submit_login_CTA);
        elementsValidator(username_input, password_input, submit_login_CTA);
    }

    public void checkLoginSuccessfully(String username, String password) {
        sendTextToInputField(username, username_input);
        sendTextToInputField(password, password_input);
        clickOnElement(submit_login_CTA);
        waitForVisibilityOfElement(dashboardTitle);
    }

    public void logOut() {
        clickOnElement(userbutton);
        clickOnElement(logoutbutton);
    }

    public void checkInvalidLogin(String username, String password) {
        sendTextToInputField(username, username_input);
        sendTextToInputField(password, password_input);
        clickOnElement(submit_login_CTA);
        waitForVisibilityOfElement(invalidmessge);
    }


}