package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.ExtentReportManager;

import java.io.IOException;
import java.lang.reflect.Method;

import static utilities.ExtentReportManager.*;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeTest(alwaysRun = true)
    @Parameters("browser")
    public void startDriverSession(@Optional("chrome") String browser) {
        try {
            initializeDriver(browser);
            startReporting();
        } catch (Exception e) {
            throw new RuntimeException("Failed to start WebDriver session", e);
        }
    }

    private void initializeDriver(String browser) {
        WebDriver localDriver;

        switch (browser.toLowerCase()) {
            case "chrome":
                localDriver = new ChromeDriver();
                break;
            case "edge":
                localDriver = new EdgeDriver();
                break;
            case "firefox":
                localDriver = new FirefoxDriver();
                break;
            case "safari":
                localDriver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.set(localDriver);
        configureDriver();
    }


    private void configureDriver() {
        getDriver().manage().window().maximize();
        getDriver().get(PropertiesLoader.readEnvFile("URL"));
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) throws IOException {
        ExtentReportManager.beforeMethod(method);
    }

    @AfterMethod(alwaysRun = true)
    public void A_takeScreenShot(ITestResult result) throws IOException, InterruptedException {
        takeScreenshot(result, getDriver());
        System.out.println("screenshot captured");
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
        flushReport();
        System.out.println("Report path is here:  " + reportName);
    }
}
