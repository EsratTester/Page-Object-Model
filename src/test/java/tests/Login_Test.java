package tests;

import basedreivers.PageDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Login_Page;
import utilities.CommonMethods;
import utilities.ExtentFactory;
import java.io.IOException;
import static basedreivers.BaseDriver.url;

public class Login_Test extends CommonMethods {

    ExtentReports extent;
    ExtentTest parentTest;
    ExtentTest childTest;

    @BeforeClass
    public void open_url() throws InterruptedException {
        PageDriver.getCurrentDriver().get(url);
        sleep();
        extent = ExtentFactory.getInstance();
        parentTest = extent.createTest("<p style=\"color:Green; font-size:14px\"><b>Orange HRM </b></p>").assignAuthor("Esrat").assignDevice("Windows");
    }

    @Test
    public void orangehrm_login() throws IOException {
        childTest = parentTest.createNode("<p style=\"color:Green; font-size:14px\"><b>Login</b></p>");
        Login_Page login_page = new Login_Page(childTest);
        login_page.login();

    }

   @AfterClass
    public void report(){
        extent.flush();
    }
}
