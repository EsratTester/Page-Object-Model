package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentFactory {
    // Define a public static final ExtentReports variable named extentReports to hold the ExtentReports instance
    public static final ExtentReports extentReports = new ExtentReports();

    // Define a public synchronized static method to get the instance of ExtentReports
    public synchronized static ExtentReports getInstance() {
        // Create a new ExtentSparkReporter with the specified report file path
        ExtentSparkReporter reporter = new ExtentSparkReporter("./reports/Report.html");

        // Configure the reporter with a report name
        reporter.config().setReportName("Automation - Esrat");

        // Attach the reporter to the extentReports instance
        extentReports.attachReporter(reporter);

        // Return the extentReports instance
        return extentReports;
    }

}
