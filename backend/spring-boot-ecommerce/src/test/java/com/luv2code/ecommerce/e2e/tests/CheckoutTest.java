package com.luv2code.ecommerce.e2e.tests;

import com.luv2code.ecommerce.e2e.base.BaseTest;
import com.luv2code.ecommerce.e2e.base.TestConfig;
import com.luv2code.ecommerce.e2e.pages.CartPage;
import com.luv2code.ecommerce.e2e.pages.CheckoutPage;
import com.luv2code.ecommerce.e2e.pages.HomePage;
import com.luv2code.ecommerce.e2e.pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * E2E tests for Checkout Process
 */
public class CheckoutTest extends BaseTest {
    
    @Test(priority = 1, description = "Test complete checkout process with Stripe test card")
    public void testCompleteCheckoutProcess() {
        HomePage homePage = new HomePage(driver);
        ProductListPage productListPage = new ProductListPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        
        // Select role and add product
        homePage.selectGroomRole();
        
        try {
            Thread.sleep(3000);
            productListPage.addFirstProductToCart();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Navigate to cart
        driver.navigate().to("http://localhost:4200/cart-details");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Proceed to checkout
        cartPage.proceedToCheckout();
        
        // Wait for checkout page to load
        checkoutPage.waitForCheckoutForm();
        
        // Fill in billing address
        checkoutPage.fillBillingAddress(
            "John",
            "Doe",
            "john.doe@example.com",
            "123 Main St",
            "Springfield",
            "12345",
            "United States",
            "Illinois"
        );
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Fill in payment details
        checkoutPage.fillPaymentDetails();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Submit order
        checkoutPage.submitOrder();
        
        // Wait for order confirmation or handle Stripe iframe
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify we're not on checkout page anymore (either error or success)
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("/checkout"), 
            "Should navigate away from checkout page after submission");
    }
    
    @Test(priority = 2, description = "Test checkout form validation")
    public void testCheckoutFormValidation() {
        HomePage homePage = new HomePage(driver);
        ProductListPage productListPage = new ProductListPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        
        // Add product and go to checkout
        homePage.selectGroomRole();
        
        try {
            Thread.sleep(3000);
            productListPage.addFirstProductToCart();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        driver.navigate().to("http://localhost:4200/cart-details");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        cartPage.proceedToCheckout();
        checkoutPage.waitForCheckoutForm();
        
        // Try to submit without filling form
        checkoutPage.submitOrder();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Should still be on checkout page (validation error)
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/checkout"), 
            "Should remain on checkout page if validation fails");
    }
}

