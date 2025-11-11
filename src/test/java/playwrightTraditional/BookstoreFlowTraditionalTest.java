package playwrightTraditional;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookstoreFlowTraditionalTest {
  static Playwright playwright;
  static Browser browser;
  static BrowserContext context;
  static Page page;

  @BeforeAll
  static void setup() {
    playwright = Playwright.create();
    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));

    // fresh context each run and record video
    context = browser.newContext(new Browser.NewContextOptions()
            .setRecordVideoDir(Paths.get("videos/"))
            .setRecordVideoSize(1280, 720));
    page = context.newPage();
  }

  @AfterAll
  static void teardown() {
    if (context != null) context.close();
    if (browser != null) browser.close();
    if (playwright != null) playwright.close();
  }

  private void waitForIdleNetwork() {
    page.waitForLoadState(LoadState.NETWORKIDLE);
  }

  @Test
  @Order(1)
  void fullPurchasePathway() {
    page.navigate("https://depaul.bncollege.com/");
    waitForIdleNetwork();

    // kill cookie/popups if present (only click visible ones)
    Locator accept = page.locator(
            "button:has-text('Accept'), " +
                    "button:has-text('I Accept'), " +
                    "button:has-text('Agree'), " +
                    "button:has-text('Got it'), " +
                    "button[aria-label*='Close' i]"
    );
    if (accept.count() > 0) {
      for (int i = 0; i < accept.count(); i++) {
        if (accept.nth(i).isVisible()) {
          accept.nth(i).click();
          page.waitForTimeout(400);
          break;
        }
      }
    }

    // 1. Search earbuds  (robust locator)
    Locator search = page.locator(
            "input[aria-label*='search' i], " +
                    "input[placeholder*='search' i], " +
                    "form[role='search'] input, " +
                    "input[type='search'], " +
                    "input[name*='search' i]"
    );

    // open hidden search icon if needed
    if (search.count() == 0 || !search.first().isVisible()) {
      Locator searchToggle = page.locator(
              "button[aria-label*='search' i], " +
                      "button:has-text('Search'), " +
                      "[data-testid*='search' i], " +
                      "a[aria-label*='search' i]"
      );
      if (searchToggle.count() > 0) {
        searchToggle.first().click();
        page.waitForTimeout(400);
        search = page.locator(
                "input[aria-label*='search' i], input[placeholder*='search' i], form[role='search'] input, input[type='search'], input[name*='search' i]"
        );
      }
    }

    assertTrue(search.count() > 0, "Search input should exist");
    search.first().fill("earbuds");
    search.first().press("Enter");
    waitForIdleNetwork();

    // 2. Filters: Brand -> JBL
    Locator brandFilter = page.locator("button:has-text('Brand'), summary:has-text('Brand')");
    if (brandFilter.count() > 0) brandFilter.first().click();
    Locator jbl = page.locator("label:has-text('JBL'), input[type='checkbox'][value*='JBL']");
    if (jbl.count() > 0) jbl.first().click();
    waitForIdleNetwork();

    // 3. Color -> Black
    Locator colorFilter = page.locator("button:has-text('Color'), summary:has-text('Color')");
    if (colorFilter.count() > 0) colorFilter.first().click();
    Locator black = page.locator("label:has-text('Black'), input[type='checkbox'][value*='Black']");
    if (black.count() > 0) black.first().click();
    waitForIdleNetwork();

    // 4. Price -> Over $50
    Locator priceFilter = page.locator("button:has-text('Price'), summary:has-text('Price')");
    if (priceFilter.count() > 0) priceFilter.first().click();
    Locator over50 = page.locator("label:has-text('Over $50'), input[type='checkbox'][value*='50']");
    if (over50.count() > 0) over50.first().click();
    waitForIdleNetwork();

    // 5. Click first visible product result (any brand or type)
    Locator productLink = page.locator(
            "a[href*='/product/']:visible, " +
                    "a[href*='/shop/']:visible, " +
                    "[data-testid*='product']:has(a), " +
                    "div:has(a[href*='product']):visible"
    );

    // fallback for new layouts (cards with clickable divs)
    if (productLink.count() == 0) {
      productLink = page.locator("div:has-text('Earbud'), div:has-text('Wireless'), div:has-text('Headphone')").first();
    }

    assertTrue(productLink.count() > 0, "Expected at least one visible product link or card on results page");
    productLink.first().click();
    waitForIdleNetwork();


  }
}
