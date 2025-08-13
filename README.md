# ITI-Automation-Web-Project

# 🧪 OrangeHRM UI Automation Framework

This project is a **Selenium-based UI test automation framework** for testing [OrangeHRM Demo](https://opensource-demo.orangehrmlive.com/). It uses Java 21, TestNG, Maven, and Extent Reports with the Page Object Model (POM) design pattern.

---

## 🚀 Tech Stack

- **Language**: Java 21
- **Automation**: Selenium WebDriver
- **Test Framework**: TestNG
- **Build Tool**: Maven
- **Reporting**: ExtentReports

---

## ⚙️ Setup Instructions

### 🔹 Prerequisites

- ✅ Java **21** installed and configured (`java -version`)
- ✅ Maven installed (`mvn -version`)
- ✅ IDE like IntelliJ or Eclipse (recommended)
- ✅ Chrome browser (default browser)

---

## ▶️ How to Run the Tests


### 1️⃣ Option A: Run from Terminal (Recommended)

Navigate to the root directory of the project and execute the following command to run the test suite:

```bash
mvn clean verify -Dbrowser=<browser_name>
```

Replace `<browser_name>` with one of the supported browser values:  
`chrome`, `firefox`, `edge`, or `safari`.

> Example:
```bash
mvn clean verify -Dbrowser=firefox
```

If no browser is specified, the tests will default to running on **Chrome**:

```bash
mvn clean verify
```

> Runs all tests and generates Extent Report.

---

### 2️⃣ Option B: Run `testng.xml` (e.g., ESpace.xml)

Right-click the file in your IDE and choose:  
**Run as → TestNG Suite**

---

### 3️⃣ Option C: Run a Specific Class

Right-click on the class `EndToEndScenarios` in your test/regression package and select:  
**Run 'EndToEndScenarios'**

---

## 📊 Reports

After the test run, navigate to:

```
E-Space\reports
```

Open the `.html` file in any browser to see a detailed test report .
also you will find a folder for passed and failed screenshots

---

## 🧩 Design Pattern

The framework uses the **Page Object Model (POM)**:

- Each page has its own Java class.
- Clean separation of logic and test steps.
- Reusability and maintainability.
