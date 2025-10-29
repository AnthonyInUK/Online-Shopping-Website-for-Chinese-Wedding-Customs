package com.luv2code.ecommerce.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object for the Product List page
 */
public class ProductListPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By productBoxes = By.className("product-box");
    private By productName = By.cssSelector("h1");
    private By productPrice = By.className("price");
    private By addToCartButton = By.cssSelector(".btn-primary");
    private By requiredBadge = By.className("badge-required");
    private By optionalBadge = By.className("badge-optional");
    private By categoryHeader = By.tagName("h2");
    
    public ProductListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    /**
     * Get list of all products on the page
     */
    public List<WebElement> getProducts() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productBoxes));
        return driver.findElements(productBoxes);
    }
    
    /**
     * Click on a product by name
     */
    public void clickProductByName(String productName) {
        By productLink = By.xpath("//a[.//h1[contains(text(), '" + productName + "')]]");
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(productLink));
        product.click();
    }
    
    /**
     * Add first product to cart
     */
    public void addFirstProductToCart() {
        WebElement firstProduct = getProducts().get(0);
        WebElement addToCartBtn = firstProduct.findElement(addToCartButton);
        addToCartBtn.click();
        
        // Wait for cart to update
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
    
    /**
     * Get product count
     */
    public int getProductCount() {
        return getProducts().size();
    }
    
    /**
     * Check if product has required badge
     */
    public boolean hasRequiredBadge(WebElement product) {
        try {
            product.findElement(requiredBadge);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if product has optional badge
     */
    public boolean hasOptionalBadge(WebElement product) {
        try {
            product.findElement(optionalBadge);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get category header text
     */
    public String getCategoryHeader() {
        WebElement header = wait.until(ExpectedConditions.presenceOfElementLocated(categoryHeader));
        return header.getText();
    }
}

