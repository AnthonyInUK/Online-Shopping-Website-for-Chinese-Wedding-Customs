package com.luv2code.ecommerce;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Note: This test is disabled by default because it requires full application context
 * including Okta configuration. 
 * 
 * For E2E testing, use the E2E test classes in the e2e.tests package instead,
 * which test the application through the WebDriver without requiring Spring context.
 */
@SpringBootTest
@Disabled("Disabled - requires Okta configuration. Use E2E tests instead.")
class SpringBootEcommerceApplicationTests {

	@Test
	void contextLoads() {
	}

}
