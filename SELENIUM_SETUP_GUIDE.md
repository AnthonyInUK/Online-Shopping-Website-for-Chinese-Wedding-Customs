# 🧪 Selenium E2E Testing Setup Guide

This guide provides complete instructions for setting up and running Selenium-based End-to-End (E2E) tests for the Chinese Wedding Customs e-commerce platform.

## 📋 Table of Contents

1. [Overview](#overview)
2. [Testing Strategy](#testing-strategy)
3. [Key E2E Testing Scenarios](#key-e2e-testing-scenarios)
4. [Setup Instructions](#setup-instructions)
5. [Running Tests](#running-tests)
6. [CI/CD Integration](#cicd-integration)
7. [Best Practices](#best-practices)

---

## 🎯 Overview

This project implements Selenium WebDriver for automated E2E testing with the following architecture:

- **Frontend Tests**: Angular + Protractor (TypeScript)
- **Backend Tests**: Spring Boot + Selenium WebDriver (Java)
- **Test Runner**: Maven/Gradle for backend, npm scripts for frontend
- **CI/CD**: GitHub Actions for automated test execution

---

## 📊 Testing Strategy

### Why E2E Testing?

E2E tests validate the complete user journey from frontend to backend, ensuring:
- ✅ User interactions work correctly
- ✅ API integrations function properly
- ✅ Role-based features behave as expected
- ✅ Payment flows process successfully
- ✅ Database operations complete correctly

### Testing Pyramid

```
                    /\
                   /  \
                  / E2E\     ← High value, high cost
                 /------\
                /  API   \   ← Medium value, medium cost
               /----------\
              /   UNIT     \ ← Low value, low cost
             /--------------\
```

**Focus Areas for E2E Tests:**
1. Critical user journeys
2. Payment processing
3. Role-based product filtering
4. Authentication flows
5. Order management

---

## 🎪 Key E2E Testing Scenarios

### 1. **Role Selection & Product Filtering** 🔑
**Priority**: CRITICAL

**User Journey**:
1. User visits homepage
2. Modal appears asking for role (Groom/Bride)
3. User selects "Groom"
4. System navigates to Groom Attire category
5. Products are filtered to show only Groom/Common items
6. Category menu shows only relevant categories

**Test Cases**:
- ✅ Role selector modal appears on first visit
- ✅ Selecting Groom navigates to category 2
- ✅ Selecting Bride navigates to category 3
- ✅ Products are correctly filtered by targetUser
- ✅ Only relevant categories are displayed in sidebar
- ✅ Role can be changed mid-session

---

### 2. **Product Browsing & Search** 🔍
**Priority**: HIGH

**User Journey**:
1. User browses products in a category
2. User clicks on a product to view details
3. User uses search function to find products
4. User navigates between categories

**Test Cases**:
- ✅ Products display with correct images
- ✅ Product badges (Required/Optional) show correctly
- ✅ Target user badges (Groom/Bride/Common) display properly
- ✅ Product details page shows all information
- ✅ Search returns relevant results
- ✅ Pagination works correctly

---

### 3. **Shopping Cart Management** 🛒
**Priority**: HIGH

**User Journey**:
1. User adds product to cart
2. Cart count updates in header
3. User views cart details
4. User updates quantity
5. User removes item from cart
6. Total price updates correctly

**Test Cases**:
- ✅ Cart count badge updates when item added
- ✅ Cart page displays all items
- ✅ Quantity can be incremented/decremented
- ✅ Remove item button works
- ✅ Subtotal, tax, total calculations are correct
- ✅ Empty cart shows appropriate message

---

### 4. **Checkout Process** 💳
**Priority**: CRITICAL

**User Journey**:
1. User proceeds to checkout
2. Form validates address details
3. User enters payment information
4. User reviews order summary
5. User submits order
6. Order confirmation appears

**Test Cases**:
- ✅ Form validation works for all fields
- ✅ State dropdown populates based on country
- ✅ Stripe payment form appears correctly
- ✅ Order summary matches cart contents
- ✅ Successful order creates confirmation
- ✅ Order tracking number is generated
- ✅ User is redirected to confirmation page

---

### 5. **Authentication Flow** 🔐
**Priority**: HIGH

**User Journey**:
1. User clicks "Login"
2. User enters credentials
3. User is authenticated
4. User can access protected resources

**Test Cases**:
- ✅ Login modal appears
- ✅ Valid credentials log user in
- ✅ Invalid credentials show error
- ✅ Logout clears session
- ✅ Protected routes redirect to login

---

### 6. **Order History** 📜
**Priority**: MEDIUM

**User Journey**:
1. Logged-in user clicks "Order History"
2. User sees list of past orders
3. User clicks an order to view details

**Test Cases**:
- ✅ Order history page loads
- ✅ All orders are displayed
- ✅ Order details are accurate
- ✅ Empty order history shows message

---

### 7. **Role-Based Category Filtering** 🎭
**Priority**: CRITICAL

**User Journey**:
1. Groom selects role
2. Sidebar shows only Groom categories
3. Bride selects role
4. Sidebar updates to show Bride categories

**Test Cases**:
- ✅ Groom sees: Attire (2), Jewelry (4), Decorations (6)
- ✅ Bride sees: Bridal Wear (3), Shoes (5), Candy (7), Red Envelopes (8), Supplies (9)
- ✅ Category list updates when role changes
- ✅ Clicking category shows correct products

---

## 🛠️ Setup Instructions

### Prerequisites

```bash
# Install Java 11+
java -version

# Install Node.js 14+
node -v

# Install Chrome/ChromeDriver
# macOS
brew install chromedriver

# Linux
sudo apt-get install chromium-chromedriver

# Windows
# Download from https://chromedriver.chromium.org/
```

---

### Backend Selenium Setup (Java)

#### 1. Update `pom.xml`

Add Selenium dependencies:

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.15.0</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-chrome-driver</artifactId>
    <version>4.15.0</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.6.2</version>
    <scope>test</scope>
</dependency>

<!-- TestNG for test execution -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.8.0</version>
    <scope>test</scope>
</dependency>
```

#### 2. Project Structure

```
src/test/java/com/luv2code/ecommerce/
├── e2e/
│   ├── base/
│   │   ├── BaseTest.java          # Base test class with WebDriver setup
│   │   └── TestConfig.java        # Configuration constants
│   ├── pages/
│   │   ├── HomePage.java          # Page Object for homepage
│   │   ├── ProductListPage.java   # Page Object for product list
│   │   ├── CartPage.java          # Page Object for shopping cart
│   │   └── CheckoutPage.java      # Page Object for checkout
│   ├── tests/
│   │   ├── RoleSelectionTest.java
│   │   ├── ProductBrowsingTest.java
│   │   ├── CartManagementTest.java
│   │   ├── CheckoutTest.java
│   │   └── OrderHistoryTest.java
│   └── utils/
│       ├── WebDriverFactory.java
│       └── DatabaseUtils.java
```

---

### Frontend Selenium Setup (Protractor)

Angular already includes Protractor configuration. Update `package.json`:

```json
{
  "scripts": {
    "e2e": "protractor e2e/protractor.conf.js",
    "e2e:headless": "CHROME_BIN=/usr/bin/google-chrome CHROME_ARGS='--headless --disable-gpu --no-sandbox' npm run e2e"
  }
}
```

Update `protractor.conf.js` for better reliability:

```javascript
exports.config = {
  allScriptsTimeout: 11000,
  specs: ['./src/**/*.e2e-spec.ts'],
  capabilities: {
    browserName: 'chrome',
    chromeOptions: {
      args: ['--no-sandbox', '--disable-dev-shm-usage']
    }
  },
  directConnect: true,
  baseUrl: 'http://localhost:4200/',
  framework: 'jasmine',
  jasmineNodeOpts: {
    showColors: true,
    defaultTimeoutInterval: 60000, // Increased for slower CI environments
    print: function() {}
  },
  SELENIUM_PROMISE_MANAGER: false,
  onPrepare() {
    require('ts-node').register({
      project: require('path').join(__dirname, './tsconfig.json')
    });
    jasmine.getEnv().addReporter(new SpecReporter({ spec: { displayStacktrace: true } }));
  }
};
```

---

## 🚀 Running Tests

### Backend Tests (Java)

```bash
# Run all E2E tests
cd backend/spring-boot-ecommerce
mvn test

# Run specific test class
mvn test -Dtest=RoleSelectionTest

# Run tests in headless mode
mvn test -Dbrowser=headless
```

### Frontend Tests (Protractor)

```bash
# Start the application first
cd frontend/angular-ecommerce
npm start

# In another terminal, run tests
npm run e2e

# Run in headless mode
npm run e2e:headless
```

---

## 🔄 CI/CD Integration

### GitHub Actions Workflow

Create `.github/workflows/e2e-tests.yml`:

```yaml
name: E2E Tests

on:
  pull_request:
    branches: [ main, develop ]
  push:
    branches: [ main, develop ]

jobs:
  e2e-backend:
    runs-on: ubuntu-latest
    
    services:
      mysql:
        image: mysql:8.0
        env:
          MYSQL_ROOT_PASSWORD: root
          MYSQL_DATABASE: full_stack_ecommerce
        ports:
          - 3306:3306
        options: >-
          --health-cmd="mysqladmin ping"
          --health-interval=10s
          --health-timeout=5s
          --health-retries=3
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 11
      uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'
    
    - name: Start Backend
      run: |
        cd backend/spring-boot-ecommerce
        nohup mvn spring-boot:run > backend.log 2>&1 &
        sleep 30
        tail -n 50 backend.log
    
    - name: Run E2E Tests
      run: |
        cd backend/spring-boot-ecommerce
        mvn test -Dtest=*E2ETest* -Dbrowser=headless
    
    - name: Upload Test Reports
      if: always()
      uses: actions/upload-artifact@v3
      with:
        name: e2e-backend-reports
        path: backend/spring-boot-ecommerce/target/surefire-reports/

  e2e-frontend:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up Node.js
      uses: actions/setup-node@v3
      with:
        node-version: '14'
    
    - name: Install dependencies
      run: |
        cd frontend/angular-ecommerce
        npm install --legacy-peer-deps
    
    - name: Start MySQL and Backend
      run: |
        # Use Docker Compose for full stack
        docker-compose up -d mysql backend
        sleep 45
    
    - name: Build and Start Frontend
      run: |
        cd frontend/angular-ecommerce
        npm start &
        sleep 30
    
    - name: Install Chrome
      run: |
        sudo apt-get update
        sudo apt-get install -y chromium-browser chromium-chromedriver
    
    - name: Run E2E Tests
      env:
        CHROME_BIN: /usr/bin/chromium-browser
        CHROME_ARGS: --headless --disable-gpu --no-sandbox
        NODE_OPTIONS: --openssl-legacy-provider
      run: |
        cd frontend/angular-ecommerce
        npm run e2e
    
    - name: Upload Test Reports
      if: always()
      uses: actions/upload-artifact@v3
      with:
        name: e2e-frontend-reports
        path: frontend/angular-ecommerce/e2e/reports/
```

---

## 💡 Best Practices

### 1. Page Object Model (POM)
Always use Page Objects to encapsulate page interactions:

```java
public class HomePage {
    private WebDriver driver;
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void selectGroomRole() {
        driver.findElement(By.id("groom-btn")).click();
    }
    
    public boolean isProductListDisplayed() {
        return driver.findElement(By.className("product-list")).isDisplayed();
    }
}
```

### 2. Wait Strategies
Always use explicit waits instead of Thread.sleep():

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.elementToBeClickable(By.id("submit-btn")));
```

### 3. Test Data Management
Use separate test data for E2E tests:

```java
@Test
public void testCheckoutWithTestPayment() {
    // Use Stripe test card
    checkoutPage.enterCardNumber("4242 4242 4242 4242");
    checkoutPage.enterExpiryDate("12/25");
    checkoutPage.enterCVC("123");
}
```

### 4. Screenshot on Failure
Capture screenshots when tests fail:

```java
@AfterMethod
public void tearDown(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        // Save screenshot
    }
}
```

### 5. Parallel Execution
Run tests in parallel for faster execution:

```java
@Test(threadPoolSize = 3, invocationCount = 3)
public void testMultipleUsers() {
    // Test logic
}
```

---

## 📝 Next Steps

1. ✅ Review this guide
2. ⏳ Set up Selenium dependencies
3. ⏳ Create Page Objects
4. ⏳ Write E2E test cases
5. ⏳ Configure CI/CD pipeline
6. ⏳ Run tests locally and in CI
7. ⏳ Monitor test results

---

## 🆘 Troubleshooting

### ChromeDriver Issues

```bash
# Update ChromeDriver
brew upgrade chromedriver  # macOS
sudo apt-get upgrade chromium-chromedriver  # Linux
```

### Port Conflicts

```bash
# Kill process on port 4200
lsof -ti:4200 | xargs kill -9
```

### Database Connection Issues

Ensure MySQL is running before starting backend:

```bash
mysqladmin ping
```

---

## 📚 Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Protractor API](https://www.protractortest.org/#/api)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
- [TestNG Documentation](https://testng.org/doc/documentation-main.html)

