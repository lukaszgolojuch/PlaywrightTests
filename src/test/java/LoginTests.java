import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

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
        page.navigate("https://www.saucedemo.com");
    }

    //Tests
    @Test
    public void testValidLogin() {
        //fill login inputs
        page.fill(usernameInputId, "standard_user");
        page.fill(passwordInputId, "secret_sauce");
        //click submit button
        page.click(loginButtonId);
        //check if user is logged in
        assertThat(page.url()).isEqualTo("https://www.saucedemo.com/inventory.html");
    }

    //Tests
    @Test
    public void testInvalidLogin() {
        //fill login inputs
        page.fill(usernameInputId, "standard_user");
        page.fill(passwordInputId, "invalid_password");
        //click submit button
        page.click(loginButtonId);
        //check if user is logged in
        assertThat(page.url()).isNotEqualTo("https://www.saucedemo.com/inventory.html");
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
