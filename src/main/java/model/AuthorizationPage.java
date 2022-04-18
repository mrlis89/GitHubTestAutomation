package model;

import org.openqa.selenium.By;

import static common.DriverConfigurator.chromeDriver;
import static common.Waiter.*;

public class AuthorizationPage {
    private final By loginField = By.xpath("//input[@id=\"login_field\"]");
    private final By passwordField = By.xpath("//input[@id=\"password\"]");
    private final By signInBtn = By.xpath("//input[@value=\"Sign in\"]");

    public void enterLogin(String login) {
        waitFor(loginField);
        chromeDriver
                .findElement(loginField)
                .sendKeys(login);
    }

    public void enterPassword(String password) {
        chromeDriver
                .findElement(passwordField)
                .sendKeys(password);
    }

    public void clickSignIn() {
        chromeDriver
                .findElement(signInBtn)
                .click();
    }
}
