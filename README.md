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

**Recordings:**
- Tests are configured to record to the `videos/` directory.
- Resolution: **1280×720**

**Reflection:**
The manual UI testing approach using Playwright and Java required more time and attention to detail.
Writing selectors, managing timing issues, and maintaining assertions manually gave full control over each step
but was tedious. It was also more reliable once stabilized, since every action and expectation was explicitly coded.

The AI-assisted UI testing with Playwright MCP was faster to generate and easier to start with.
The agent produced runnable test code that covered most steps correctly, but the generated selectors sometimes broke
or missed dynamic elements, which required manual fixes.

In terms of maintenance, the manual tests are easier to debug and update when the website changes,
while the AI-generated tests may need regeneration from scratch.
Overall, the manual method proved more dependable, while the AI-assisted one showed strong potential
for speeding up test creation but still needs refinement for complex or dynamic UIs.