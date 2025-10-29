package com.luv2code.ecommerce.e2e.tests;

import com.luv2code.ecommerce.e2e.base.BaseTest;
import com.luv2code.ecommerce.e2e.base.TestConfig;
import com.luv2code.ecommerce.e2e.pages.HomePage;
import com.luv2code.ecommerce.e2e.pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * E2E tests for Role Selection functionality
 */
public class RoleSelectionTest extends BaseTest {
    
    @Test(priority = 1, description = "Test role selector modal appears on first visit")
    public void testRoleSelectorAppearsOnFirstVisit() {
        HomePage homePage = new HomePage(driver);
        
        // Verify role selector modal is visible
        boolean isVisible = homePage.isRoleSelectorVisible();
        Assert.assertTrue(isVisible, "Role selector modal should be visible on first visit");
    }
    
    @Test(priority = 2, description = "Test selecting Groom role navigates to correct category")
    public void testSelectGroomRole() {
        HomePage homePage = new HomePage(driver);
        
        // Wait for role selector and select Groom
        homePage.selectGroomRole();
        
        // Wait for page to load
        try {
            Thread.sleep(3000); // Wait for navigation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify current role is displayed
        boolean isRoleDisplayed = homePage.isCurrentRoleDisplayed();
        Assert.assertTrue(isRoleDisplayed, "Current role badge should be displayed after selection");
        
        // Verify role text contains Groom
        String roleText = homePage.getCurrentRoleText();
        Assert.assertTrue(roleText.contains("Groom"), "Role text should contain 'Groom'");
    }
    
    @Test(priority = 3, description = "Test selecting Bride role navigates to correct category")
    public void testSelectBrideRole() {
        HomePage homePage = new HomePage(driver);
        
        // Select Bride role
        homePage.selectBrideRole();
        
        // Wait for navigation
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify current role is displayed
        boolean isRoleDisplayed = homePage.isCurrentRoleDisplayed();
        Assert.assertTrue(isRoleDisplayed, "Current role badge should be displayed");
        
        // Verify role text contains Bride
        String roleText = homePage.getCurrentRoleText();
        Assert.assertTrue(roleText.contains("Bride"), "Role text should contain 'Bride'");
    }
    
    @Test(priority = 4, description = "Test changing role mid-session")
    public void testChangeRole() {
        HomePage homePage = new HomePage(driver);
        
        // First select Groom
        homePage.selectGroomRole();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Change role
        homePage.changeRole();
        
        // Verify role selector appears again
        boolean isVisible = homePage.isRoleSelectorVisible();
        Assert.assertTrue(isVisible, "Role selector should appear when changing role");
        
        // Select Bride
        homePage.selectBrideRole();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify role changed
        String roleText = homePage.getCurrentRoleText();
        Assert.assertTrue(roleText.contains("Bride"), "Role should be changed to Bride");
    }
}

