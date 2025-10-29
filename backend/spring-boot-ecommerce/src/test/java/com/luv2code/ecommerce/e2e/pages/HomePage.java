package com.luv2code.ecommerce.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object for the Home page
 */
public class HomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By roleSelectorModal = By.className("role-selector-modal");
    private By groomButton = By.id("groom-btn");
    private By brideButton = By.id("bride-btn");
    private By searchBox = By.id("search-box");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By cartStatus = By.className("cart-status");
    private By loginStatus = By.className("login-status");
    private By productCategoryMenu = By.className("product-category-menu");
    private By currentRoleBadge = By.className("current-role");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    /**
     * Check if role selector modal is displayed
     */
    public boolean isRoleSelectorVisible() {
        try {
            WebElement modal = wait.until(ExpectedConditions.presenceOfElementLocated(roleSelectorModal));
            return modal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Select Groom role
     */
    public void selectGroomRole() {
        wait.until(ExpectedConditions.elementToBeClickable(groomButton));
        driver.findElement(groomButton).click();
        
        // Wait for navigation to complete
        wait.until(ExpectedConditions.invisibilityOfElementLocated(roleSelectorModal));
    }
    
    /**
     * Select Bride role
     */
    public void selectBrideRole() {
        wait.until(ExpectedConditions.elementToBeClickable(brideButton));
        driver.findElement(brideButton).click();
        
        // Wait for navigation to complete
        wait.until(ExpectedConditions.invisibilityOfElementLocated(roleSelectorModal));
    }
    
    /**
     * Check if current role badge is displayed
     */
    public boolean isCurrentRoleDisplayed() {
        try {
            WebElement roleBadge = wait.until(ExpectedConditions.presenceOfElementLocated(currentRoleBadge));
            return roleBadge.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get the text of current role badge
     */
    public String getCurrentRoleText() {
        WebElement roleBadge = wait.until(ExpectedConditions.presenceOfElementLocated(currentRoleBadge));
        return roleBadge.findElement(By.className("role-text")).getText();
    }
    
    /**
     * Change role
     */
    public void changeRole() {
        WebElement changeRoleBtn = driver.findElement(By.className("change-role-btn"));
        changeRoleBtn.click();
        
        // Wait for role selector modal to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(roleSelectorModal));
    }
    
    /**
     * Perform search
     */
    public void searchProducts(String searchTerm) {
        WebElement searchInput = driver.findElement(searchBox);
        searchInput.clear();
        searchInput.sendKeys(searchTerm);
        
        WebElement searchBtn = driver.findElement(searchButton);
        searchBtn.click();
    }
    
    /**
     * Click on a category in the sidebar
     */
    public void clickCategory(String categoryName) {
        By categoryLink = By.xpath("//a[contains(text(), '" + categoryName + "')]");
        WebElement category = wait.until(ExpectedConditions.elementToBeClickable(categoryLink));
        category.click();
    }
}

