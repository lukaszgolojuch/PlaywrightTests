import com.microsoft.playwright.*;
import org.example.page.LoginPage;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTests {

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;
    private LoginPage loginPage;

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
        loginPage = new LoginPage(page);
        page.navigate("https://www.saucedemo.com");
    }

    //Tests
    @Test
    public void testValidLogin() {
        loginPage.login("standard_user", "secret_sauce");
        assertThat(page.url()).isEqualTo("https://www.saucedemo.com/inventory.html");
    }

    //Tests
    @Test
    public void testInvalidLogin() {
        loginPage.login("standard_user", "invalidPassword");
        loginPage.assertErrorMessageDisplayed("Epic sadface: Username and password do not match any user in this service");
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
