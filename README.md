# SE333 – Assignment 6: UI Testing (Playwright + Java)

## Project Structure
- `src/test/java/playwrightTraditional` – Manual Playwright test written in Java
- `src/test/java/playwrightLLM` – AI-assisted test generated via Playwright MCP

## GitHub Repository
[https://github.com/aylaganama/Assignment6](https://github.com/aylaganama/Assignment6)

## Running the Tests
- To execute all tests:
  ```bash
  mvn test

**GitHub Repository:** [https://github.com/aylaganama/Assignment6](https://github.com/aylaganama/Assignment6)

**Recordings:**
- Tests are configured to record to the `videos/` directory.
- Resolution: **1280×720**

**Summary:**
- **Traditional flow:** Validated product search, navigation, and purchase flow.
- **LLM flow:** Implemented the same logic using AI-assisted code generation.
- **Visual flow:** Created baseline screenshots and confirmed visual consistency between runs.

**Reflection:**
- **Manual UI testing:** Effort, reliability, flakiness handling, selector strategy, and maintenance.
- **AI-assisted testing:** Speed of authoring, accuracy of generated selectors, fixes required, and limitations.
- **Visual testing:** Benefits for layout regression and design consistency.
- **Failures:** What failed most often and why (e.g., timing or dynamic content).
- **Final verdict:** When to prefer manual vs AI-assisted vs visual regression testing.
