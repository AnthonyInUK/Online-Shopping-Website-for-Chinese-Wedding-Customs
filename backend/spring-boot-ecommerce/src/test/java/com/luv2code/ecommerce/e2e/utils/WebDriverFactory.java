package com.luv2code.ecommerce.e2e.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * Factory class for creating WebDriver instances
 */
public class WebDriverFactory {
    
    private static WebDriver driver;
    
    /**
     * Get or create a WebDriver instance
     * @param browserName Browser name (chrome, firefox, headless)
     * @return WebDriver instance
     */
    public static WebDriver getDriver(String browserName) {
        if (driver == null) {
            driver = createDriver(browserName);
        }
        return driver;
    }
    
    /**
     * Create a new WebDriver instance based on browser name
     */
    private static WebDriver createDriver(String browserName) {
        WebDriver webDriver;
        
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
                
            case "headless":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--window-size=1920,1080");
                webDriver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
                
            default:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
        }
        
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        
        return webDriver;
    }
    
    /**
     * Quit the WebDriver instance
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    /**
     * Close the current window
     */
    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}

