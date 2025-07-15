# Telstra iPhone 13 Selection Automation

This project contains Java Selenium automation scripts to launch the Telstra mobile phones website and select iPhone 13.

## 📋 Project Overview

The automation performs the following actions:
1. Launches the Telstra mobile phones URL: `https://www.telstra.com.au/mobile-phones/mobiles-on-a-plan`
2. Searches for iPhone 13 (if search functionality is available)
3. Applies Apple/iPhone filters (if available)
4. Locates and selects iPhone 13 from the available phones

## 🏗️ Project Structure

```
telstra-selenium-automation/
├── src/
│   ├── main/java/com/telstra/automation/
│   │   ├── base/
│   │   │   └── BaseTest.java                 # Base test class with WebDriver setup
│   │   └── pages/
│   │       └── TelstraMobilePhonesPage.java  # Page Object Model for Telstra page
│   └── test/
│       ├── java/com/telstra/automation/
│       │   └── TelstraIPhone13SelectionTest.java  # Main test class
│       └── resources/
│           ├── testng.xml                    # TestNG configuration
│           └── log4j2.xml                   # Logging configuration
├── pom.xml                                  # Maven dependencies
└── README.md                               # This file
```

## 🛠️ Prerequisites

Before running the automation, ensure you have the following installed:

1. **Java Development Kit (JDK) 11 or higher**
   ```bash
   java -version
   ```

2. **Apache Maven 3.6 or higher**
   ```bash
   mvn -version
   ```

3. **Google Chrome browser** (latest version recommended)

4. **Internet connection** to access the Telstra website

## 🚀 Setup and Installation

1. **Clone or download the project** to your local machine

2. **Navigate to the project directory**
   ```bash
   cd telstra-selenium-automation
   ```

3. **Install dependencies**
   ```bash
   mvn clean install
   ```

4. **Create logs directory** (optional, will be created automatically)
   ```bash
   mkdir logs
   ```

## ▶️ Running the Tests

### Option 1: Run via Maven (Recommended)

```bash
# Run all tests
mvn test

# Run with specific browser (chrome is default)
mvn test -Dbrowser=chrome

# Run in headless mode (for CI/CD)
mvn test -Dheadless=true

# Run with specific TestNG suite
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Option 2: Run via TestNG XML

```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Option 3: Run individual test class

```bash
mvn test -Dtest=TelstraIPhone13SelectionTest
```

## 🔧 Configuration Options

### Browser Configuration
- **Default**: Chrome
- **Supported**: Chrome, Firefox
- **Headless mode**: Add `-Dheadless=true` for headless execution

### Test Configuration
Modify `src/test/resources/testng.xml` to:
- Change browser type
- Add parallel execution
- Configure test parameters

### Logging Configuration
Modify `src/test/resources/log4j2.xml` to:
- Change log levels
- Modify log output format
- Configure file logging

## 📊 Test Execution Flow

1. **Page Navigation**: Opens Telstra mobile phones URL
2. **Page Load Verification**: Waits for page to load completely
3. **Phone Discovery**: Prints available phones for debugging
4. **Search Functionality**: Attempts to search for "iPhone 13"
5. **Filter Application**: Applies Apple/iPhone filters if available
6. **iPhone 13 Selection**: Uses multiple strategies to locate and select iPhone 13:
   - Direct text matching
   - CSS class-based selection
   - XPath traversal
   - Parent element navigation
7. **Verification**: Confirms successful selection

## 🔍 Troubleshooting

### Common Issues and Solutions

1. **iPhone 13 Not Found**
   - The website structure may have changed
   - iPhone 13 might not be available
   - Check the console logs for debugging information

2. **WebDriver Issues**
   - Ensure Chrome browser is installed and up-to-date
   - WebDriverManager automatically downloads the correct ChromeDriver version

3. **Page Load Timeout**
   - Check internet connection
   - Verify the Telstra website is accessible
   - Increase timeout values in `BaseTest.java` if needed

4. **Element Not Clickable**
   - The page might have overlays or pop-ups
   - Elements might be loading dynamically
   - Check for cookie consent or other blocking elements

### Debug Mode
To run tests with additional debugging information:

```bash
mvn test -Dlog4j.logger.com.telstra.automation=DEBUG
```

## 📁 Key Features

- **Page Object Model (POM)**: Clean separation of page elements and test logic
- **WebDriverManager**: Automatic driver management
- **Multiple Selection Strategies**: Robust element location methods
- **Comprehensive Logging**: Detailed execution logs
- **TestNG Integration**: Professional test framework
- **Maven Build System**: Standard Java project structure
- **Cross-browser Support**: Chrome and Firefox support
- **Headless Execution**: CI/CD friendly

## 📝 Test Reports

After test execution:
- **Console Output**: Real-time test execution logs
- **Log File**: Detailed logs saved in `logs/automation.log`
- **TestNG Reports**: Generated in `target/surefire-reports/`
- **Maven Reports**: Available in `target/` directory

## 🔄 Extending the Automation

To add more test scenarios:

1. **Add new test methods** in `TelstraIPhone13SelectionTest.java`
2. **Create additional page objects** for other pages
3. **Modify TestNG XML** to include new test classes
4. **Update Maven configuration** for new dependencies

## 🐛 Known Limitations

- The automation depends on the current structure of the Telstra website
- iPhone 13 availability may vary based on stock and regional restrictions
- Some elements might require additional wait strategies for dynamic content
- Pop-ups, cookie banners, or promotional overlays might interfere with automation

## 📞 Support

For issues or questions:
1. Check the console logs and log files for error details
2. Verify the Telstra website is accessible manually
3. Ensure all prerequisites are installed correctly
4. Review the troubleshooting section above

## 📄 License

This project is for educational and testing purposes only.