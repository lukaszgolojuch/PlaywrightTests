import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class LoginTests {

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;

    //Locators
    private final String usernameInputId = "#user-name";
    private final String passwordInputId = "#password";
    private final String loginButtonId = "#login-button";

    //Initialization
    @BeforeAll
    public static void setup() {
        playwright = Playwright.create();
        browser = playwright
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeEach
    public void createContext() {
        context = browser.newContext();
        page = context.newPage();
    }

    //Tests
    @Test
    public void testValidLogin() {
        page.navigate("https://www.saucedemo.com");
        //fill login inputs
        page.fill(usernameInputId, "standard_user");
        page.fill(passwordInputId, "secret_sauce");
        //click submit button
        page.click(loginButtonId);

        //check if user is logged in
    }

    //Cleanup
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
