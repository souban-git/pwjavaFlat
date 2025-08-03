package com.pwcore;

import com.microsoft.playwright.*;

public class PlaywrightManager {
    private static final ThreadLocal<Playwright> playwright = ThreadLocal.withInitial(Playwright::create);
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    private static String browserType = ""; // Default value

    public static void setBrowserType(String type) {
        browserType = type;
    }

    public static String getBrowserType() {
        return browserType;
    }

    public static void init() {
        BrowserType type;
        switch (getBrowserType().toLowerCase()) {
            case "firefox":
                type = playwright.get().firefox();
                break;
            case "webkit":
                type = playwright.get().webkit();
                break;
            default:
                type = playwright.get().chromium();
        }
        browser.set(type.launch(new BrowserType.LaunchOptions().setHeadless(false)));
        context.set(browser.get().newContext());
        page.set(context.get().newPage());
    }

    public static Page getPage() {
        return page.get();
    }

    public static Playwright getPlaywright() {
        return playwright.get();
    }

    public static Browser getBrowser() {
        return browser.get();
    }

    public static BrowserContext getContext() {
        return context.get();
    }

    public static void close() {
        if (context.get() != null) context.get().close();
        if (browser.get() != null) browser.get().close();
        if (playwright.get() != null) playwright.get().close();
    }
}