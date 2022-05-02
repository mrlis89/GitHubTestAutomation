package model;

import common.Page;
import common.Screenshot;
import dto.UserAccount;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorizationPage extends Page {
    @FindBy(xpath = "//input[@id=\"login_field\"]")
    private WebElement loginField;
    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@value=\"Sign in\"]")
    private WebElement signInBtn;

    public AuthorizationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Ввести логин")
    public void enterLogin(String login) {
        waiter.waitFor(loginField);
        loginField.sendKeys(login);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    @Step("Нажать зеленую кнопку Sign In")
    public void clickSignIn() {
        new Screenshot(webDriver).withName("Введенные данные пользователя");
        signInBtn.click();
    }

    public void authorize(UserAccount userAccount) {
        enterLogin(userAccount.getLogin());
        enterPassword(userAccount.getPassword());
        clickSignIn();
    }
}
