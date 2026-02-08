# Salesforce Login Automation Framework

Enterprise-level Selenium automation framework for testing Salesforce login functionality.

## Tech Stack

- **Java 17**
- **Selenium 4.18.1**
- **TestNG 7.9.0**
- **Maven**
- **WebDriverManager 5.7.0**

## Project Structure

```
SeleniumFramework/
├── pom.xml
├── testng.xml
└── src/
    ├── main/java/com/salesforce/pages/
    │   └── LoginPage.java
    └── test/java/com/salesforce/
        ├── base/
        │   └── BaseTest.java
        └── tests/
            ├── ValidLoginTest.java
            └── InvalidLoginTest.java
```

## Features

- **Page Object Model** with PageFactory
- **XPath-only** locators
- **WebDriverWait** for element synchronization
- **TestNG** annotations for test lifecycle management
- **21 test cases** covering valid and invalid login scenarios

## Test Cases

### Valid Login Tests (9 tests)
- Login page display verification
- Username/Password field visibility
- Login button and Remember Me checkbox verification
- Page title and URL validation
- Valid credential login scenarios

### Invalid Login Tests (12 tests)
- Empty credentials
- Empty username/password
- Invalid username/password combinations
- Special characters and SQL injection attempts
- Very long input validation
- Error message verification

## Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn -Dtest=ValidLoginTest test
mvn -Dtest=InvalidLoginTest test

# Clean and test
mvn clean test
```

## Prerequisites

- Java 17+
- Maven 3.6+
- Chrome browser installed

## Configuration

Update credentials in test files for valid login tests:
- `ValidLoginTest.java` - Lines with `validUsername` and `validPassword`
