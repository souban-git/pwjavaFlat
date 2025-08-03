package steps;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.pwcore.PlaywrightManager;
import com.pwcore.data.TestDataContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.LoginPage;

import java.util.Map;


public class loginSteps extends PlaywrightManager {

    @Given("^user is on login page$")
    public void userIsOnLoginPage() {
        // This step can be used to navigate to the login page
        // For example, using Playwright or Selenium WebDriver
//        Map<String, Object> data = TestDataContext.current();
        String username = TestDataContext.get("username");
        String password = TestDataContext.get("password");
        getPage().navigate("https://demoqa.com/login");
       new LoginPage().enterLoginCredentials(username, password);
    }

    @Then("^verify user is logged in successfully$")
    public void verifyUserIsLoggedInSuccessfully() {
        PlaywrightAssertions.assertThat(new LoginPage().getLblUserName())
                .isVisible();
    }

}
