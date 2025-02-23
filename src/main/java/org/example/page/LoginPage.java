package org.example.page;

import com.microsoft.playwright.Page;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage {

    private final Page page;
    private final String usernameInputId = "#user-name";
    private final String passwordInputId = "#password";
    private final String loginButtonId = "#login-button";
    private final String errorMessageId = "[data-test='error']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login(String username, String password) {
        fillUsername(username);
        fillPassword(password);
        submitLogin();
    }

    public void assertErrorMessageDisplayed(String expectedErrorMessage) {
        assertThat(page.textContent(errorMessageId))
                .isEqualTo(expectedErrorMessage);
    }

    private void fillUsername(String username) {
        page.fill(usernameInputId, username);
    }

    private void fillPassword(String password) {
        page.fill(passwordInputId, password);
    }

    private void submitLogin() {
        page.click(loginButtonId);
    }

}
