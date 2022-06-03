package model;

import common.Interfaces.Selector;
import common.Page;
import common.selectors.XPathSelector;
import dto.UserAccount;
import org.openqa.selenium.WebDriver;

public class AuthorizationPage extends Page {
    @Selector(
            selectorName = "Поле ввода логина",
            elementXPath = "//input[@id=\"login_field\"]"
    )
    private XPathSelector loginField;
    @Selector(
            selectorName = "Поле ввода пароля",
            elementXPath = "//input[@id=\"password\"]"
    )
    private XPathSelector passwordField;
    @Selector(
            selectorName = "Зеленая кнопка Sign In",
            elementXPath = "//input[@value=\"Sign in\"]"
    )
    private XPathSelector signInBtn;

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
