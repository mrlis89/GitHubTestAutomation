package dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static io.qameta.allure.Allure.step;

class userAccountTest {

    private static UserAccount userAccount;

    @BeforeAll
    static void setupLoginAndPassword() throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        userAccount = mapper.readValue(new File("credentials/gitHubUser.yaml"), UserAccount.class);
    }

    @Test
    void UserAuthorization() {
        WebDriver webDriver = new ChromeDriver();
        step("Открыть github.com/", () -> {
                    webDriver.get("https://github.com/");
                }
        );
        step("Нажать кнопку навигации(3 горизонтальные линии) в правом верхнем углу", () -> {
                    WebElement NavigationBtn = webDriver
                            .findElement(By.xpath("//button[@aria-label=\"Toggle navigation\"]"));
                    NavigationBtn.click();
                }
        );
        step("Нажать кнопку Sign In в нижней части открывшегося меню", () -> {
                    new WebDriverWait(webDriver, Duration.ofSeconds(10))
                            .until(ExpectedConditions.elementToBeClickable(
                                            By.xpath("//a[@href=\"/login\"]")
                                    )
                            );
                    WebElement signInBtn = webDriver.findElement(By.xpath("//a[@href=\"/login\"]"));
                    signInBtn.click();
                }
        );
        step("Ввести логин из файла gitHubUser.yaml", () -> {
                    new WebDriverWait(webDriver, Duration.ofSeconds(10))
                            .until(ExpectedConditions.elementToBeClickable(
                                            By.xpath("//input[@id=\"login_field\"]")
                                    )
                            );
                    WebElement userLogin = webDriver.findElement(By.xpath("//input[@id=\"login_field\"]"));
                    userLogin.sendKeys(userAccount.getLogin());
                }
        );
        step("Ввести пароль из файла gitHubUser.yaml", () -> {
                    WebElement userPassword = webDriver.findElement(By.xpath("//input[@id=\"password\"]"));
                    userPassword.sendKeys(userAccount.getPassword());
                }
        );
        step("Нажать зеленую кнопку Sign In", () -> {
                    WebElement signInBtn = webDriver.findElement(By.xpath("//input[@value=\"Sign in\"]"));
                    signInBtn.click();
                }
        );
    }
}