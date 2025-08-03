package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.pwcore.PlaywrightManager;

public class LoginPage extends PlaywrightManager {

    Page page = PlaywrightManager.getPage();
    private Locator txtUserName = page.getByPlaceholder("UserName");
    private Locator txtPassword = page.getByPlaceholder("Password");
    private Locator btnLogin = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));

    public Page enterLoginCredentials(String username, String password) {
        txtUserName.fill(username);
        txtPassword.fill(password);
        btnLogin.click();
        return page;
    }

    public boolean isLoginSuccessful() {
        // Implement logic to verify if login was successful
        // This could be checking for a specific element that appears after login
        return page.getByText("User Name :").isVisible();
    }

    public Locator getLblUserName() {
        return page.getByText("User Name :");
    }
}
