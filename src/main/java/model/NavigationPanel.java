package model;

import org.openqa.selenium.By;

import static common.DriverConfigurator.chromeDriver;
import static common.Waiter.waitFor;

public class NavigationPanel {
    private final By navigationButton = By.xpath("//button[@aria-label=\"Toggle navigation\"]");
    private final By loginButton = By.xpath("//a[@href=\"/login\"]");

    public NavigationPanel open() {
        chromeDriver
                .findElement(navigationButton)
                .click();
        return new NavigationPanel();
    }

    public AuthorizationPage clickOnSignIn() {
        waitFor(loginButton);
        chromeDriver
                .findElement(loginButton)
                .click();
        return new AuthorizationPage();
    }
}
