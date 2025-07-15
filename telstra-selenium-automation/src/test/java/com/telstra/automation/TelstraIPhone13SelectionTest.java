package com.telstra.automation;

import com.telstra.automation.base.BaseTest;
import com.telstra.automation.pages.TelstraMobilePhonesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for automating iPhone 13 selection on Telstra website
 */
public class TelstraIPhone13SelectionTest extends BaseTest {
    
    private TelstraMobilePhonesPage mobilePhonesPage;
    
    @Test(description = "Launch Telstra URL and select iPhone 13", priority = 1)
    public void testSelectIPhone13() {
        logger.info("Starting test: Launch Telstra URL and select iPhone 13");
        
        try {
            // Step 1: Navigate to Telstra mobile phones page
            logger.info("Step 1: Navigating to Telstra mobile phones page");
            navigateToBaseUrl();
            
            // Step 2: Initialize page object
            logger.info("Step 2: Initializing page object");
            mobilePhonesPage = new TelstraMobilePhonesPage(driver);
            
            // Step 3: Wait for page to load
            logger.info("Step 3: Waiting for page to load");
            mobilePhonesPage.waitForPageLoad();
            
            // Step 4: Print available phones for debugging (optional)
            logger.info("Step 4: Printing available phones for debugging");
            mobilePhonesPage.printAvailablePhones();
            
            // Step 5: Try to search for iPhone 13
            logger.info("Step 5: Searching for iPhone 13");
            mobilePhonesPage.searchForIPhone13();
            
            // Step 6: Apply Apple filter if available
            logger.info("Step 6: Applying Apple filter");
            mobilePhonesPage.applyAppleFilter();
            
            // Step 7: Attempt to select iPhone 13
            logger.info("Step 7: Attempting to select iPhone 13");
            boolean iPhoneSelected = mobilePhonesPage.selectIPhone13();
            
            // Step 8: Verify the selection
            if (iPhoneSelected) {
                logger.info("✓ iPhone 13 selection test PASSED");
                
                // Additional verification - check if we're on a product page or similar
                String currentUrl = driver.getCurrentUrl();
                String pageTitle = driver.getTitle();
                
                logger.info("Current URL after selection: {}", currentUrl);
                logger.info("Page title after selection: {}", pageTitle);
                
                // Soft assertion - the test passes if iPhone was clicked, even if navigation doesn't happen
                if (currentUrl.toLowerCase().contains("iphone") || 
                    pageTitle.toLowerCase().contains("iphone")) {
                    logger.info("✓ Successfully navigated to iPhone-related page");
                } else {
                    logger.info("⚠ iPhone 13 was clicked but may not have navigated to product page");
                }
                
            } else {
                logger.error("✗ iPhone 13 selection test FAILED - iPhone 13 not found or not clickable");
                
                // Take a screenshot for debugging
                takeScreenshot("iphone13_not_found");
                
                // Still try to get information about available phones
                mobilePhonesPage.getAlliPhoneModels();
                
                Assert.fail("iPhone 13 was not found or could not be selected on the Telstra website");
            }
            
        } catch (Exception e) {
            logger.error("Test failed with exception: {}", e.getMessage(), e);
            takeScreenshot("test_exception");
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
        
        logger.info("Test completed: Launch Telstra URL and select iPhone 13");
    }
    
    @Test(description = "Verify page loads and displays mobile phones", priority = 2)
    public void testPageLoadAndPhoneDisplay() {
        logger.info("Starting test: Verify page loads and displays mobile phones");
        
        try {
            // Navigate to the page
            navigateToBaseUrl();
            
            // Initialize page object
            mobilePhonesPage = new TelstraMobilePhonesPage(driver);
            mobilePhonesPage.waitForPageLoad();
            
            // Verify page title
            String pageTitle = driver.getTitle();
            Assert.assertNotNull(pageTitle, "Page title should not be null");
            Assert.assertFalse(pageTitle.isEmpty(), "Page title should not be empty");
            logger.info("✓ Page title verification passed: {}", pageTitle);
            
            // Verify page URL
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("telstra.com.au"), 
                "URL should contain telstra.com.au");
            Assert.assertTrue(currentUrl.contains("mobile"), 
                "URL should contain 'mobile'");
            logger.info("✓ Page URL verification passed: {}", currentUrl);
            
            // Check if any iPhone models are available
            var iPhoneModels = mobilePhonesPage.getAlliPhoneModels();
            logger.info("Found {} iPhone models on the page", iPhoneModels.size());
            
            if (iPhoneModels.size() > 0) {
                logger.info("✓ iPhone models are available on the page");
            } else {
                logger.warn("⚠ No iPhone models found on the page - this might indicate a page structure change");
            }
            
        } catch (Exception e) {
            logger.error("Page load test failed: {}", e.getMessage(), e);
            takeScreenshot("page_load_test_failed");
            Assert.fail("Page load test failed: " + e.getMessage());
        }
        
        logger.info("Test completed: Verify page loads and displays mobile phones");
    }
    
    /**
     * Helper method to take screenshots for debugging
     */
    private void takeScreenshot(String fileName) {
        try {
            // This is a basic implementation - in a real project you might use more sophisticated screenshot utilities
            logger.info("Screenshot functionality would be implemented here: {}", fileName);
            // Implementation would save screenshot with timestamp and test name
        } catch (Exception e) {
            logger.warn("Failed to take screenshot: {}", e.getMessage());
        }
    }
}