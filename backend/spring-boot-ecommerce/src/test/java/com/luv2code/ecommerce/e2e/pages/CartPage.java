package com.luv2code.ecommerce.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object for the Shopping Cart page
 */
public class CartPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By cartItems = By.className("cart-item");
    private By quantityInput = By.cssSelector("input[type='number']");
    private By removeButton = By.cssSelector("button.btn-danger");
    private By subtotal = By.className("subtotal");
    private By tax = By.className("tax");
    private By total = By.className("total");
    private By checkoutButton = By.className("checkout-btn");
    private By emptyCartMessage = By.className("empty-cart");
    
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    /**
     * Get all cart items
     */
    public List<WebElement> getCartItems() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartItems));
        return driver.findElements(cartItems);
    }
    
    /**
     * Get cart item count
     */
    public int getCartItemCount() {
        return getCartItems().size();
    }
    
    /**
     * Update quantity for first item
     */
    public void updateQuantity(int quantity) {
        WebElement quantityField = driver.findElement(quantityInput);
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(quantity));
    }
    
    /**
     * Remove first item from cart
     */
    public void removeFirstItem() {
        WebElement removeBtn = driver.findElement(removeButton);
        removeBtn.click();
        
        // Wait for removal to complete
        wait.until(ExpectedConditions.stalenessOf(removeBtn));
    }
    
    /**
     * Get subtotal value
     */
    public String getSubtotal() {
        WebElement subtotalElement = wait.until(ExpectedConditions.presenceOfElementLocated(subtotal));
        return subtotalElement.getText();
    }
    
    /**
     * Get tax value
     */
    public String getTax() {
        WebElement taxElement = wait.until(ExpectedConditions.presenceOfElementLocated(tax));
        return taxElement.getText();
    }
    
    /**
     * Get total value
     */
    public String getTotal() {
        WebElement totalElement = wait.until(ExpectedConditions.presenceOfElementLocated(total));
        return totalElement.getText();
    }
    
    /**
     * Click checkout button
     */
    public void proceedToCheckout() {
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutBtn.click();
    }
    
    /**
     * Check if empty cart message is displayed
     */
    public boolean isEmptyCartMessageDisplayed() {
        try {
            WebElement emptyMsg = wait.until(ExpectedConditions.presenceOfElementLocated(emptyCartMessage));
            return emptyMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

