import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    public static void setup() {
        playwright = Playwright.create();
        browser = playwright
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    public void createContext() {
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://www.saucedemo.com");
    }

    @AfterEach
    public void closeContext() {
        context.close();
    }

    @AfterAll
    public static void tearDown() {
        browser.close();
        playwright.close();
    }
}
