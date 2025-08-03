package com.pwcore.utils;

import com.microsoft.playwright.Page;
import com.pwcore.PlaywrightManager;

public class BasePage {

    public Page getPage() {
        Page page = PlaywrightManager.getPage();
        if (page == null) {
            throw new IllegalStateException("Page is not initialized. Ensure that PlaywrightManager is properly configured.");
        }
        return page;
    }
}
