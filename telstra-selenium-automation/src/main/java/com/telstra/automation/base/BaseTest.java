package com.telstra.automation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Base test class for all Selenium automation tests
 * Handles WebDriver initialization, configuration, and cleanup
 */
public class BaseTest {
    
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    // Configuration constants
    protected static final int IMPLICIT_WAIT_TIMEOUT = 10;
    protected static final int EXPLICIT_WAIT_TIMEOUT = 20;
    protected static final String BASE_URL = "https://www.telstra.com.au/mobile-phones/mobiles-on-a-plan";
    
    /**
     * Setup method that runs before each test
     * Initializes WebDriver based on browser parameter
     */
    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        logger.info("Setting up WebDriver for browser: {}", browser);
        
        try {
            driver = initializeDriver(browser);
            configureDriver();
            logger.info("WebDriver setup completed successfully");
        } catch (Exception e) {
            logger.error("Failed to setup WebDriver: {}", e.getMessage());
            throw new RuntimeException("WebDriver setup failed", e);
        }
    }
    
    /**
     * Initialize WebDriver based on browser type
     */
    private WebDriver initializeDriver(String browser) {
        WebDriver driver;
        
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                // Add headless mode option for CI/CD environments
                if (System.getProperty("headless", "false").equals("true")) {
                    chromeOptions.addArguments("--headless");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (System.getProperty("headless", "false").equals("true")) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
                
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        
        return driver;
    }
    
    /**
     * Configure WebDriver with timeouts and other settings
     */
    private void configureDriver() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        
        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
    }
    
    /**
     * Navigate to the base URL
     */
    protected void navigateToBaseUrl() {
        logger.info("Navigating to: {}", BASE_URL);
        driver.get(BASE_URL);
        logger.info("Successfully navigated to base URL");
    }
    
    /**
     * Cleanup method that runs after each test
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing WebDriver");
            driver.quit();
            logger.info("WebDriver closed successfully");
        }
    }
    
    /**
     * Get the current WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }
    
    /**
     * Get the WebDriverWait instance
     */
    public WebDriverWait getWait() {
        return wait;
    }
}