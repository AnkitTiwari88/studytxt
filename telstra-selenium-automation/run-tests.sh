#!/bin/bash

# Telstra iPhone 13 Selection Automation - Test Runner Script

echo "🚀 Telstra iPhone 13 Selection Automation"
echo "======================================="

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven and try again."
    exit 1
fi

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 11+ and try again."
    exit 1
fi

echo "✅ Prerequisites check passed"

# Create logs directory if it doesn't exist
mkdir -p logs

# Function to run tests
run_tests() {
    local browser=${1:-chrome}
    local headless=${2:-false}
    
    echo "🔄 Running tests with browser: $browser, headless: $headless"
    
    if [ "$headless" = "true" ]; then
        mvn clean test -Dbrowser=$browser -Dheadless=true
    else
        mvn clean test -Dbrowser=$browser
    fi
}

# Parse command line arguments
case "${1:-}" in
    "chrome")
        run_tests chrome false
        ;;
    "firefox")
        run_tests firefox false
        ;;
    "headless")
        run_tests chrome true
        ;;
    "chrome-headless")
        run_tests chrome true
        ;;
    "firefox-headless")
        run_tests firefox true
        ;;
    "install")
        echo "📦 Installing dependencies..."
        mvn clean install
        ;;
    "help"|"-h"|"--help")
        echo "Usage: $0 [option]"
        echo ""
        echo "Options:"
        echo "  chrome           Run tests with Chrome browser (default)"
        echo "  firefox          Run tests with Firefox browser"
        echo "  headless         Run tests with Chrome in headless mode"
        echo "  chrome-headless  Run tests with Chrome in headless mode"
        echo "  firefox-headless Run tests with Firefox in headless mode"
        echo "  install          Install project dependencies"
        echo "  help             Show this help message"
        echo ""
        echo "Examples:"
        echo "  $0                    # Run with Chrome (default)"
        echo "  $0 chrome             # Run with Chrome"
        echo "  $0 firefox            # Run with Firefox"
        echo "  $0 headless           # Run with Chrome headless"
        echo "  $0 install            # Install dependencies"
        ;;
    "")
        # Default: run with Chrome
        run_tests chrome false
        ;;
    *)
        echo "❌ Unknown option: $1"
        echo "Use '$0 help' to see available options"
        exit 1
        ;;
esac

echo "✅ Script execution completed"