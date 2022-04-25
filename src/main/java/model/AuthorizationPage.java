package model;

import common.Waiter;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizationPage {
    private final WebDriver webDriver;
    private Waiter waiter;
    @FindBy(xpath = "//input[@id=\"login_field\"]")
    private WebElement loginField;
    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@value=\"Sign in\"]")
    private WebElement signInBtn;

    public AuthorizationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        waiter = new Waiter(webDriver);
        PageFactory.initElements(webDriver, this);
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
        signInBtn.click();
    }
}
