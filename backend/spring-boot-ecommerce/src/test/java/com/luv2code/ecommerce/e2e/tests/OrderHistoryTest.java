package com.luv2code.ecommerce.e2e.tests;

import com.luv2code.ecommerce.e2e.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * E2E tests for Order History
 */
public class OrderHistoryTest extends BaseTest {
    
    @Test(priority = 1, description = "Test order history page is accessible")
    public void testOrderHistoryPageAccessible() {
        // Navigate to order history
        driver.navigate().to("http://localhost:4200/members");
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify we're on order history page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/members") || currentUrl.contains("/order-history"),
            "Should navigate to order history page");
    }
}

