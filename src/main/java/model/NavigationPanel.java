package model;


import common.Interfaces.Selector;
import common.Page;
import common.selectors.XPathSelector;
import org.openqa.selenium.WebDriver;

public class NavigationPanel extends Page {
    @Selector(
            selectorName = "Кнопка навигации(3 горизонтальные линии) в правом верхнем углу",
            elementXPath = "//button[@aria-label=\"Toggle navigation\"]"
    )
    private XPathSelector navigationButton;
    @Selector(
            selectorName = "Кнопка Sign In",
            elementXPath = "//a[@href=\"/login\"]"
    )
    private XPathSelector loginButton;

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
