package playwrightvisual;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomePageVisualTest {
    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));

        // create new context for each run, record video
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
    void compareHomePageScreenshot() throws java.io.IOException {  // ← add this “throws” part
        page.navigate("https://depaul.bncollege.com/");
        waitForIdleNetwork();

        // ignore cookie banner if it exists
        Locator accept = page.locator("button:has-text('Accept'), button:has-text('Got it')");
        if (accept.count() > 0 && accept.first().isVisible()) {
            accept.first().click();
            page.waitForTimeout(400);
        }

        // capture current homepage
        Path newScreenshot = Paths.get("screenshots/homepage-current.png");
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(newScreenshot)
                .setFullPage(true));

        // if baseline image doesn't exist, treat current as baseline
        Path baseline = Paths.get("screenshots/homepage-baseline.png");
        if (!baseline.toFile().exists()) {
            boolean renamed = newScreenshot.toFile().renameTo(baseline.toFile());
            if (!renamed) {
                System.err.println("Could not rename screenshot to baseline, check file permissions.");
            } else {
                System.out.println("Baseline image created. Future runs will compare against it.");
            }
            return;
        }

        // compare pixels
        byte[] current = java.nio.file.Files.readAllBytes(newScreenshot);
        byte[] base = java.nio.file.Files.readAllBytes(baseline);
        assertArrayEquals(base, current, "Homepage layout changed from baseline!");
    }

}
