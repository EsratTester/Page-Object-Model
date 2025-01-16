package basedreivers;

import org.openqa.selenium.WebDriver;

public class PageDriver {
        // Define a public static final ThreadLocal variable named webDriver to hold
        // WebDriver instances for each thread
        public static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

        // Define a private static variable named instance to hold a single instance of PageDriver
        private static PageDriver instance = null;

        // Private constructor to prevent instantiation from outside the class
        private PageDriver() {

        }

        // Public static method to get the single instance of PageDriver (Singleton pattern)
        public static PageDriver getInstance() {
            // Check if instance is null, meaning it has not been created yet
            if (instance == null) {
                // If null, create a new instance of PageDriver
                instance = new PageDriver();
            }
            // Return the single instance of PageDriver
            return instance;
        }

        // Public method to get the WebDriver instance for the current thread
        public WebDriver getDriver() {
            // Retrieve the WebDriver instance from the ThreadLocal variable
            return webDriver.get();
        }

        // Public method to set the WebDriver instance for the current thread
        public void setDriver(WebDriver driver) {
            // Store the WebDriver instance in the ThreadLocal variable
            webDriver.set(driver);
        }

        // Public static method to get the WebDriver instance for the current thread via the singleton instance
        public static WebDriver getCurrentDriver() {
            // Get the singleton instance of PageDriver and retrieve the WebDriver instance for the current thread
            return getInstance().getDriver();
        }
    }




