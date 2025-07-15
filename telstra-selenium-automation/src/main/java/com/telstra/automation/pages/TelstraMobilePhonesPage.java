package com.telstra.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object Model for Telstra Mobile Phones page
 * Contains methods to interact with mobile phone selection elements
 */
public class TelstraMobilePhonesPage {
    
    private static final Logger logger = LogManager.getLogger(TelstraMobilePhonesPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    
    // Page elements using @FindBy annotations
    @FindBy(xpath = "//input[@placeholder='Search' or @placeholder='Search phones' or contains(@placeholder, 'search')]")
    private WebElement searchBox;
    
    @FindBy(xpath = "//button[contains(text(), 'Search') or @type='submit']")
    private WebElement searchButton;
    
    @FindBy(xpath = "//div[contains(@class, 'filter') or contains(@class, 'category')]//span[contains(text(), 'iPhone') or contains(text(), 'Apple')]")
    private WebElement appleFilter;
    
    @FindBy(xpath = "//div[contains(@class, 'product') or contains(@class, 'phone') or contains(@class, 'mobile')]")
    private List<WebElement> mobilePhoneCards;
    
    // Constructor
    public TelstraMobilePhonesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        logger.info("TelstraMobilePhonesPage initialized");
    }
    
    /**
     * Wait for the page to load completely
     */
    public TelstraMobilePhonesPage waitForPageLoad() {
        logger.info("Waiting for mobile phones page to load");
        
        try {
            // Wait for page title to contain expected text
            wait.until(ExpectedConditions.titleContains("Mobile"));
            
            // Wait for main content to be visible
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'content') or contains(@class, 'main')]")));
            
            logger.info("Mobile phones page loaded successfully");
        } catch (Exception e) {
            logger.warn("Standard page load wait failed, continuing anyway: {}", e.getMessage());
        }
        
        return this;
    }
    
    /**
     * Search for iPhone 13 using search functionality
     */
    public TelstraMobilePhonesPage searchForIPhone13() {
        logger.info("Searching for iPhone 13");
        
        try {
            // Try to find and use search box if available
            WebElement searchElement = findSearchBox();
            if (searchElement != null) {
                searchElement.clear();
                searchElement.sendKeys("iPhone 13");
                
                // Try to click search button
                WebElement searchBtn = findSearchButton();
                if (searchBtn != null) {
                    searchBtn.click();
                    logger.info("Search initiated for iPhone 13");
                }
            }
        } catch (Exception e) {
            logger.info("Search functionality not available or failed: {}", e.getMessage());
        }
        
        return this;
    }
    
    /**
     * Apply Apple/iPhone filter if available
     */
    public TelstraMobilePhonesPage applyAppleFilter() {
        logger.info("Attempting to apply Apple/iPhone filter");
        
        try {
            List<WebElement> filters = driver.findElements(By.xpath(
                "//button[contains(text(), 'Apple') or contains(text(), 'iPhone')] | " +
                "//div[contains(@class, 'filter')]//span[contains(text(), 'Apple') or contains(text(), 'iPhone')] | " +
                "//label[contains(text(), 'Apple') or contains(text(), 'iPhone')]"
            ));
            
            for (WebElement filter : filters) {
                if (filter.isDisplayed() && filter.isEnabled()) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", filter);
                    logger.info("Applied Apple filter successfully");
                    Thread.sleep(2000); // Wait for filter to apply
                    break;
                }
            }
        } catch (Exception e) {
            logger.info("Apple filter not available or failed to apply: {}", e.getMessage());
        }
        
        return this;
    }
    
    /**
     * Find and select iPhone 13 from the available phones
     */
    public boolean selectIPhone13() {
        logger.info("Attempting to select iPhone 13");
        
        try {
            // Multiple strategies to find iPhone 13
            List<String> iphone13Selectors = List.of(
                "//div[contains(@class, 'product') or contains(@class, 'phone') or contains(@class, 'card')]" +
                "[.//text()[contains(., 'iPhone 13') and not(contains(., 'iPhone 13 Pro'))]]",
                
                "//a[contains(@href, 'iphone-13') and not(contains(@href, 'pro'))]",
                
                "//h3[contains(text(), 'iPhone 13') and not(contains(text(), 'Pro'))]/..",
                
                "//*[contains(text(), 'iPhone 13') and not(contains(text(), 'Pro')) and not(contains(text(), 'mini'))]" +
                "/ancestor::div[contains(@class, 'product') or contains(@class, 'card') or contains(@class, 'item')]"
            );
            
            for (String selector : iphone13Selectors) {
                List<WebElement> iphone13Elements = driver.findElements(By.xpath(selector));
                
                for (WebElement element : iphone13Elements) {
                    if (element.isDisplayed()) {
                        // Scroll to element
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        js.executeScript("arguments[0].scrollIntoView(true);", element);
                        Thread.sleep(1000);
                        
                        // Try to click the element
                        try {
                            element.click();
                            logger.info("Successfully selected iPhone 13");
                            return true;
                        } catch (Exception e) {
                            // If direct click fails, try JavaScript click
                            js.executeScript("arguments[0].click();", element);
                            logger.info("Successfully selected iPhone 13 using JavaScript click");
                            return true;
                        }
                    }
                }
            }
            
            // If specific iPhone 13 not found, look for any iPhone 13 mention
            List<WebElement> allElements = driver.findElements(By.xpath("//*[contains(text(), 'iPhone 13')]"));
            for (WebElement element : allElements) {
                String text = element.getText().toLowerCase();
                if (text.contains("iphone 13") && !text.contains("pro") && !text.contains("mini")) {
                    WebElement clickableParent = findClickableParent(element);
                    if (clickableParent != null) {
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        js.executeScript("arguments[0].scrollIntoView(true);", clickableParent);
                        Thread.sleep(1000);
                        js.executeScript("arguments[0].click();", clickableParent);
                        logger.info("Successfully selected iPhone 13 through parent element");
                        return true;
                    }
                }
            }
            
        } catch (Exception e) {
            logger.error("Failed to select iPhone 13: {}", e.getMessage());
        }
        
        logger.warn("iPhone 13 not found or not selectable on the page");
        return false;
    }
    
    /**
     * Get all available iPhone models on the page
     */
    public List<WebElement> getAlliPhoneModels() {
        logger.info("Getting all iPhone models from the page");
        
        List<WebElement> iphones = driver.findElements(By.xpath(
            "//*[contains(text(), 'iPhone') or contains(text(), 'iphone')]" +
            "/ancestor-or-self::div[contains(@class, 'product') or contains(@class, 'card') or contains(@class, 'item')]"
        ));
        
        logger.info("Found {} iPhone models on the page", iphones.size());
        return iphones;
    }
    
    /**
     * Print all available phones for debugging
     */
    public void printAvailablePhones() {
        logger.info("Printing all available phones for debugging");
        
        List<WebElement> phones = driver.findElements(By.xpath(
            "//div[contains(@class, 'product') or contains(@class, 'phone') or contains(@class, 'card')] | " +
            "//*[contains(text(), 'iPhone') or contains(text(), 'Samsung') or contains(text(), 'Google')]"
        ));
        
        for (int i = 0; i < phones.size(); i++) {
            try {
                String text = phones.get(i).getText();
                if (!text.trim().isEmpty()) {
                    logger.info("Phone {}: {}", i + 1, text.trim());
                }
            } catch (Exception e) {
                logger.debug("Could not get text for element {}: {}", i + 1, e.getMessage());
            }
        }
    }
    
    // Helper methods
    private WebElement findSearchBox() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//input[@placeholder='Search' or @placeholder='Search phones' or contains(@placeholder, 'search') or @type='search']"
            )));
        } catch (Exception e) {
            return null;
        }
    }
    
    private WebElement findSearchButton() {
        try {
            return driver.findElement(By.xpath(
                "//button[contains(text(), 'Search') or @type='submit' or contains(@class, 'search')]"
            ));
        } catch (Exception e) {
            return null;
        }
    }
    
    private WebElement findClickableParent(WebElement element) {
        WebElement current = element;
        for (int i = 0; i < 5; i++) { // Go up to 5 levels
            try {
                current = current.findElement(By.xpath(".."));
                String tagName = current.getTagName().toLowerCase();
                String className = current.getAttribute("class");
                
                if (tagName.equals("a") || tagName.equals("button") || 
                    (className != null && (className.contains("clickable") || 
                     className.contains("product") || className.contains("card")))) {
                    return current;
                }
            } catch (Exception e) {
                break;
            }
        }
        return null;
    }
}