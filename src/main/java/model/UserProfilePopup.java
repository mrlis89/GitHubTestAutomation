package model;

import common.Page;
import common.Screenshot;
import common.Waiter;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class UserProfilePopup extends Page {
    @FindBy(xpath = "//header[@role=\"banner\"]/descendant::summary[@aria-label=\"View profile and more\"]")
    private WebElement userProfileButton;
    @FindBy(xpath = "//form[@class=\"logout-form\"]/descendant::button[@type=\"submit\"]")
    private WebElement signOutButton;
    @FindBy(xpath = "//details-menu[@class=\"dropdown-menu dropdown-menu-sw\"]/descendant::a[@href=\"/mrlis89?tab=repositories\"]")
    private WebElement yourRepositoriesButton;

    public UserProfilePopup(WebDriver webDriver) {
        super(webDriver);
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
    @Step("Нажать кнопку Your repositories чтобы открыть окно с проектами пользователя")
    public YourRepositoriesPage openRepositories() {
        waiter.waitFor(yourRepositoriesButton);
        new Screenshot(webDriver).withName("Выпадающее меню");
        yourRepositoriesButton.click();
        return new YourRepositoriesPage(webDriver);
    }

    @Step("Проверить что в правом углу экрана появилась кнопка профиля с фотографией")
    public Boolean isDisplayed() {
        new Screenshot(webDriver).withName("Пользователь авторизован");
        return userProfileButton.isDisplayed();
    }
}
