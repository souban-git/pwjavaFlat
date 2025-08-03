package com.pwcore.base;

import com.microsoft.playwright.*;
import com.pwcore.PlaywrightManager;
import com.pwcore.utils.PropertyUtil;

import static com.pwcore.PlaywrightManager.setBrowserType;

public class BaseTest {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static void setUp() {
        String browserType = PropertyUtil.getProperty("playwright.browser");
        setBrowserType(browserType); // Set default browser type
        PlaywrightManager.init();
        playwright = PlaywrightManager.getPlaywright();
        browser = PlaywrightManager.getBrowser();
        context = PlaywrightManager.getContext();
        page = PlaywrightManager.getPage();
    }

    public static void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    public static Page getPage() {
        return page;
    }

    public static BrowserContext getContext() {
        return context;
    }
}
