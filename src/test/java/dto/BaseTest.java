package dto;

import common.GWT;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import model.AuthorizationPage;
import model.HomePage;
import model.NavigationPanel;
import model.UserProfilePopup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {
    UserAccount userAccount;
    WebDriver chromeDriver;

    @BeforeSuite
    void setupLoginAndPasswordFromFile() throws IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=/cookies");
        options.addArguments("--window-size=1000,1000");

        chromeDriver = new ChromeDriver(options);
        userAccount = UserAccount.getUserAccount();
    }

    @Test
    @Description("Пользователь проходит авторизацию c корректными логином и паролем")
    @Feature("Authorization")
    void UserCanAuthorizeWithCorrectCredentials() {
        new GWT<AuthorizationPage>().given("Открыта страница авторизации, даны логин и пароль", () -> {
            new HomePage(chromeDriver).open();
            var navigationPanel = new NavigationPanel(chromeDriver).open();
            return navigationPanel.clickOnSignIn();
        }).when("Введены логин и пароль", (authPage) -> {
            authPage.authorize(userAccount);
        }).then("Авторизация успешна, доступна панель пролфиля пользователя", () -> {
        assertThat(new UserProfilePopup(chromeDriver).isDisplayed()).isTrue();
        });
    }

    @AfterSuite
    void UserLogOut() {
        var userProfilePopup = new UserProfilePopup(chromeDriver).open();
        userProfilePopup.clickOnSignOut();
        chromeDriver.quit();
    }
}
