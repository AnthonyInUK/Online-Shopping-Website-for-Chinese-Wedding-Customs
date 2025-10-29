package com.luv2code.ecommerce.e2e.pages;

import com.luv2code.ecommerce.e2e.base.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object for the Checkout page
 */
public class CheckoutPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("email");
    private By country = By.id("country");
    private By state = By.id("state");
    private By zipCode = By.id("zipCode");
    private By street = By.id("street");
    private By city = By.id("city");
    
    // Stripe payment form
    private By cardNumberField = By.id("cardNumber");
    private By expiryField = By.id("expiry");
    private By cvvField = By.id("cvc");
    
    // Buttons
    private By submitOrderButton = By.cssSelector("button[type='submit']");
    private By orderSummary = By.className("order-summary");
    
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    /**
     * Fill in billing address
     */
    public void fillBillingAddress(String firstNameValue, String lastNameValue, String emailValue,
                                   String streetValue, String cityValue, String zipCodeValue,
                                   String countryValue, String stateValue) {
        driver.findElement(firstName).sendKeys(firstNameValue);
        driver.findElement(lastName).sendKeys(lastNameValue);
        driver.findElement(email).sendKeys(emailValue);
        driver.findElement(street).sendKeys(streetValue);
        driver.findElement(city).sendKeys(cityValue);
        driver.findElement(zipCode).sendKeys(zipCodeValue);
        
        // Select country
        Select countrySelect = new Select(driver.findElement(country));
        countrySelect.selectByVisibleText(countryValue);
        
        // Wait for state dropdown to populate
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(state, 0));
        
        // Select state
        Select stateSelect = new Select(driver.findElement(state));
        stateSelect.selectByVisibleText(stateValue);
    }
    
    /**
     * Fill in payment details with test card
     */
    public void fillPaymentDetails() {
        fillPaymentDetails(TestConfig.TEST_CARD_NUMBER, TestConfig.TEST_CARD_EXPIRY, TestConfig.TEST_CARD_CVC);
    }
    
    /**
     * Fill in payment details
     */
    public void fillPaymentDetails(String cardNumber, String expiry, String cvv) {
        wait.until(ExpectedConditions.presenceOfElementLocated(cardNumberField));
        driver.findElement(cardNumberField).sendKeys(cardNumber);
        driver.findElement(expiryField).sendKeys(expiry);
        driver.findElement(cvvField).sendKeys(cvv);
    }
    
    /**
     * Submit order
     */
    public void submitOrder() {
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitOrderButton));
        submitBtn.click();
    }
    
    /**
     * Check if order summary is displayed
     */
    public boolean isOrderSummaryDisplayed() {
        try {
            WebElement summary = wait.until(ExpectedConditions.presenceOfElementLocated(orderSummary));
            return summary.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Wait for checkout form to load
     */
    public void waitForCheckoutForm() {
        wait.until(ExpectedConditions.presenceOfElementLocated(firstName));
        wait.until(ExpectedConditions.presenceOfElementLocated(submitOrderButton));
    }
}

