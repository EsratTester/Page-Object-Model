package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

public class Screenshots {

    // Define a public static method named capture that takes a WebDriver instance and a screenshot name as parameters
    // This method returns the path of the captured screenshot as a String and throws IOException
    public static String capture(WebDriver driver, String screenShotName) throws IOException {
        // Cast the WebDriver instance to TakesScreenshot to enable screenshot capturing
        TakesScreenshot ts = (TakesScreenshot) driver;

        // Capture the screenshot and store it as a File object
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Construct the destination path for the screenshot file
        String screenshotName = "screenshot_" + System.currentTimeMillis();
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + screenShotName + ".png";

        // Create a new File object for the destination path
        File destination = new File(dest);

        // Copy the screenshot file to the destination path
        FileUtils.copyFile(source, destination);

        // Return the destination path of the screenshot
        return dest;
    }



}
