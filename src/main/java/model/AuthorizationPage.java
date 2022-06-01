package model;

import common.Interfaces.SelectorXPath;
import common.Page;
import common.selectors.Selector;
import dto.UserAccount;
import org.openqa.selenium.WebDriver;

public class AuthorizationPage extends Page {
    @SelectorXPath(
            selectorName = "Поле ввода логина",
            elementXPath = "//input[@id=\"login_field\"]"
    )
    private Selector loginField;
    @SelectorXPath(
            selectorName = "Поле ввода пароля",
            elementXPath = "//input[@id=\"password\"]"
    )
    private Selector passwordField;
    @SelectorXPath(
            selectorName = "Зеленая кнопка Sign In",
            elementXPath = "//input[@value=\"Sign in\"]"
    )
    private Selector signInBtn;

    public AuthorizationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterLogin(String login) {
        loginField.input(login);
    }

    public void enterPassword(String password) {
        passwordField.input(password);
    }

    public void clickSignIn() {
        signInBtn.click();
    }

    public void authorize(UserAccount userAccount) {
        enterLogin(userAccount.getLogin());
        enterPassword(userAccount.getPassword());
        clickSignIn();
    }
}
