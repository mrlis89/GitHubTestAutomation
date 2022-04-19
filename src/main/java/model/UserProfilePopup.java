package model;

import common.Waiter;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class UserProfilePopup {
    private final WebDriver webDriver;
    private Waiter waiter;
    @FindBy(xpath = "//header[@role=\"banner\"]/descendant::summary[@aria-label=\"View profile and more\"]")
    private WebElement userProfileButton;
    @FindBy(xpath = "//form[@class=\"logout-form\"]/descendant::button[@type=\"submit\"]")
    private WebElement signOutButton;

    public UserProfilePopup(WebDriver webDriver) {
        this.webDriver = webDriver;
        waiter = new Waiter(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Step("Нажать кнопку профиля с фотографией в правом верхнем углу, откроется меню профиля")
    public UserProfilePopup open() {
        userProfileButton.click();
        return new UserProfilePopup(webDriver);
    }

    @Step("Нажать кнопку Sign Out чтобы выйти из профиля")
    public void clickOnSignOut() {
        waiter.waitFor(signOutButton);
        signOutButton.click();
    }

    @Step("Проверить что в правом углу экрана появилась кнопка профиля с фотографией")
    public Boolean isDisplayed() {
        return userProfileButton.isDisplayed();
    }
}