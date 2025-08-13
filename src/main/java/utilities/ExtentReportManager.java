package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static final String BASE_DIR = System.getProperty("user.dir") + "/reports/";
    private static final String SCREENSHOT_DIR = BASE_DIR + "screenshots/";
    public static String reportName;
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;

    public static void startReporting() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        reportName = BASE_DIR + "extentReports/extentReport_" + timestamp + ".html";

        htmlReporter = new ExtentHtmlReporter(reportName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        createDirectory(BASE_DIR + "extentReports");
        createDirectory(SCREENSHOT_DIR + "failure");
        createDirectory(SCREENSHOT_DIR + "pass");
        createDirectory(SCREENSHOT_DIR + "skip");
    }

    public static void beforeMethod(Method method) {
        ExtentTest test = extent.createTest(method.getName());
        testThread.set(test);
    }

    public static void takeScreenshot(ITestResult result, WebDriver driver) {
        ExtentTest test = testThread.get();
        String methodName = result.getName();

        String statusFolder;
        String label;

        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                statusFolder = "failure";
                label = "FAIL";
                break;
            case ITestResult.SUCCESS:
                statusFolder = "pass";
                label = "PASS";
                break;
            case ITestResult.SKIP:
                statusFolder = "skip";
                label = "SKIP";
                break;
            default:
                return;
        }

        String screenshotFolderPath = SCREENSHOT_DIR + statusFolder + "/";
        String fileBaseName = label + "_" + methodName;

        try {
            String capturedPath = ScreenshotUtils.captureScreenshot(screenshotFolderPath, fileBaseName, driver);

            switch (result.getStatus()) {
                case ITestResult.FAILURE:
                    test.fail(result.getThrowable().getMessage(),
                            MediaEntityBuilder.createScreenCaptureFromPath(capturedPath).build());
                    break;
                case ITestResult.SUCCESS:
                    test.pass("Test passed",
                            MediaEntityBuilder.createScreenCaptureFromPath(capturedPath).build());
                    break;
                case ITestResult.SKIP:
                    test.skip(result.getThrowable() != null ? result.getThrowable().getMessage() : "Test skipped",
                            MediaEntityBuilder.createScreenCaptureFromPath(capturedPath).build());
                    break;
            }
        } catch (IOException e) {
            System.err.println("[ExtentReportManager] Screenshot error: " + e.getMessage());
        } finally {
            testThread.remove(); // ensure no memory leaks
        }
    }

    private static void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists() && !directory.mkdirs()) {
            System.err.println("Failed to create directory: " + path);
        }
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }
}
