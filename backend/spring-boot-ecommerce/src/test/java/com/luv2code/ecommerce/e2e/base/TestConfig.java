package com.luv2code.ecommerce.e2e.base;

/**
 * Configuration constants for E2E tests
 */
public class TestConfig {
    
    // Application URLs
    public static final String BASE_URL = "http://localhost:4200";
    public static final String API_URL = "http://localhost:8081/api";
    
    // Timeout configurations (in seconds)
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 20;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    
    // Test data
    public static final String TEST_EMAIL = "test@example.com";
    public static final String TEST_PASSWORD = "test123";
    
    // Stripe test card details
    public static final String TEST_CARD_NUMBER = "4242 4242 4242 4242";
    public static final String TEST_CARD_EXPIRY = "12/25";
    public static final String TEST_CARD_CVC = "123";
    
    // Expected category IDs
    public static final int GROOM_ATTIRE_CATEGORY = 2;
    public static final int BRIDAL_WEAR_CATEGORY = 3;
    
    // Role constants
    public static final String GROOM = "GROOM";
    public static final String BRIDE = "BRIDE";
}

