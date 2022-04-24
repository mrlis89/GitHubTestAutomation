package dto;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import model.HomePage;
import model.NavigationPanel;
import model.UserProfilePopup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class userAuthorizationTest {

    UserAccount userAccount;
    WebDriver chromeDriver;

    @BeforeAll
    void setupLoginAndPasswordFromFile() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=/cookies");
        options.addArguments("--window-size=1024,768");

        chromeDriver = new ChromeDriver(options);
        userAccount = UserAccount.getUserAccount();
    }

    @Description("Пользователь проходит авторизацию c корректными логином и паролем")
    @Feature("Authorization")
    @Test
    void UserCanAuthorizeWithCorrectCredentials() {
        new HomePage(chromeDriver).open();
        var navigationPanel = new NavigationPanel(chromeDriver).open();
        var authPage = navigationPanel.clickOnSignIn();
        authPage.enterLogin(userAccount.getLogin());
        authPage.enterPassword(userAccount.getPassword());
        authPage.clickSignIn();
        assertThat(new UserProfilePopup(chromeDriver).isDisplayed()).isTrue();
    }

    @AfterAll
    void UserLogOut() {
        var userProfilePopup = new UserProfilePopup(chromeDriver).open();
        userProfilePopup.clickOnSignOut();
        chromeDriver.quit();
    }
}