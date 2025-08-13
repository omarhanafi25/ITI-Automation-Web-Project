package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class WebPageHelper {
    protected final By loginTitle = By.cssSelector("//*[normalize-space()='Login']");
    protected final By users_system_text = By.xpath("//*[normalize-space()='System Users']");
    public WebDriver driver;

    public WebPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnElement(By by) {
        waitForVisibilityOfElement(by);
        driver.findElement(by).click();
    }

    public void waitForVisibilityOfElement(By by) {
        new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public Boolean assertElementDisplayed(By by) {
        waitForVisibilityOfElement(by);
        return driver.findElement(by).isDisplayed();
    }

    public void checkContainsText(By by, String text) {
        waitForVisibilityOfElement(by);
        Assert.assertTrue(driver.findElement(by).getText().contains(text));
    }

    public void scrollToElement(By element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                driver.findElement(element));
    }


    public void sendTextToInputField(String text, By by) {
        waitForVisibilityOfElement(by);
        clearInputField(by);
        driver.findElement(by).sendKeys(text);
    }

    public void clearInputField(By by) {
        driver.findElement(by).clear();
    }

    public void elementsValidator(By... screenLocators) {
        for (By locator : screenLocators) {
            try {
                Assert.assertTrue(assertElementDisplayed(locator));
            } catch (Exception e) {
                scrollToElement(locator);
                Assert.assertTrue(assertElementDisplayed(locator));
            }
        }
    }

    public void test(String... names) {
        for (String name : names) {
            System.out.println(name);
        }
    }

    public void hoover(By by) {
        waitForVisibilityOfElement(by);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(by)).perform();
        clickOnElement(by);
    }

    public String generateRandomUsername() {
        String prefix = "omar";
        int randomNumber = (int) (Math.random() * 1000);
        return prefix + randomNumber;
    }


}
