package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(String directoryPath, String baseName, WebDriver driver) throws IOException {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver is null. Cannot capture screenshot.");
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        String fileName = baseName + "_" + timestamp + ".png";
        Path filePath = Paths.get(directoryPath, fileName);
        Files.createDirectories(filePath.getParent());

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.move(screenshot.toPath(), filePath, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("[ScreenshotUtils] Screenshot saved to: " + filePath.toAbsolutePath());

        return filePath.toAbsolutePath().toString();
    }
}
