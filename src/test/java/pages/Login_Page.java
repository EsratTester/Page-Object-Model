package pages;

import basedreivers.PageDriver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonMethods;
import utilities.Screenshots;
import java.io.IOException;


public class Login_Page extends CommonMethods {

    ExtentTest test;

    public Login_Page(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(),this);
        this.test = test;
    }

    @FindBys({ @FindBy(xpath = "//input[@name='username']"),
            @FindBy(xpath = "//input[@placeholder='Username']")


    })
    WebElement username;

    @FindBys({ @FindBy(xpath = "//input[@name='password']") })
    WebElement password;

    @FindBys({ @FindBy(xpath = "//button[@type='submit']") })
    WebElement submit;




    // Report and Screenshot
    public void passCase(String message) {
        test.pass("<p style='color:#85BC63; font-size:14px'><b>" + message + "</b></p>");
    }

    public void passCaseWithSC(String message, String screenshotname) throws IOException {
        test.pass("<p style='color:#22dd3e; font-size:14px'><b>" + message + "</b></p>");
        String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(),""+ screenshotname +"");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + screenshotname + ".png";
        test.info(dest);
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }

    public void failCase(String message,String screenshotname) throws IOException{
        test.fail("<p style='color:Red; font-size:14px'><b>" + message + "</b></p>");
        Throwable t = new InterruptedException("Exception");
        test.fail(t);

        String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(),""+ screenshotname +"");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + screenshotname + ".png";
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());


        PageDriver.getCurrentDriver().quit();
    }

    public void login() throws IOException {
        try{
            test.info("Please enter your username");
            if (username.isDisplayed()){
                username.sendKeys("Admin");
                passCase("You have successfully entered your username");
                Thread.sleep(5000);

                try {
                    test.info("Please enter your password");
                    if(password.isDisplayed()){
                        password.sendKeys("admin123");
                        passCase("You have successfully entered your password");
                        Thread.sleep(5000);

                        try{
                            test.info("Please click on Login Button");
                            if (submit.isDisplayed()){
                                submit.click();
                                Thread.sleep(5000);
                                passCaseWithSC("You Successfully logged in","login_success");
                            }
                        }catch (Exception e) {
                            failCase("Login Button was not Locate ","login_button_fail");
                        }

                    }
                } catch (Exception e) {
                    failCase("Password was not Locate ","pass_fail");
                }
            }

        }catch (Exception e){
            failCase("User name was not Locate ","user_name_fail");

        }
    }
}
