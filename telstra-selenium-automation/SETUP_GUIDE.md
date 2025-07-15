# 🚀 Telstra iPhone 13 Automation - Complete Setup Guide

## 📁 Project Created Successfully!

Your Telstra iPhone 13 selection automation project has been created with the following structure:

```
telstra-selenium-automation/
├── 📄 pom.xml                                    # Maven configuration with dependencies
├── 📄 README.md                                  # Comprehensive project documentation
├── 📄 SETUP_GUIDE.md                            # This setup guide
├── 🔧 run-tests.sh                              # Test runner script (executable)
├── src/
│   ├── main/java/com/telstra/automation/
│   │   ├── base/
│   │   │   └── 📋 BaseTest.java                 # Base test class with WebDriver setup
│   │   └── pages/
│   │       └── 📋 TelstraMobilePhonesPage.java  # Page Object Model for Telstra page
│   └── test/
│       ├── java/com/telstra/automation/
│       │   └── 📋 TelstraIPhone13SelectionTest.java  # Main automation test
│       └── resources/
│           ├── ⚙️ testng.xml                    # TestNG test configuration
│           └── ⚙️ log4j2.xml                   # Logging configuration
└── logs/                                        # (Will be created automatically)
```

## 🛠️ Installation Requirements

Before running the automation, you need to install:

### 1. Java Development Kit (JDK)
```bash
# Ubuntu/Debian
sudo apt-get update
sudo apt-get install openjdk-11-jdk

# CentOS/RHEL/Fedora
sudo yum install java-11-openjdk-devel
# or for newer versions
sudo dnf install java-11-openjdk-devel

# macOS (using Homebrew)
brew install openjdk@11

# Windows
# Download from: https://adoptium.net/
```

### 2. Apache Maven
```bash
# Ubuntu/Debian
sudo apt-get install maven

# CentOS/RHEL/Fedora
sudo yum install maven
# or for newer versions
sudo dnf install maven

# macOS (using Homebrew)
brew install maven

# Windows
# Download from: https://maven.apache.org/download.cgi
# Add to PATH environment variable
```

### 3. Google Chrome Browser
- Download and install the latest version of Google Chrome
- The automation will automatically download the compatible ChromeDriver

## 🔧 Quick Start

### Step 1: Verify Installation
```bash
# Check Java version (should be 11 or higher)
java -version

# Check Maven version
mvn -version
```

### Step 2: Navigate to Project Directory
```bash
cd telstra-selenium-automation
```

### Step 3: Install Dependencies
```bash
mvn clean install
```

### Step 4: Run the Automation
```bash
# Option 1: Use the runner script (recommended)
./run-tests.sh

# Option 2: Use Maven directly
mvn test

# Option 3: Run in headless mode
./run-tests.sh headless
```

## 🎯 What the Automation Does

The automation script will:

1. **🌐 Launch Browser**: Opens Chrome browser
2. **📱 Navigate to Telstra**: Goes to https://www.telstra.com.au/mobile-phones/mobiles-on-a-plan
3. **🔍 Search for iPhone 13**: Uses multiple strategies to find iPhone 13
4. **🎯 Select iPhone 13**: Clicks on iPhone 13 if found
5. **✅ Verify Success**: Confirms the selection was successful

## 🔄 Running Different Test Scenarios

```bash
# Run with Chrome (default)
./run-tests.sh chrome

# Run with Firefox
./run-tests.sh firefox

# Run in headless mode (no browser window)
./run-tests.sh headless

# Run with Chrome in headless mode
./run-tests.sh chrome-headless

# Run with Firefox in headless mode
./run-tests.sh firefox-headless

# Just install dependencies
./run-tests.sh install

# Show help
./run-tests.sh help
```

## 📊 Understanding Test Results

### ✅ Success Indicators
- Console shows: "✓ iPhone 13 selection test PASSED"
- Test completes without errors
- Log shows successful navigation and selection

### ❌ Failure Scenarios
- "iPhone 13 not found" - The phone might not be available or page structure changed
- WebDriver errors - Browser or driver issues
- Timeout errors - Network or page loading issues

### 📋 Logs and Reports
- **Console Output**: Real-time execution logs
- **Log File**: `logs/automation.log` (detailed logs)
- **TestNG Reports**: `target/surefire-reports/` (HTML reports)

## 🔧 Troubleshooting Common Issues

### Issue: Maven not found
```bash
# Install Maven first
sudo apt-get install maven  # Ubuntu/Debian
sudo yum install maven       # CentOS/RHEL
```

### Issue: Java not found
```bash
# Install Java 11+
sudo apt-get install openjdk-11-jdk  # Ubuntu/Debian
```

### Issue: Chrome not found
```bash
# Install Google Chrome
wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
sudo sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list'
sudo apt-get update
sudo apt-get install google-chrome-stable
```

### Issue: Permission denied for run-tests.sh
```bash
chmod +x run-tests.sh
```

### Issue: iPhone 13 not found
This is normal if:
- iPhone 13 is out of stock
- Page structure has changed
- Regional restrictions apply

Check the logs for more details and available phone models.

## 🎨 Customizing the Automation

### Modify Search Target
Edit `TelstraMobilePhonesPage.java` to search for different phones:
```java
// Change "iPhone 13" to your desired phone
searchElement.sendKeys("iPhone 14");
```

### Add More Test Cases
Add new methods to `TelstraIPhone13SelectionTest.java`:
```java
@Test
public void testSelectiPhone14() {
    // Your test logic here
}
```

### Change Browser Settings
Modify `BaseTest.java` to add browser options:
```java
chromeOptions.addArguments("--window-size=1920,1080");
chromeOptions.addArguments("--disable-notifications");
```

## 📱 Project Features

- ✅ **Page Object Model**: Clean, maintainable code structure
- ✅ **Multiple Selection Strategies**: Robust element finding
- ✅ **Cross-browser Support**: Chrome and Firefox
- ✅ **Headless Execution**: CI/CD friendly
- ✅ **Comprehensive Logging**: Detailed execution tracking
- ✅ **TestNG Integration**: Professional test framework
- ✅ **Maven Build System**: Standard Java project
- ✅ **Automatic Driver Management**: No manual driver downloads

## 🚀 Next Steps

1. **Run the basic test** to ensure everything works
2. **Review the logs** to understand the execution flow
3. **Customize the automation** for your specific needs
4. **Add more test scenarios** as required
5. **Integrate with CI/CD** using headless mode

## 📞 Support

If you encounter issues:
1. Check this setup guide first
2. Review the main README.md for detailed documentation
3. Examine the log files for error details
4. Verify all prerequisites are installed correctly

**Happy Testing! 🎉**