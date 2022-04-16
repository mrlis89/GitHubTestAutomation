package dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import model.NavigationPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import model.StartPage;
import webDriver.Initialize;

import java.io.IOException;

import static io.qameta.allure.Allure.step;

class userAccountTest {

    private static UserAccount userAccount;

    @BeforeAll
    static void setupLoginAndPassword() throws IOException {
        userAccount = Initialize.userAccount();
    }

    @Test
    void UserAuthorization() {
        step("Открыть github.com/", () -> {
                    new StartPage().open();
                }
        );
        var navigationPanel = step("Нажать кнопку навигации(3 горизонтальные линии) в правом верхнем углу", () -> {
                    var navPanel = new NavigationPanel();
                    return navPanel.open();
                }
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
    }
}