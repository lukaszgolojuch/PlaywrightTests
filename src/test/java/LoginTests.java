import com.microsoft.playwright.*;
import org.example.page.InventoryPage;
import org.example.page.LoginPage;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTests {

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

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
        inventoryPage = new InventoryPage(page);
        page.navigate("https://www.saucedemo.com");
    }

    //Tests
    @Test
    public void testValidLogin() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.assertPageIsOpened(true);
    }

    //Tests
    @Test
    public void testInvalidLogin() {
        loginPage.login("standard_user", "invalidPassword");
        loginPage.assertErrorMessageDisplayed("Epic sadface: Username and password do not match any user in this service");
        inventoryPage.assertPageIsOpened(false);
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
