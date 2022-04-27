package dto;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import model.HomePage;
import model.NavigationPanel;
import model.UserProfilePopup;
import net.bytebuddy.build.Plugin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {
    UserAccount userAccount;
    WebDriver chromeDriver;

    @BeforeTest
    void setupLoginAndPasswordFromFile() throws IOException {
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
        //given
        new HomePage(chromeDriver).open();
        var navigationPanel = new NavigationPanel(chromeDriver).open();
        var authPage = navigationPanel.clickOnSignIn();

        //when
        authPage.enterLogin(userAccount.getLogin());
        authPage.enterPassword(userAccount.getPassword());
        authPage.clickSignIn();

        //then
        assertThat(new UserProfilePopup(chromeDriver).isDisplayed()).isTrue();
    }

    @AfterTest
    void UserLogOut() {
        var userProfilePopup = new UserProfilePopup(chromeDriver).open();
        userProfilePopup.clickOnSignOut();
        chromeDriver.quit();
    }
}
