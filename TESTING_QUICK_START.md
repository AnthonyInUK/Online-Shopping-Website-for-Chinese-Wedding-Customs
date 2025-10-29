# 🧪 Quick Start Guide for E2E Testing

## Prerequisites

- Java 11+
- Node.js 14+
- Chrome/ChromeDriver installed
- MySQL running
- Backend and Frontend applications running

## Running Tests

### Option 1: Backend E2E Tests (Selenium + Java)

```bash
# Navigate to backend
cd backend/spring-boot-ecommerce

# Install dependencies (first time only)
mvn clean install

# Run all E2E tests in headless mode
mvn test -Dbrowser=headless

# Run specific test class
mvn test -Dtest=RoleSelectionTest

# Run with visible browser (for debugging)
mvn test -Dbrowser=chrome
```

### Option 2: Frontend E2E Tests (Protractor)

```bash
# Terminal 1: Start Frontend
cd frontend/angular-ecommerce
npm start

# Terminal 2: Run E2E Tests
cd frontend/angular-ecommerce
npm run e2e

# Run in headless mode
CHROME_BIN=/usr/bin/google-chrome CHROME_ARGS='--headless --disable-gpu' npm run e2e
```

## Test Coverage

### ✅ What's Tested

1. **Role Selection & Navigation**
   - Modal appears on first visit
   - Groom/Bride selection works
   - Auto-navigation to correct category
   - Role change functionality

2. **Product Browsing**
   - Products display after role selection
   - Badges (Required/Optional) show correctly
   - Product filtering by role

3. **Shopping Cart**
   - Add product to cart
   - Update quantity
   - Remove items
   - Price calculations

4. **Checkout Process**
   - Form validation
   - Address entry
   - Stripe payment integration (test cards)
   - Order submission

5. **Order History**
   - Page accessibility
   - Order listing (requires authentication)

## Tips for Running Tests

### Debug Mode
Run with visible browser to see what's happening:
```bash
mvn test -Dbrowser=chrome  # Backend
# or just use npm run e2e (without headless) for frontend
```

### Screenshots
Failed tests automatically capture screenshots in `backend/spring-boot-ecommerce/screenshots/`

### CI/CD
Tests run automatically on GitHub Actions for every PR. Check the Actions tab for results.

## Common Issues

### Port Already in Use
```bash
lsof -ti:4200 | xargs kill -9  # Kill frontend
lsof -ti:8081 | xargs kill -9  # Kill backend
```

### ChromeDriver Version Mismatch
```bash
# Update ChromeDriver
brew upgrade chromedriver  # macOS
```

### Tests Timeout
Increase timeout in `BaseTest.java` or `protractor.conf.js`

## Next Steps

- Read full guide: [SELENIUM_SETUP_GUIDE.md](SELENIUM_SETUP_GUIDE.md)
- View test reports in CI/CD artifacts
- Add more test cases as needed

