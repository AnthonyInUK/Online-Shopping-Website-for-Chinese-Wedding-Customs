package com.luv2code.ecommerce.e2e.tests;

import com.luv2code.ecommerce.e2e.base.BaseTest;
import com.luv2code.ecommerce.e2e.pages.HomePage;
import com.luv2code.ecommerce.e2e.pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * E2E tests for Product Browsing
 */
public class ProductBrowsingTest extends BaseTest {
    
    @Test(priority = 1, description = "Test products are displayed after role selection")
    public void testProductsDisplayedAfterRoleSelection() {
        HomePage homePage = new HomePage(driver);
        ProductListPage productListPage = new ProductListPage(driver);
        
        // Select Groom role
        homePage.selectGroomRole();
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify products are displayed
        int productCount = productListPage.getProductCount();
        Assert.assertTrue(productCount > 0, "Products should be displayed after role selection");
    }
    
    @Test(priority = 2, description = "Test product badges are displayed")
    public void testProductBadgesDisplayed() {
        HomePage homePage = new HomePage(driver);
        ProductListPage productListPage = new ProductListPage(driver);
        
        // Select role
        homePage.selectGroomRole();
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Get first product
        var products = productListPage.getProducts();
        if (products.size() > 0) {
            var firstProduct = products.get(0);
            
            // Check if it has required or optional badge
            boolean hasBadge = productListPage.hasRequiredBadge(firstProduct) 
                || productListPage.hasOptionalBadge(firstProduct);
            
            Assert.assertTrue(hasBadge, "Product should have either required or optional badge");
        }
    }
}

