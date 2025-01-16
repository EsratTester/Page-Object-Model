package utilities;

import basedreivers.BaseDriver;
import basedreivers.PageDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CommonMethods extends BaseDriver {

    public void sleep() throws InterruptedException{
        Thread.sleep(5000);
    }

    public String getCurrentPageUrl() {
        return PageDriver.getCurrentDriver().getCurrentUrl();
    }

    public String getTrimmedPageUrl() {
        String currentPageUrl = getCurrentPageUrl();
        return currentPageUrl.substring(currentPageUrl.indexOf(".com/") + 5);
    }

    public String getPageTitle() {
        return PageDriver.getCurrentDriver().getTitle();
    }

    public void refreshPage() {
        PageDriver.getCurrentDriver().navigate().refresh();
        scrollPageUp();
    }

    public void navigateToUrl(String url) {
        PageDriver.getCurrentDriver().navigate().to(url);
    }

    /*************************
     * Input Utilities *
     *************************/

    public void enterText(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public void enterTextUsingJs(WebElement element, String value) {
        ((JavascriptExecutor) PageDriver.getCurrentDriver())
                .executeScript("arguments[0].value=arguments[1];", element, value);
    }

    /*************************
     * Action Utilities *
     *************************/

    public void hoverOverElement(WebElement element) {
        new Actions(PageDriver.getCurrentDriver()).moveToElement(element).perform();
    }

    public void doubleClickElement(WebElement element) {
        new Actions(PageDriver.getCurrentDriver()).doubleClick(element).perform();
    }

    public void clickUsingJs(WebElement element) {
        ((JavascriptExecutor) PageDriver.getCurrentDriver()).executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) PageDriver.getCurrentDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollPageUp() {
        new Actions(PageDriver.getCurrentDriver()).sendKeys(Keys.PAGE_UP).perform();
    }

    public void scrollPageDown() {
        new Actions(PageDriver.getCurrentDriver()).sendKeys(Keys.PAGE_DOWN).perform();
    }

    /*************************
     * Dropdown Utilities *
     *************************/

    public void selectByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    public void selectByVisibleText(WebElement element, String value) {
        new Select(element).selectByVisibleText(value);
    }

    public void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    public String getSelectedOptionText(WebElement element) {
        return new Select(element).getFirstSelectedOption().getText();
    }

    public boolean isOptionSelected(List<WebElement> elements, String value) {
        return elements.stream()
                .anyMatch(element -> element.getAttribute("value").equals(value) && element.isSelected());
    }

    /*************************
     * Wait Utilities *
     *************************/

    private WebDriverWait wait = new WebDriverWait(PageDriver.getCurrentDriver(), Duration.ofSeconds(30));

    public void waitForSeconds(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForPageLoad() {
        wait.until(wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    /*************************
     * Window Utilities *
     *************************/

    public List<String> getAllWindowHandles() {
        return new ArrayList<>(PageDriver.getCurrentDriver().getWindowHandles());
    }

    public void openNewTab() {
        PageDriver.getCurrentDriver().switchTo().newWindow(WindowType.TAB);
    }


    public void closeAllTabsExceptCurrent() {
        String currentHandle = PageDriver.getCurrentDriver().getWindowHandle();
        for (String handle : PageDriver.getCurrentDriver().getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                PageDriver.getCurrentDriver().switchTo().window(handle).close();
            }
        }
        PageDriver.getCurrentDriver().switchTo().window(currentHandle);
    }

    /*************************
     * Validation Utilities *
     *************************/

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    /*************************
     * JavaScript Execution Utilities *
     *************************/

    public Object executeJs(String script, Object... args) {
        return ((JavascriptExecutor) PageDriver.getCurrentDriver()).executeScript(script, args);
    }

    /*************************
     * Highlight Element *
     *************************/

    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) PageDriver.getCurrentDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

}
