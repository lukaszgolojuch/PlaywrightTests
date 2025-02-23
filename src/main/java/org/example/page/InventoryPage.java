package org.example.page;

import com.microsoft.playwright.Page;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryPage {

    private final Page page;
    private final String url = "https://www.saucedemo.com/inventory.html";

    public InventoryPage(Page page) {
        this.page = page;
    }

    public void assertPageIsOpened(boolean isPageOpened) {
        if (isPageOpened) {
            assertThat(page.url()).isEqualTo(url);
        } else {
            assertThat(page.url()).isNotEqualTo(url);
        }
    }

}
