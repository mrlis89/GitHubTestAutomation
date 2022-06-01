package model;


import common.Interfaces.SelectorXPath;
import common.Page;
import common.selectors.Selector;
import org.openqa.selenium.WebDriver;

public class NavigationPanel extends Page {
    @SelectorXPath(
            selectorName = "Кнопка навигации(3 горизонтальные линии) в правом верхнем углу",
            elementXPath = "//button[@aria-label=\"Toggle navigation\"]"
    )
    private Selector navigationButton;
    @SelectorXPath(
            selectorName = "Кнопка Sign In",
            elementXPath = "//a[@href=\"/login\"]"
    )
    private Selector loginButton;

    public NavigationPanel(WebDriver webDriver) {
        super(webDriver);
    }

    public NavigationPanel open() {
        navigationButton.click();
        return new NavigationPanel(webDriver);
    }

    public AuthorizationPage clickOnSignIn() {
        loginButton.click();
        return new AuthorizationPage(webDriver);
    }
}
