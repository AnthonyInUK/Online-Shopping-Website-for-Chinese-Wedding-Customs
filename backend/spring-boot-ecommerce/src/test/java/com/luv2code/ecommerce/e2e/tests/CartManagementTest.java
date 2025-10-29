package com.luv2code.ecommerce.e2e.tests;

import com.luv2code.ecommerce.e2e.base.BaseTest;
import com.luv2code.ecommerce.e2e.pages.CartPage;
import com.luv2code.ecommerce.e2e.pages.HomePage;
import com.luv2code.ecommerce.e2e.pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * E2E tests for Shopping Cart Management
 */
public class CartManagementTest extends BaseTest {
    
    @Test(priority = 1, description = "Test adding product to cart")
    public void testAddProductToCart() {
        HomePage homePage = new HomePage(driver);
        ProductListPage productListPage = new ProductListPage(driver);
        CartPage cartPage = new CartPage(driver);
        
        // Select role and navigate to products
        homePage.selectGroomRole();
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Add first product to cart
        productListPage.addFirstProductToCart();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Navigate to cart
        driver.navigate().to("http://localhost:4200/cart-details");
        
        // Verify cart has items
        int itemCount = cartPage.getCartItemCount();
        Assert.assertTrue(itemCount > 0, "Cart should have at least one item");
    }
    
    @Test(priority = 2, description = "Test updating quantity in cart")
    public void testUpdateQuantity() {
        HomePage homePage = new HomePage(driver);
        ProductListPage productListPage = new ProductListPage(driver);
        CartPage cartPage = new CartPage(driver);
        
        // Add product to cart
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
        
        // Update quantity
        cartPage.updateQuantity(3);
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify cart still has items
        int itemCount = cartPage.getCartItemCount();
        Assert.assertTrue(itemCount > 0, "Cart should still have items after quantity update");
    }
    
    @Test(priority = 3, description = "Test removing item from cart")
    public void testRemoveItemFromCart() {
        HomePage homePage = new HomePage(driver);
        ProductListPage productListPage = new ProductListPage(driver);
        CartPage cartPage = new CartPage(driver);
        
        // Add product to cart
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
        
        // Remove item
        cartPage.removeFirstItem();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify cart is empty or has decreased count
        boolean isEmpty = cartPage.isEmptyCartMessageDisplayed();
        Assert.assertTrue(isEmpty || cartPage.getCartItemCount() == 0, 
            "Cart should be empty after removing item");
    }
}

