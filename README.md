# SE333 Assignment 6 – UI Testing (Playwright + Java)

This repository contains three approaches to automated UI testing for the DePaul Bookstore flow using **Playwright** and **Java**.

## Project Structure
- `src/test/java/playwrightTraditional` – Manual Playwright test written by hand (`BookstoreFlowTraditionalTest`)
- `src/test/java/playwrightLLM` – AI-assisted test generated via Playwright MCP (`BookstoreFlowLLMTest`)
- `src/test/java/playwrightvisual` – Visual regression test comparing screenshots (`HomePageVisualTest`)

## Requirements
- Java 17+
- Maven 3.8.5+

## Install Browsers
Maven will auto-install Playwright browsers on first run, or you can run manually:
```bash
mvn -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install --with-deps" exec:java


## Record videos
Tests are configured to record to `videos/` at 1280×720.

## Run
```bash
mvn -q test
```
**Recordings:**
- Tests are configured to record to the `videos/` directory.
- Resolution: **1280×720**

**Run Tests:**
- To execute all tests:
  ```bash
  mvn -q test
  ```
- To run individual tests in IntelliJ:
    - **BookstoreFlowTraditionalTest** → runs the manual test
    - **BookstoreFlowLLMTest** → runs the AI-assisted test
    - **HomePageVisualTest** → runs visual regression and creates or compares the baseline image

**GitHub Actions:**
- Workflow file: `.github/workflows/maven.yml`
- Automatically runs all tests on each push.

**Summary:**
- **Traditional flow:** Validated product search, navigation, and purchase flow.
- **LLM flow:** Implemented the same logic using AI-assisted code generation.
- **Visual flow:** Created baseline screenshots and confirmed visual consistency between runs.

**Reflection (include in written submission):**
- **Manual UI testing:** Effort, reliability, flakiness handling, selector strategy, and maintenance.
- **AI-assisted testing:** Speed of authoring, accuracy of generated selectors, fixes required, and limitations.
- **Visual testing:** Benefits for layout regression and design consistency.
- **Failures:** What failed most often and why (e.g., timing or dynamic content).
- **Final verdict:** When to prefer manual vs AI-assisted vs visual regression testing.

**Assignment brief:** SE333 (6%) – Assignment 6 – UI Testing (Playwright + Java)
