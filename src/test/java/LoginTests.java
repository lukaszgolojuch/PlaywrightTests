import com.microsoft.playwright.*;
import org.example.page.InventoryPage;
import org.example.page.LoginPage;
import org.junit.jupiter.api.*;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeEach
    public void createContext() {
        super.createContext();
        loginPage = new LoginPage(page);
        inventoryPage = new InventoryPage(page);
    }

    @Test
    public void testValidLogin() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.assertPageIsOpened(true);
    }

    @Test
    public void testInvalidLogin() {
        loginPage.login("standard_user", "invalidPassword");
        loginPage.assertErrorMessageDisplayed("Epic sadface: Username and password do not match any user in this service");
        inventoryPage.assertPageIsOpened(false);
    }

}
