package dto;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import model.NavigationPanel;
import model.UserProfilePopup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import model.HomePage;
import common.DriverConfigurator;

import static common.DriverConfigurator.chromeDriver;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import static io.qameta.allure.Allure.step;

class userAuthorizationTest {

    private static UserAccount userAccount;

    @BeforeAll
    static void setupLoginAndPasswordFromFile() throws IOException {
        userAccount = UserAccount.getUserAccount();
        DriverConfigurator.setPageSize(1024, 768);
    }

    @Description("Пользователь проходит авторизацию c корректными логином и паролем")
    @Feature("Authorization")
    @Test
    void UserCanAuthorizeWithCorrectCredentials() {
        step("Открыть github.com/", () -> {
                    new HomePage().open();
                }
        );
        var navigationPanel = step(
                "Нажать кнопку навигации(3 горизонтальные линии) в правом верхнем углу",
                () -> new NavigationPanel().open()
        );
        var authPage = step(
                "Нажать кнопку Sign In в нижней части открывшегося меню",
                navigationPanel::clickOnSignIn
        );
        step("Ввести логин из файла gitHubUser.yaml", () -> {
                    authPage.enterLogin(userAccount.getLogin());
                }
        );
        step("Ввести пароль из файла gitHubUser.yaml", () -> {
                    authPage.enterPassword(userAccount.getPassword());
                }
        );
        step("Нажать зеленую кнопку Sign In", authPage::clickSignIn);
        step("Проверить что в правом углу экрана появилась кнопка профиля с фотографией", () -> {
            assertThat(new UserProfilePopup().isDisplayed()).isTrue();
        });
    }

    @AfterAll
    static void UserLogOut() {
        var userProfilePopup = step(
                "Нажать кнопку профиля с фотографией в правом верхнем углу, откроется меню профиля",
                () -> new UserProfilePopup().open()
        );
        step("Нажать кнопку Sign Out чтобы выйти из профиля", userProfilePopup::clickOnLogOut);
        step("Закрыть окно браузера", () -> chromeDriver.quit());
    }
}