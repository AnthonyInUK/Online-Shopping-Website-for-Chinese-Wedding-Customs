package com.luv2code.ecommerce.e2e.base;

import com.luv2code.ecommerce.e2e.utils.WebDriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Base test class that provides common setup and teardown methods
 * 
 * Note: This class is for E2E tests and does NOT require Spring context.
 * Tests use WebDriver to interact with the running application.
 */
public class BaseTest {
    
    protected WebDriver driver;
    private String browserName = "chrome"; // Default browser
    
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Get browser from system property, default to chrome
        browserName = System.getProperty("browser", "chrome");
        
        // Create WebDriver instance
        driver = WebDriverFactory.getDriver(browserName);
        
        // Navigate to base URL
        driver.get(TestConfig.BASE_URL);
    }
    
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        // Take screenshot on test failure
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
        
        // Quit WebDriver
        WebDriverFactory.quitDriver();
    }
    
    /**
     * Capture screenshot on test failure
     * @param testName Name of the test that failed
     */
    private void takeScreenshot(String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            
            // Create screenshots directory if it doesn't exist
            String screenshotDir = "screenshots";
            Path dirPath = Paths.get(screenshotDir);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            
            // Generate filename with timestamp
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = screenshotDir + "/" + testName + "_" + timestamp + ".png";
            
            // Copy screenshot to destination
            File destFile = new File(fileName);
            if (sourceFile.exists()) {
                Files.copy(sourceFile.toPath(), destFile.toPath());
                System.out.println("Screenshot saved: " + fileName);
            }
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}

