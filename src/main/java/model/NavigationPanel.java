package model;


import common.Waiter;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationPanel {
    private final WebDriver webDriver;
    private final Waiter waiter;
    @FindBy(xpath = "//button[@aria-label=\"Toggle navigation\"]")
    private WebElement navigationButton;
    @FindBy(xpath = "//a[@href=\"/login\"]")
    private WebElement loginButton;

    public NavigationPanel(WebDriver webDriver) {
        this.webDriver = webDriver;
        waiter = new Waiter(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Step("Нажать кнопку навигации(3 горизонтальные линии) в правом верхнем углу")
    public NavigationPanel open() {
        navigationButton.click();
        return new NavigationPanel(webDriver);
    }

    @Step("Нажать кнопку Sign In в нижней части открывшегося меню")
    public AuthorizationPage clickOnSignIn() {
        waiter.waitFor(loginButton);
        loginButton.click();
        return new AuthorizationPage(webDriver);
    }
}
